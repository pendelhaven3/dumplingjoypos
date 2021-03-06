package com.dumplingjoy.pos

import java.util.Date;
import java.util.List;

class AdjustmentOut {

	transient springSecurityService
	
	Integer adjustmentOutNumber
	String remarks
	PricingScheme pricingScheme
	List<AdjustmentOutItem> items
	boolean posted
	Date postDate
	String postedBy
	
    static constraints = {
		adjustmentOutNumber unique: true
		remarks blank: false
		postDate nullable: true
		postedBy nullable: true
    }
	
	static hasMany = [
		items: AdjustmentOutItem
	]
	
	public boolean post() {
		AdjustmentOut.withTransaction { status ->
			items.each { AdjustmentOutItem item ->
				UnitQuantity unitQuantity = item.product.unitQuantities.find {it.unit == item.unit}
				unitQuantity.quantity -= item.quantity
				if (unitQuantity.quantity < 0) {
					errors.reject("postItem.notEnoughQuantityAvailable.message", [item.product.code, item.unit] as Object[], 
						"postItem.notEnoughQuantityAvailable.message")
					item.hasPostError = true
				} else {
					unitQuantity.save(failOnError: true)
				}
			}
			if (hasErrors()) {
				status.setRollbackOnly()
				return false
			}
			posted = true
			postDate = new Date()
			postedBy = ((User)springSecurityService.currentUser).username
			save(failOnError: true)
			return true
		}
	}
	
	public boolean containsItem(AdjustmentOutItem item) {
		return items.find {it.id != item.id && it.product.id == item.product?.id && it.unit == item.unit} != null
	}

}