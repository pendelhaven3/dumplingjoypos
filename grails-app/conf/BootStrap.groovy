
import java.text.DecimalFormat

import com.dumplingjoy.pos.AdjustmentInSequenceNumber
import com.dumplingjoy.pos.AdjustmentOutSequenceNumber
import com.dumplingjoy.pos.Customer
import com.dumplingjoy.pos.PricingScheme
import com.dumplingjoy.pos.Product;
import com.dumplingjoy.pos.ProductUnitPrice
import com.dumplingjoy.pos.SalesInvoiceSequenceNumber
import com.dumplingjoy.pos.SalesRequisition
import com.dumplingjoy.pos.SalesRequisitionItem
import com.dumplingjoy.pos.SalesRequisitionSequenceNumber
import com.dumplingjoy.pos.StockQuantityConversionSequenceNumber
import com.dumplingjoy.pos.Unit;
import com.dumplingjoy.pos.User;

class BootStrap {

	def bootStrapService
	
    def init = { servletContext ->
		setupInitialUser()
		setupSequences()
		bootStrapService.importProductsFromExcel()
//		setupDummyProducts()
		setupInitialPricingScheme() // must be placed after initial products have been created
		setupDummyCustomers()
//		setupDummySalesInvoice()
    }
	
    def destroy = {
    }
	
	private void setupInitialUser() {
		if (!User.findByUsername("joy")) {
			new User(username: "joy", password: "joy", enabled: true).save(failOnError:true)
		}
	}
	
	private void setupSequences() {
		if (AdjustmentInSequenceNumber.count() == 0) {
			new AdjustmentInSequenceNumber().save(failOnError: true)	
		}
		if (AdjustmentOutSequenceNumber.count() == 0) {
			new AdjustmentOutSequenceNumber().save(failOnError: true)	
		}
		if (StockQuantityConversionSequenceNumber.count() == 0) {
			new StockQuantityConversionSequenceNumber().save(failOnError: true)	
		}
		if (SalesRequisitionSequenceNumber.count() == 0) {
			new SalesRequisitionSequenceNumber().save(failOnError: true)	
		}
		if (SalesInvoiceSequenceNumber.count() == 0) {
			new SalesInvoiceSequenceNumber().save(failOnError: true)	
		}
	}
	
	private void setupInitialPricingScheme() {
		PricingScheme pricingScheme = new PricingScheme()
		pricingScheme.description = "Canvasser"
		pricingScheme.save(failOnError: true)
		
		pricingScheme = new PricingScheme()
		pricingScheme.description = "Marketing"
		pricingScheme.save(failOnError: true)
	}
	
	private void setupDummyCustomers() {
		for (int i=1; i<=20; i++) {
			Customer customer = new Customer()
			customer.name = "Customer " + i
			customer.address = "Address " + i
			customer.defaultPricingScheme = PricingScheme.getCanvasserPricingScheme()
			customer.save(failOnError: true)
		}
	}
	
	private void setupDummyProducts() {
		for (int i=1; i<=20; i++) {
			Product product = new Product()
			product.code = "PROD" + i
			product.description = "Product " + i
			product.addToUnits(Unit.CSE)
			product.addToUnits(Unit.PCS)
			product.save(failOnError: true)
			
			product.unitQuantities.each {
				it.quantity = 100
				it.save(failOnError: true)
			}
		}
	}
	
	private void setupDummySalesInvoice() {
		SalesRequisition salesRequisition = new SalesRequisition()
		salesRequisition.salesRequisitionNumber = SalesRequisitionSequenceNumber.getNextValue()	
		SalesRequisitionSequenceNumber.increment()
		salesRequisition.customer = Customer.get(1)
		salesRequisition.pricingScheme = PricingScheme.getCanvasserPricingScheme()
		salesRequisition.orderType = "Walk-in"
		salesRequisition.postedBy = "TESTING"
		salesRequisition.createdBy = "TESTING"
		
		Product productInstance = Product.get(1)
		
		List<ProductUnitPrice> unitPrices = PricingScheme.getCanvasserPricingScheme().getProductUnitPrices(productInstance)
		ProductUnitPrice unitPrice = unitPrices.find {it.unit == Unit.CSE}
		unitPrice.price = new BigDecimal("125234.36")
		unitPrice.save(failOnError: true)
		
		unitPrice = unitPrices.find {it.unit == Unit.PCS}
		unitPrice.price = new BigDecimal("69.78")
		unitPrice.save(failOnError: true)

		SalesRequisitionItem item = new SalesRequisitionItem()
		item.product = productInstance
		item.unit = Unit.CSE
		item.quantity = 8
		salesRequisition.addToItems(item)
		
		item = new SalesRequisitionItem()
		item.product = productInstance
		item.unit = Unit.PCS
		item.quantity = 1
		salesRequisition.addToItems(item)
		
		salesRequisition.save(failOnError: true)
//		salesRequisition.post()
	}
	
}