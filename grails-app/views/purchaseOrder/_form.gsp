<%@ page import="com.dumplingjoy.pos.PurchaseOrder" %>



<div class="fieldcontain ${hasErrors(bean: purchaseOrderInstance, field: 'items', 'error')} ">
	<label for="items">
		<g:message code="purchaseOrder.items.label" default="Items" />
		
	</label>
	<g:select name="items" from="${com.dumplingjoy.pos.PurchaseOrderItem.list()}" multiple="multiple" optionKey="id" size="5" value="${purchaseOrderInstance?.items*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: purchaseOrderInstance, field: 'supplier', 'error')} required">
	<label for="supplier">
		<g:message code="purchaseOrder.supplier.label" default="Supplier" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="supplier" name="supplier.id" from="${com.dumplingjoy.pos.Supplier.list()}" optionKey="id" required="" value="${purchaseOrderInstance?.supplier?.id}" class="many-to-one"/>
</div>

