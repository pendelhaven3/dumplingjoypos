package com.dumplingjoy.pos

import java.util.Date;
import java.util.List;

class AdjustmentOut {

	transient springSecurityService
	
	Integer adjustmentOutNumber
	String description
	List<AdjustmentOutItem> items
	boolean posted
	Date postDate
	String postedBy
	
    static constraints = {
		adjustmentOutNumber nullable: false, unique: true
		description nullable: false, blank: false
		postDate nullable: true
		postedBy nullable: true
    }
	
	static hasMany = [
		items: AdjustmentOutItem
	]
	
	public void post() {
		items.each { AdjustmentOutItem item ->
			UnitQuantity unitQuantity = item.product.unitQuantities.find {it.unit == item.unit}
			unitQuantity.quantity -= item.quantity
			unitQuantity.save(failOnError:true)
		}
		posted = true
		postDate = new Date()
		postedBy = ((User)springSecurityService.currentUser).username
		save(failOnError:true)
	}
	
}