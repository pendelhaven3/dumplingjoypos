package com.dumplingjoy.pos

import java.math.RoundingMode;

class ProductUnitPriceService {

	def update(ProductUnitPrice productUnitPrice) {
		productUnitPrice.save(fail: true)
		
		Product product = productUnitPrice.product
		product.units.each { unit ->
			if (unit.order < productUnitPrice.unit.order) {
				int convertedQuantity = product.unitConversions.find { unitConversion ->
					unitConversion.fromUnit == productUnitPrice.unit && unitConversion.toUnit == unit
				}.convertedQuantity
				
				ProductUnitPrice smallerUnitPrice = ProductUnitPrice.findByProductAndUnit(product, unit)
				smallerUnitPrice.price = productUnitPrice.price.divide(new BigDecimal(convertedQuantity), RoundingMode.UP)
				smallerUnitPrice.save(fail: true)
			}
		}
		
		return true
	}
	
}
