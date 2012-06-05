package com.dumplingjoy.pos.json

import java.text.DecimalFormat
import java.util.List;

import com.dumplingjoy.pos.PricingScheme;
import com.dumplingjoy.pos.Product;
import com.dumplingjoy.pos.ProductUnitPrice;
import com.dumplingjoy.pos.UnitConversion;
import com.dumplingjoy.pos.UnitQuantity;

class ProductJson {

	Integer id
	String code
	String description
	List<String> units
	List<UnitQuantity> unitQuantities
	List<UnitConversion> unitConversions
	List<ProductUnitPrice> unitPrices
	
	public ProductJson(Product product, PricingScheme pricingScheme) {
		id = product.id
		code = product.code
		description = product.description
		units = product.units.collect {it.toString()}
		unitQuantities = product.unitQuantities.collect {[unit: it.unit.toString(), quantity: it.quantity]}
		unitConversions = product.unitConversions.collect {[fromUnit: it.fromUnit.toString(), toUnit: it.toUnit.toString(), convertedQuantity: it.convertedQuantity]}
		unitPrices = pricingScheme.getProductUnitPrices(product).collect {[
			unit: it.unit.toString(), 
			price: it.price,
			formattedPrice: new DecimalFormat("#,##0.00").format(it.price)
		]}
	}
	
}