package com.dumplingjoy.pos

class Product {

	String code
	String description
	
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
	
}
