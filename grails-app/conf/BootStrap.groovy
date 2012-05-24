
import com.dumplingjoy.pos.AdjustmentInSequenceNumber
import com.dumplingjoy.pos.AdjustmentOutSequenceNumber
import com.dumplingjoy.pos.Product;
import com.dumplingjoy.pos.StockQuantityConversionSequenceNumber
import com.dumplingjoy.pos.Unit;
import com.dumplingjoy.pos.User;

class BootStrap {

    def init = { servletContext ->
		setupInitialUser()
		setupSequences()
		setupDummyProducts()
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
	}
	
	private void setupDummyProducts() {
		for (int i=1; i<=20; i++) {
			Product product = new Product()
			product.code = "PROD" + i
			product.description = "Product " + i
			product.addToUnits(Unit.CSE)
			product.addToUnits(Unit.PCS)
			product.save(failOnError:true)
		}
	}
	
}
