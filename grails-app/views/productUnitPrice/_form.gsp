<%@ page import="com.dumplingjoy.pos.ProductUnitPrice" %>



<div class="fieldcontain ${hasErrors(bean: productUnitPriceInstance, field: 'price', 'error')} required">
	<label for="price">
		<g:message code="productUnitPrice.price.label" default="Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="price" min="0" required="" value="${fieldValue(bean: productUnitPriceInstance, field: 'price')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productUnitPriceInstance, field: 'product', 'error')} required">
	<label for="product">
		<g:message code="productUnitPrice.product.label" default="Product" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="product" name="product.id" from="${com.dumplingjoy.pos.Product.list()}" optionKey="id" required="" value="${productUnitPriceInstance?.product?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productUnitPriceInstance, field: 'unit', 'error')} required">
	<label for="unit">
		<g:message code="productUnitPrice.unit.label" default="Unit" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="unit" from="${com.dumplingjoy.pos.Unit?.values()}" keys="${com.dumplingjoy.pos.Unit.values()*.name()}" required="" value="${productUnitPriceInstance?.unit?.name()}"/>
</div>

