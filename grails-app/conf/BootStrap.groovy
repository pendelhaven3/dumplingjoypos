
import com.dumplingjoy.pos.AdjustmentInSequenceNumber
import com.dumplingjoy.pos.AdjustmentOutSequenceNumber
import com.dumplingjoy.pos.Customer
import com.dumplingjoy.pos.PricingScheme
import com.dumplingjoy.pos.Product;
import com.dumplingjoy.pos.SalesRequisitionSequenceNumber
import com.dumplingjoy.pos.StockQuantityConversionSequenceNumber
import com.dumplingjoy.pos.Unit;
import com.dumplingjoy.pos.User;

class BootStrap {

    def init = { servletContext ->
		setupInitialUser()
		setupSequences()
		setupDummyProducts()
		setupInitialPricingScheme() // must be placed after initial products have been created
		setupDummyCustomers()
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
			customer.defaultPricingScheme = PricingScheme.getCanvasserPricingScheme()
			customer.save(failOnError: true)
		}
	}
	
}