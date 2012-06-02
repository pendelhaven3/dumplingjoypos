package com.dumplingjoy.pos

import java.util.Date;
import java.util.List;

class StockQuantityConversion {

	transient springSecurityService
	
	Integer stockQuantityConversionNumber
	String remarks
	List<StockQuantityConversionItem> items
	boolean posted
	Date postDate
	String postedBy
	
    static constraints = {
		stockQuantityConversionNumber unique: true
		remarks blank: false
		postDate nullable: true
		postedBy nullable: true
    }
	
	static hasMany = [
		items: StockQuantityConversionItem
	]
	
	public boolean post() {
		StockQuantityConversion.withTransaction { status ->
			items.each { StockQuantityConversionItem item ->
				UnitConversion unitConversion = item.product.unitConversions.find {it.fromUnit == item.fromUnit && it.toUnit == item.toUnit}
				
				UnitQuantity fromUnitQuantity = item.product.unitQuantities.find {it.unit == item.fromUnit}
				fromUnitQuantity.quantity -= item.quantity
				if (fromUnitQuantity.quantity < 0) {
					errors.reject("postItem.notEnoughQuantityAvailable.message", [item.product.code, item.fromUnit] as Object[], 
						"postItem.notEnoughQuantityAvailable.message")
					item.hasPostError = true
				} else {
					fromUnitQuantity.save()
				}
				
				UnitQuantity toUnitQuantity = item.product.unitQuantities.find {it.unit == item.toUnit}
				toUnitQuantity.quantity += item.quantity * unitConversion.convertedQuantity
				toUnitQuantity.save()
			}
			if (hasErrors()) {
				status.setRollbackOnly()
				return false
			}
			posted = true
			postDate = new Date()
			postedBy = ((User)springSecurityService.currentUser).username
			save()
			return true
		}
	}

	public boolean containsItem(StockQuantityConversionItem item) {
		return items.find {it.id != item.id && it.product.id == item.product?.id && it.fromUnit == item.fromUnit && it.toUnit == item.toUnit} != null
	}


}
