
import com.dumplingjoy.pos.AdjustmentInSequenceNumber
import com.dumplingjoy.pos.AdjustmentOut;
import com.dumplingjoy.pos.AdjustmentOutItem;
import com.dumplingjoy.pos.AdjustmentOutSequenceNumber
import com.dumplingjoy.pos.PricingScheme
import com.dumplingjoy.pos.Product;
import com.dumplingjoy.pos.StockQuantityConversionSequenceNumber
import com.dumplingjoy.pos.Unit;
import com.dumplingjoy.pos.UnitQuantity
import com.dumplingjoy.pos.User;

class BootStrap {

    def init = { servletContext ->
		setupInitialUser()
		setupSequences()
		setupDummyProducts()
		setupInitialPricingScheme()
//		setupAdjustmentOutPostErrorScenario()
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
	}
	
	private void setupAdjustmentOutPostErrorScenario() {
		UnitQuantity unitQuantity = Product.findByCode("PROD1").unitQuantities.find {it.unit == Unit.CSE}
		unitQuantity.quantity = 1
		unitQuantity.save(failOnError:true)
		
		UnitQuantity unitQuantity2 = Product.findByCode("PROD2").unitQuantities.find {it.unit == Unit.CSE}
		unitQuantity2.quantity = 1
		unitQuantity2.save(failOnError:true)
		
		AdjustmentOut adjustmentOut = new AdjustmentOut()
		adjustmentOut.adjustmentOutNumber = AdjustmentOutSequenceNumber.getNextValue()
		AdjustmentOutSequenceNumber.increment()
		adjustmentOut.description = "Test Adjustment Out 1"
		
		AdjustmentOutItem adjustmentOutItem = new AdjustmentOutItem()
		adjustmentOutItem.product = Product.findByCode("PROD1")
		adjustmentOutItem.unit = Unit.CSE
		adjustmentOutItem.quantity = 1
		adjustmentOutItem.save(failOnError:true)

		adjustmentOut.addToItems(adjustmentOutItem)
		adjustmentOut.save(failOnError:true)
		
		AdjustmentOut adjustmentOut2 = new AdjustmentOut()
		adjustmentOut2.adjustmentOutNumber = AdjustmentOutSequenceNumber.getNextValue()
		AdjustmentOutSequenceNumber.increment()
		adjustmentOut2.description = "Test Adjustment Out 2"
		
		AdjustmentOutItem adjustmentOutItem2 = new AdjustmentOutItem()
		adjustmentOutItem2.product = Product.findByCode("PROD1")
		adjustmentOutItem2.unit = Unit.CSE
		adjustmentOutItem2.quantity = 1
		adjustmentOutItem2.save(failOnError:true)

		AdjustmentOutItem adjustmentOutItem3 = new AdjustmentOutItem()
		adjustmentOutItem3.product = Product.findByCode("PROD2")
		adjustmentOutItem3.unit = Unit.CSE
		adjustmentOutItem3.quantity = 1
		adjustmentOutItem3.save(failOnError:true)

		adjustmentOut2.addToItems(adjustmentOutItem2)
		adjustmentOut2.addToItems(adjustmentOutItem3)
		adjustmentOut2.save(failOnError:true)
	}
		
}
