package com.dumplingjoy.pos

class Product {

	String code
	String description
	List<Unit> units
	List<UnitQuantity> unitQuantities
	List<UnitConversion> unitConversions
	Integer minimumLevel
	Integer maximumLevel
	Manufacturer manufacturer
	
    static constraints = {
		code nullable:false, blank:false, unique:true
		description nullable:false, blank:false, unique:true
		minimumLevel nullable: true, min: 0, validator: validateMinimumMaximumLevels
		maximumLevel nullable: true, min: 0
		manufacturer nullable: true
    }
	
	static hasMany = [
		units: Unit,
		unitQuantities: UnitQuantity,
		unitConversions: UnitConversion
	]
	
	private static def validateMinimumMaximumLevels = { Integer minimumLevel, Product product ->
		if (product.minimumLevel && product.maximumLevel) {
			if (product.minimumLevel > product.maximumLevel) {
				return ["default.invalid.min.message", "Minimum Level", "Maximum Level"]
			}
		}
		return true
	}

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
		updatePricingSchemes()
		updateProductUnitCosts()
	}
	
	private void updatePricingSchemes() {
		ProductUnitPrice.withNewSession { session ->
			Unit.values().each { Unit unit ->
				if (!units.find {it == unit}) {
					ProductUnitPrice.executeUpdate("delete from ProductUnitPrice up where up.product = ? and up.unit = ?", [this, unit])
				}
			}
		}
		
		PricingScheme.list().each { PricingScheme pricingScheme ->
			units.each { Unit unit ->
				if (!ProductUnitPrice.find("from ProductUnitPrice up where up.pricingScheme = ? and up.product = ? and up.unit = ?", 
						[pricingScheme, this, unit])) {
					pricingScheme.addToUnitPrices(new ProductUnitPrice(product: this, unit: unit))
				}
			}
			pricingScheme.save(failOnError: true)
		}
	}
	
	// TODO: Review On delete Product
	def beforeDelete() {
		ProductUnitPrice.withNewSession { session ->
			ProductUnitPrice.findAllByProduct(this).each {
				it.delete(failOnError: true)
			}
		}
	}
	
	private void updateProductUnitCosts() {
		ProductUnitCost.withNewSession { session -> 
			Unit.values().each { Unit unit ->
				if (!units.find {it == unit}) {
					ProductUnitCost.executeUpdate("delete from ProductUnitCost uc where uc.product = ? and uc.unit = ?", [this, unit])
				} else {
					if (!ProductUnitCost.find("from ProductUnitCost uc where uc.product = ? and uc.unit = ?", [this, unit])) {
						new ProductUnitCost(product: this, unit: unit).save(failOnError: true)
					}
				}
			}
		}
	}
	
	def afterUpdate() {
		updatePricingSchemes()
		updateProductUnitCosts()
	}

}