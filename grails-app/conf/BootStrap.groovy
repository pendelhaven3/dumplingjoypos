
import com.dumplingjoy.pos.AdjustmentInSequenceNumber
import com.dumplingjoy.pos.User;

class BootStrap {

    def init = { servletContext ->
		setupInitialUser()
    }
	
    def destroy = {
    }
	
	private void setupInitialUser() {
		if (!User.findByUsername("joy")) {
			new User(username: "joy", password: "joy", enabled: true).save(failOnError:true)
		}
		
		if (AdjustmentInSequenceNumber.count() == 0) {
			new AdjustmentInSequenceNumber().save(failOnError: true)	
		}
	}
	
}
