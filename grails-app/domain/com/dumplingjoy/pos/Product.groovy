package com.dumplingjoy.pos

class Product {

	String code
	String description
	List<Unit> units
	List<UnitQuantity> unitQuantities
	List<UnitConversion> unitConversions
	
    static constraints = {
		code nullable:false, blank:false, unique:true
		description nullable:false, blank:false, unique:true
    }
	
	static hasMany = [
		units: Unit,
		unitQuantities: UnitQuantity,
		unitConversions: UnitConversion
	]
	
	def beforeInsert() {
		createUnitQuantities()
		createUnitConversions()
	}
	
	private void createUnitQuantities() {
		units.each {
			addToUnitQuantities(new UnitQuantity(unit: it))
		}
	}

	def beforeUpdate() {
		createUnitQuantitiesForNewUnits()
		updateUnitConversions()
//		updateProductUnitPrice()
	}
	
	private void createUnitQuantitiesForNewUnits() {
		units.each { Unit unit ->
			if (!unitQuantities.find {it.unit == unit}) {
				addToUnitQuantities(new UnitQuantity(unit: unit))
			}
		}
	}
	
	private void createUnitConversions() {
		if (units.find {it == Unit.CSE}) {
			if (units.find {it == Unit.CTN}) {
				if (!unitConversions.find {it.fromUnit == Unit.CSE && it.toUnit == Unit.CTN}) {
					UnitConversion unitConversion = new UnitConversion()
					unitConversion.fromUnit = Unit.CSE
					unitConversion.toUnit = Unit.CTN
					unitConversion.convertedQuantity = 0
					addToUnitConversions(unitConversion)
				}
			}
			if (units.find {it == Unit.DOZ}) {
				if (!unitConversions.find {it.fromUnit == Unit.CSE && it.toUnit == Unit.DOZ}) {
					UnitConversion unitConversion = new UnitConversion()
					unitConversion.fromUnit = Unit.CSE
					unitConversion.toUnit = Unit.DOZ
					unitConversion.convertedQuantity = 0
					addToUnitConversions(unitConversion)
				}
			}
			if (units.find {it == Unit.PCS}) {
				if (!unitConversions.find {it.fromUnit == Unit.CSE && it.toUnit == Unit.PCS}) {
					UnitConversion unitConversion = new UnitConversion()
					unitConversion.fromUnit = Unit.CSE
					unitConversion.toUnit = Unit.PCS
					unitConversion.convertedQuantity = 0
					addToUnitConversions(unitConversion)
				}
			}
		}
		if (units.find {it == Unit.CTN}) {
			if (units.find {it == Unit.PCS}) {
				if (!unitConversions.find {it.fromUnit == Unit.CTN && it.toUnit == Unit.PCS}) {
					UnitConversion unitConversion = new UnitConversion()
					unitConversion.fromUnit = Unit.CTN
					unitConversion.toUnit = Unit.PCS
					unitConversion.convertedQuantity = 0
					addToUnitConversions(unitConversion)
				}
			}
		}
	}

	public void updateUnits(List<String> productUnits) {
		productUnits.each {
			Unit unit = Unit.valueOf(it)
			if (!units.contains(unit)) {
				addToUnits(unit)
			}
		}
		
		List<Unit> toRemove = new ArrayList<Unit>()
		units.each {
			if (!productUnits.contains(it.toString())) {
				toRemove.add(it)
			}
		}
		toRemove.each {
			removeFromUnits(it)
		}
	}
	
	public void updateUnitQuantities() {
		List<UnitQuantity> toRemove = new ArrayList<UnitQuantity>()
		unitQuantities.each { UnitQuantity unitQuantity ->
			if (!units.contains(unitQuantity.unit)) {
				toRemove.add(unitQuantity)
			}
		}
		toRemove.each {
			removeFromUnitQuantities(it)
			save(failOnError:true)
			it.delete(failOnError:true)
		}
	}
	
	public void updateUnitConversions() {
		UnitConversion.withNewSession { session ->
			Unit.values().each { Unit unit ->
				if (!units.find {it == unit}) {
					unitConversions.find {it.fromUnit == unit || it.toUnit == unit}.each {
						removeFromUnitConversions(it)
						it.delete(failOnError: true)
					}
				}
			}
			createUnitConversions()
		}
		
	}

	def afterInsert() {
		updateExistingPricingSchemes()
		insertProductUnitCost()
	}
	
	private void updateExistingPricingSchemes() {
		PricingScheme.list().each { PricingScheme pricingScheme ->
			units.each { Unit unit ->
				pricingScheme.addToUnitPrices(new ProductUnitPrice(product: this, unit: unit))
			}
			pricingScheme.save(failOnError: true)
		}
	}
	
	def beforeDelete() {
		ProductUnitPrice.withNewSession { session ->
			ProductUnitPrice.findAllByProduct(this).each {
				it.delete(failOnError: true)
			}
		}
	}
	
	private void insertProductUnitCost() {
		ProductUnitCost.withNewSession { session -> 
			units.each { Unit unit ->
				ProductUnitCost unitCost = new ProductUnitCost()
				unitCost.product = this
				unitCost.unit = unit
				unitCost.save(failOnError: true)
			}
		}
	}
	
}