package com.dumplingjoy.pos

import java.math.RoundingMode
import java.util.List;

class ReceivingReceipt {

	transient springSecurityService
	
	Integer receivingReceiptNumber
	Supplier supplier
	Date receivedDate
	String receivedBy
	boolean posted
	String postedBy
	Date postDate
	Date orderDate
	DiscountTerms terms
	
	List<ReceivingReceiptItem> items
	
    static constraints = {
		receivingReceiptNumber unique: true, min: 0
		postedBy nullable: true
		postDate nullable: true
    }
	
	static hasMany = [items: ReceivingReceiptItem]

	static transients = ["totalAmount", "totalQuantity", "totalDiscountedAmount", "totalNetAmount"]
	
	public BigDecimal getTotalAmount() {
		BigDecimal total = BigDecimal.ZERO
		items.each {
			total = total.add(it.amount)
		}
		total
	}
	
	public int getTotalQuantity() {
		int totalQuantity = 0
		items.each {
			totalQuantity += it.quantity
		}
		totalQuantity
	}
	
	public BigDecimal getTotalDiscountedAmount() {
		BigDecimal total = BigDecimal.ZERO
		items.each {
			total = total.add(it.discountedAmount)
		}
		total
	}

	public BigDecimal getTotalNetAmount() {
		BigDecimal total = BigDecimal.ZERO
		items.each {
			total = total.add(it.netAmount)
		}
		total
	}

	public boolean post() {
		PurchaseOrder.withTransaction { status ->
			items.each { ReceivingReceiptItem item ->
				UnitQuantity unitQuantity = item.product.unitQuantities.find {it.unit == item.unit}
				unitQuantity.quantity += item.quantity
				unitQuantity.save(failOnError: true)
				
				ProductUnitCost unitCost = ProductUnitCost.findByProductAndUnit(item.product, item.unit)
				unitCost.grossCost = item.cost
				unitCost.finalCost = item.finalCost
				unitCost.save(failOnError: true)
				
				item.product.unitConversions.find {it.fromUnit == item.unit}.each { UnitConversion unitConversion ->
					ProductUnitCost toUnitCost = ProductUnitCost.findByProductAndUnit(item.product, unitConversion.toUnit)
					toUnitCost.grossCost = unitCost.grossCost.divide(new BigDecimal(unitConversion.convertedQuantity), 2, RoundingMode.HALF_UP)
					toUnitCost.finalCost = unitCost.finalCost.divide(new BigDecimal(unitConversion.convertedQuantity), 2, RoundingMode.HALF_UP)
					toUnitCost.save(failOnError: true)
				}
			}

			postedBy = ((User)springSecurityService.currentUser).username
			posted = true
			save(failOnError: true)
			return true
		}
	}

}