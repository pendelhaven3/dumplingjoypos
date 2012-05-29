package com.dumplingjoy.pos

class Product {

	String code
	String description
	List<Unit> units
	
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
		createUnitConversionsForNewUnits()
	}
	
	private void createUnitQuantitiesForNewUnits() {
		units.each { Unit unit ->
			if (!unitQuantities.find {it.unit == unit}) {
				addToUnitQuantities(new UnitQuantity(unit: unit))
			}
		}
	}
	
	private void createUnitConversions() {
		if (units && units.size() > 1) {
			addToUnitConversions(new UnitConversion(fromUnit: units.min(), toUnit: units.max(), convertedQuantity: 0))
		}
	}

	private void createUnitConversionsForNewUnits() {
		if (units && units.size() > 1) {
			if (!unitConversions.find {it.fromUnit == units.min() && it.toUnit == units.max()}) {
				addToUnitConversions(new UnitConversion(fromUnit: units.min(), toUnit: units.max(), convertedQuantity: 0))
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
		List<UnitConversion> toRemove = new ArrayList<UnitConversion>()
		unitConversions.each { UnitConversion unitConversion ->
			if (!units.contains(unitConversion.fromUnit) || !units.contains(unitConversion.toUnit)) {
				toRemove.add(unitConversion)
			}
		}
		toRemove.each {
			removeFromUnitConversions(it)
			save(failOnError:true)
			it.delete(failOnError:true)
		}
	}
	
	def afterInsert() {
		updatePricingSchemes()
	}
	
	private void updatePricingSchemes() {
		
	}
	
}
