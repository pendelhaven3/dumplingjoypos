package com.dumplingjoy.pos

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import javax.servlet.http.HttpServletRequest

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile

import com.dumplingjoy.pos.json.ProductJson

@Secured("isFullyAuthenticated()")
class ProductController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def excelImportService
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
		params.sort = params.sort ?: "code"
		params.order = params.order ?: "asc"
		
		def productInstanceList = Product.withCriteria {
			if (!StringUtils.isEmpty(params.code)) {
				ilike("code", params.code + "%")
			}
			firstResult(params.int("offset") ?: 0)
			maxResults(10)
			order(params.sort, params.order)
		}
		
		def productInstanceTotal
		if (!StringUtils.isEmpty(params.code)) {
			productInstanceTotal = Product.countByCodeIlike(params.code + "%")
		} else {
			productInstanceTotal = Product.count()
		}
		
        [productInstanceList: productInstanceList, productInstanceTotal: productInstanceTotal]
    }

    def create = {
        def productInstance = new Product()
        productInstance.properties = params
        return [productInstance: productInstance]
    }

    def save = {
        def productInstance = new Product(params)
		
		def productUnits = params.list("productUnits")
		productUnits.each {
			productInstance.addToUnits(Unit.valueOf(it))
		}

        if (productInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'product.label', default: 'Product'), productInstance.id])}"
            redirect(action: "show", id: productInstance.id)
        }
        else {
            render(view: "create", model: [productInstance: productInstance])
        }
    }

    def show = {
        def productInstance = Product.get(params.id)
        if (!productInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
            redirect(action: "list")
        }
        else {
            [productInstance: productInstance]
        }
    }

    def edit = {
        def productInstance = Product.get(params.id)
        if (!productInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [productInstance: productInstance]
        }
    }

    def update = {
        def productInstance = Product.get(params.id)
        if (productInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (productInstance.version > version) {
                    
                    productInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'product.label', default: 'Product')] as Object[], "Another user has updated this Product while you were editing")
                    render(view: "edit", model: [productInstance: productInstance])
                    return
                }
            }
			
			productInstance.manufacturer = null
			productInstance.category = null
            productInstance.properties = params
			productInstance.updateUnits(params.list("productUnits"))
			
            if (!productInstance.hasErrors() && productInstance.save(flush: true)) {
				
				productInstance.updateUnitQuantities()
				productInstance.updateUnitConversions()
				
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'product.label', default: 'Product'), productInstance.id])}"
                redirect(action: "show", id: productInstance.id)
            }
            else {
                render(view: "edit", model: [productInstance: productInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def productInstance = Product.get(params.id)
        if (productInstance) {
            try {
                productInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def getProductByCode = {
		Supplier supplierInstance = null
		if (params["supplierId"]) {
			supplierInstance = Supplier.get(params["supplierId"])
		}
		
		Product productInstance = null
		if (supplierInstance) {
			productInstance = Product.find("from Product p where p.code = ? and p in " +
				"(select supplierProducts from Supplier s inner join s.products supplierProducts where s.id = ?)",
				[params.code, supplierInstance.id])
		} else {
			productInstance = Product.findByCode(params.code)
		}
		
		if (!productInstance) {
			render new ArrayList() as JSON
			return
		}
		
		PricingScheme pricingScheme = null
		if (params["pricingSchemeId"]) {
			pricingScheme = PricingScheme.get(params["pricingSchemeId"])
		} else {
			pricingScheme = PricingScheme.getCanvasserPricingScheme()
		}
		
		render new ProductJson(productInstance, pricingScheme) as JSON
	}
	
	def select = {
		render template: "select", model: [code: StringUtils.defaultString(params.code)]
	}
	
	def searchProductsByCode = {
		Supplier supplierInstance = null
		if (params["supplierId"]) {
			supplierInstance = Supplier.get(params["supplierId"])
		}

		if (params.code?.trim()) {
			
			if (params.code.equals("*")) {
				if (supplierInstance) {
					render supplierInstance.products.sort {it.code} as JSON
				} else {
					render Product.findAll([sort:"code", order:"asc"]) as JSON
				}
			} else {
				if (supplierInstance) {
					render Product.executeQuery("select supplierProducts from Supplier s inner join s.products supplierProducts " +
						"where s.id = ? and supplierProducts.code like ?)",
						[supplierInstance.id, params.code + "%"]) as JSON
				} else {
					render Product.findAllByCodeIlike(params.code + "%", [sort:"code", order:"asc"]) as JSON
				}
			}
			
		} else {
			render new ArrayList() as JSON
		}
	}
	
}
