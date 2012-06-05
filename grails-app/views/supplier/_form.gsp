<%@ page import="com.dumplingjoy.pos.Supplier" %>



<div class="fieldcontain ${hasErrors(bean: supplierInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="supplier.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${supplierInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: supplierInstance, field: 'products', 'error')} ">
	<label for="products">
		<g:message code="supplier.products.label" default="Products" />
		
	</label>
	<g:select name="products" from="${com.dumplingjoy.pos.Product.list()}" multiple="multiple" optionKey="id" size="5" value="${supplierInstance?.products*.id}" class="many-to-many"/>
</div>

