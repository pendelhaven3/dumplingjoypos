<%@ page import="com.dumplingjoy.pos.ProductUnitCost" %>



<div class="fieldcontain ${hasErrors(bean: productUnitCostInstance, field: 'grossCost', 'error')} required">
	<label for="grossCost">
		<g:message code="productUnitCost.grossCost.label" default="Gross Cost" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="grossCost" min="0" required="" value="${fieldValue(bean: productUnitCostInstance, field: 'grossCost')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productUnitCostInstance, field: 'finalCost', 'error')} required">
	<label for="finalCost">
		<g:message code="productUnitCost.finalCost.label" default="Final Cost" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="finalCost" min="0" required="" value="${fieldValue(bean: productUnitCostInstance, field: 'finalCost')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productUnitCostInstance, field: 'product', 'error')} required">
	<label for="product">
		<g:message code="productUnitCost.product.label" default="Product" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="product" name="product.id" from="${com.dumplingjoy.pos.Product.list()}" optionKey="id" required="" value="${productUnitCostInstance?.product?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productUnitCostInstance, field: 'unit', 'error')} required">
	<label for="unit">
		<g:message code="productUnitCost.unit.label" default="Unit" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="unit" from="${com.dumplingjoy.pos.Unit?.values()}" keys="${com.dumplingjoy.pos.Unit.values()*.name()}" required="" value="${productUnitCostInstance?.unit?.name()}"/>
</div>

