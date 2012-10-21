package com.dumplingjoy.pos

import com.dumplingjoy.pos.Unit;

class UnitQuantity {

	Unit unit
	int quantity

	static constraints = {
		unit(nullable: false)
		quantity(nullable: false, min: 0)
	}

}
