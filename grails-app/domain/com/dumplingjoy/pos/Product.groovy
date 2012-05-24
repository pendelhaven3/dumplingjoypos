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
	}
	
	private void createUnitQuantitiesForNewUnits() {
		units.each { Unit unit ->
			if (!unitQuantities.find {it.unit == unit}) {
				addToUnitQuantities(new UnitQuantity(unit: unit))
			}
		}
	}
	
	private void createUnitConversions() {
		if (units.size() > 1) {
			addToUnitConversions(new UnitConversion(fromUnit: units.min(), toUnit: units.max(), convertedQuantity: 0))
		}
	}


}
