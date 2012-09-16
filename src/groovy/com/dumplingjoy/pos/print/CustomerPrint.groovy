package com.dumplingjoy.pos.print

import com.dumplingjoy.pos.Customer

class CustomerPrint {

	String name
	String address1
	String address2
	
	public CustomerPrint(Customer customer) {
		name = PrintUtil.asLeftAlignedField(customer.name, 30)
		
		if (customer.address.size() > 30) {
			address1 = customer.address.substring(0, 30)
			if (customer.address.size() > 60) {
				address2 = customer.address.substring(30, 60)
			} else {
				address2 = PrintUtil.asLeftAlignedField(customer.address.substring(30), 30)
			}
		} else {
			address1 = PrintUtil.asLeftAlignedField(customer.address, 30)
			address2 = ""
		}
		
	}
	
}
