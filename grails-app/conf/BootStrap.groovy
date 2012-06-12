

import com.dumplingjoy.pos.AdjustmentInSequenceNumber
import com.dumplingjoy.pos.AdjustmentOutSequenceNumber
import com.dumplingjoy.pos.PricingScheme
import com.dumplingjoy.pos.PurchaseOrderSequenceNumber
import com.dumplingjoy.pos.ReceivingReceiptSequenceNumber
import com.dumplingjoy.pos.Role
import com.dumplingjoy.pos.SalesInvoiceSequenceNumber
import com.dumplingjoy.pos.SalesRequisitionSequenceNumber
import com.dumplingjoy.pos.StockQuantityConversionSequenceNumber
import com.dumplingjoy.pos.UnitQuantity
import com.dumplingjoy.pos.User;
import com.dumplingjoy.pos.UserRole

class BootStrap {

	def bootStrapService
	
    def init = { servletContext ->
		if (!isDataInitialized()) {
			setupUserRoles()
			setupInitialUser()
			setupSequences()
//			bootStrapService.importProductsFromExcel()
			setupInitialPricingScheme() // must be placed after initial products have been created
		}
    }
	
    def destroy = {
    }
	
	private void setupUserRoles() {
		new Role(authority: "ROLE_MANAGER").save(failOnError: true)
	}
	
	private void setupInitialUser() {
		User user = new User(username: "joy", name: "Joy Apolonio", password: "joy", enabled: true).save(failOnError:true)
		Role managerRole = Role.findByAuthority("ROLE_MANAGER")
		new UserRole(user: user, role: managerRole).save(failOnError: true)
		
		user = new User(username: "pj", name: "PJ Miranda", password: "password", enabled: true).save(failOnError:true)
		new UserRole(user: user, role: managerRole).save(failOnError: true)
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
		if (PurchaseOrderSequenceNumber.count() == 0) {
			new PurchaseOrderSequenceNumber().save(failOnError: true)	
		}
		if (ReceivingReceiptSequenceNumber.count() == 0) {
			new ReceivingReceiptSequenceNumber().save(failOnError: true)	
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
	
	private boolean isDataInitialized() {
		return AdjustmentInSequenceNumber.get(1) != null
	}
	
}