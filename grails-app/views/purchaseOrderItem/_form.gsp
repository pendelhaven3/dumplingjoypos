<%@ page import="com.dumplingjoy.pos.PurchaseOrderItem" %>



<div class="fieldcontain ${hasErrors(bean: purchaseOrderItemInstance, field: 'quantity', 'error')} required">
	<label for="quantity">
		<g:message code="purchaseOrderItem.quantity.label" default="Quantity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="quantity" min="0" required="" value="${fieldValue(bean: purchaseOrderItemInstance, field: 'quantity')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: purchaseOrderItemInstance, field: 'cost', 'error')} required">
	<label for="cost">
		<g:message code="purchaseOrderItem.cost.label" default="Cost" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="cost" required="" value="${fieldValue(bean: purchaseOrderItemInstance, field: 'cost')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: purchaseOrderItemInstance, field: 'product', 'error')} required">
	<label for="product">
		<g:message code="purchaseOrderItem.product.label" default="Product" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="product" name="product.id" from="${com.dumplingjoy.pos.Product.list()}" optionKey="id" required="" value="${purchaseOrderItemInstance?.product?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: purchaseOrderItemInstance, field: 'unit', 'error')} required">
	<label for="unit">
		<g:message code="purchaseOrderItem.unit.label" default="Unit" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="unit" from="${com.dumplingjoy.pos.Unit?.values()}" keys="${com.dumplingjoy.pos.Unit.values()*.name()}" required="" value="${purchaseOrderItemInstance?.unit?.name()}"/>
</div>

