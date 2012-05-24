
import com.dumplingjoy.pos.AdjustmentInSequenceNumber
import com.dumplingjoy.pos.AdjustmentOutSequenceNumber
import com.dumplingjoy.pos.StockQuantityConversionSequenceNumber
import com.dumplingjoy.pos.User;

class BootStrap {

    def init = { servletContext ->
		setupInitialUser()
		setupSequences()
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
	
}
