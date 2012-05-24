<%@ page import="com.dumplingjoy.pos.StockQuantityConversionItem" %>



<div class="fieldcontain ${hasErrors(bean: stockQuantityConversionItemInstance, field: 'quantity', 'error')} required">
	<label for="quantity">
		<g:message code="stockQuantityConversionItem.quantity.label" default="Quantity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="quantity" min="1" required="" value="${fieldValue(bean: stockQuantityConversionItemInstance, field: 'quantity')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockQuantityConversionItemInstance, field: 'fromUnit', 'error')} required">
	<label for="fromUnit">
		<g:message code="stockQuantityConversionItem.fromUnit.label" default="From Unit" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="fromUnit" from="${com.dumplingjoy.pos.Unit?.values()}" keys="${com.dumplingjoy.pos.Unit.values()*.name()}" required="" value="${stockQuantityConversionItemInstance?.fromUnit?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockQuantityConversionItemInstance, field: 'product', 'error')} required">
	<label for="product">
		<g:message code="stockQuantityConversionItem.product.label" default="Product" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="product" name="product.id" from="${com.dumplingjoy.pos.Product.list()}" optionKey="id" required="" value="${stockQuantityConversionItemInstance?.product?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockQuantityConversionItemInstance, field: 'toUnit', 'error')} required">
	<label for="toUnit">
		<g:message code="stockQuantityConversionItem.toUnit.label" default="To Unit" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="toUnit" from="${com.dumplingjoy.pos.Unit?.values()}" keys="${com.dumplingjoy.pos.Unit.values()*.name()}" required="" value="${stockQuantityConversionItemInstance?.toUnit?.name()}"/>
</div>

