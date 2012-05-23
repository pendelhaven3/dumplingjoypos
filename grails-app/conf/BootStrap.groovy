import javax.transaction.UserTransaction;

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
	}
	
}
