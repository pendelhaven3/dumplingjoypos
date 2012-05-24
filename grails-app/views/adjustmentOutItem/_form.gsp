<%@ page import="com.dumplingjoy.pos.AdjustmentOutItem" %>



<div class="fieldcontain ${hasErrors(bean: adjustmentOutItemInstance, field: 'product', 'error')} required">
	<label for="product">
		<g:message code="adjustmentOutItem.product.label" default="Product" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="product" name="product.id" from="${com.dumplingjoy.pos.Product.list()}" optionKey="id" required="" value="${adjustmentOutItemInstance?.product?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adjustmentOutItemInstance, field: 'unit', 'error')} required">
	<label for="unit">
		<g:message code="adjustmentOutItem.unit.label" default="Unit" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="unit" from="${com.dumplingjoy.pos.Unit?.values()}" keys="${com.dumplingjoy.pos.Unit.values()*.name()}" required="" value="${adjustmentOutItemInstance?.unit?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adjustmentOutItemInstance, field: 'quantity', 'error')} required">
	<label for="quantity">
		<g:message code="adjustmentOutItem.quantity.label" default="Quantity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="quantity" min="1" required="" value="${fieldValue(bean: adjustmentOutItemInstance, field: 'quantity')}"/>
</div>

