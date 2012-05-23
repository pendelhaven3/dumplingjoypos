package com.dumplingjoy.pos

import javax.servlet.http.HttpServletRequest

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile

class ProductController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def excelImportService
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.sort = params.sort ?: "code"
		params.order = params.order ?: "asc"
		
        [productInstanceList: Product.list(params), productInstanceTotal: Product.count()]
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
            productInstance.properties = params
			
			updateUnits(productInstance, params.list("productUnits"))
			
            if (!productInstance.hasErrors() && productInstance.save(flush: true)) {
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

	private def updateUnits = { Product product, List<String> productUnits ->
		productUnits.each {
			def unit = Unit.valueOf(it)
			if (!product.units.contains(unit)) {
				product.addToUnits(Unit.valueOf(it))
			}
		}
		
//		product.units.each {
//			def unit = it
//			if (!productUnits.contains(unit.toString())) {
//				def unitPrice = product.unitPrices.find { it.unit = unit }
//				product.removeFromUnitPrices(unitPrice)
//				unitPrice.delete()
//				
//				def unitInventory = product.unitInventories.find { it.unit = unit }
//				product.removeFromUnitInventories(unitInventory)
//				unitInventory.delete()
//
//				product.removeFromUnits(it)
//			}
//		}
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
	
	def importExcel = { }
	
	def doImportExcel = {
		Workbook workbook = getUploadedWorkbook(request, "excelFile")
		Sheet sheet = workbook.getSheetAt(0)
		Iterator<Row> rows = sheet.iterator()
		rows.next() // ignore header row
		
		for (Row row : rows) {
			Cell codeCell = row.getCell(0)
			if (codeCell && !codeCell.getStringCellValue().isEmpty()) {
				Product product = new Product()
				product.code = codeCell.getStringCellValue()
				product.description = row.getCell(1).getStringCellValue()
				product.save(failOnError:true)
			}
		}
		
        flash.message = "Excel file imported."
		redirect action: "list"
	}
	
	private Workbook getUploadedWorkbook(HttpServletRequest request, String excelFileParameterName) {
		MultipartHttpServletRequest mpr = (MultipartHttpServletRequest)request
		CommonsMultipartFile file = (CommonsMultipartFile) mpr.getFile(excelFileParameterName)
		return new HSSFWorkbook(file.getInputStream())
	}
	
}
