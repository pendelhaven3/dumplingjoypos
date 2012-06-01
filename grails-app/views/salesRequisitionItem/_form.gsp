<%@ page import="com.dumplingjoy.pos.SalesRequisitionItem" %>



<div class="fieldcontain ${hasErrors(bean: salesRequisitionItemInstance, field: 'quantity', 'error')} required">
	<label for="quantity">
		<g:message code="salesRequisitionItem.quantity.label" default="Quantity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="quantity" min="1" required="" value="${fieldValue(bean: salesRequisitionItemInstance, field: 'quantity')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salesRequisitionItemInstance, field: 'product', 'error')} required">
	<label for="product">
		<g:message code="salesRequisitionItem.product.label" default="Product" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="product" name="product.id" from="${com.dumplingjoy.pos.Product.list()}" optionKey="id" required="" value="${salesRequisitionItemInstance?.product?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salesRequisitionItemInstance, field: 'salesRequisition', 'error')} required">
	<label for="salesRequisition">
		<g:message code="salesRequisitionItem.salesRequisition.label" default="Sales Requisition" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="salesRequisition" name="salesRequisition.id" from="${com.dumplingjoy.pos.SalesRequisition.list()}" optionKey="id" required="" value="${salesRequisitionItemInstance?.salesRequisition?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salesRequisitionItemInstance, field: 'unit', 'error')} required">
	<label for="unit">
		<g:message code="salesRequisitionItem.unit.label" default="Unit" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="unit" from="${com.dumplingjoy.pos.Unit?.values()}" keys="${com.dumplingjoy.pos.Unit.values()*.name()}" required="" value="${salesRequisitionItemInstance?.unit?.name()}"/>
</div>

