package com.dumplingjoy.pos

class AdjustmentIn {

	transient springSecurityService
	
	Integer adjustmentInNumber
	String remarks
	List<AdjustmentInItem> items
	boolean posted
	Date postDate
	String postedBy
	
    static constraints = {
		adjustmentInNumber unique: true
		remarks blank: false
		postDate nullable: true
		postedBy nullable: true
    }
	
	static hasMany = [
		items: AdjustmentInItem
	]
	
	public void post() {
		items.each { AdjustmentInItem item ->
			UnitQuantity unitQuantity = item.product.unitQuantities.find {it.unit == item.unit}
			unitQuantity.quantity += item.quantity
			unitQuantity.save(failOnError:true)
		}
		posted = true
		postDate = new Date()
		postedBy = ((User)springSecurityService.currentUser).username
		save(failOnError:true)
	}
	
	public boolean containsItem(AdjustmentInItem item) {
		return items.find {it.id != item.id && it.product.id == item.product?.id && it.unit == item.unit} != null
	}
	
}