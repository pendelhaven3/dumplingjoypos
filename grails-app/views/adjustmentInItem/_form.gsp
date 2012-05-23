<%@ page import="com.dumplingjoy.pos.AdjustmentInItem" %>



<div class="fieldcontain ${hasErrors(bean: adjustmentInItemInstance, field: 'product', 'error')} required">
	<label for="product">
		<g:message code="adjustmentInItem.product.label" default="Product" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="product" name="product.id" from="${com.dumplingjoy.pos.Product.list()}" optionKey="id" required="" value="${adjustmentInItemInstance?.product?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adjustmentInItemInstance, field: 'unit', 'error')} required">
	<label for="unit">
		<g:message code="adjustmentInItem.unit.label" default="Unit" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="unit" from="${com.dumplingjoy.pos.Unit?.values()}" keys="${com.dumplingjoy.pos.Unit.values()*.name()}" required="" value="${adjustmentInItemInstance?.unit?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adjustmentInItemInstance, field: 'quantity', 'error')} required">
	<label for="quantity">
		<g:message code="adjustmentInItem.quantity.label" default="Quantity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="quantity" min="1" required="" value="${fieldValue(bean: adjustmentInItemInstance, field: 'quantity')}"/>
</div>

