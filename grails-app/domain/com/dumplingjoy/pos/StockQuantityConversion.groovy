package com.dumplingjoy.pos

import java.util.Date;
import java.util.List;

class StockQuantityConversion {

	transient springSecurityService
	
	Integer stockQuantityConversionNumber
	String description
	List<StockQuantityConversionItem> items
	boolean posted
	Date postDate
	String postedBy
	
    static constraints = {
		stockQuantityConversionNumber nullable: false, unique: true
		description nullable: false, blank: false
		postDate nullable: true
		postedBy nullable: true
    }
	
	static hasMany = [
		items: StockQuantityConversionItem
	]
	
	public void post() {
		items.each { StockQuantityConversionItem item ->
			UnitConversion unitConversion = item.product.unitConversions.find {it.fromUnit == item.fromUnit && it.toUnit == item.toUnit}
			
			UnitQuantity fromUnitQuantity = item.product.unitQuantities.find {it.unit == item.fromUnit}
			fromUnitQuantity.quantity -= item.quantity
			fromUnitQuantity.save(failOnError:true)
			
			UnitQuantity toUnitQuantity = item.product.unitQuantities.find {it.unit == item.toUnit}
			toUnitQuantity.quantity += item.quantity * unitConversion.convertedQuantity
			toUnitQuantity.save(failOnError:true)
		}
		posted = true
		postDate = new Date()
		postedBy = ((User)springSecurityService.currentUser).username
		save(failOnError:true)
	}

	public boolean containsItem(StockQuantityConversionItem item) {
		return items.find {it.id != item.id && it.product.id == item.product?.id} != null
	}


}
