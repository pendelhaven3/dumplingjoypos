<%@ page import="com.dumplingjoy.pos.ReceivingReceiptItem" %>



<div class="fieldcontain ${hasErrors(bean: receivingReceiptItemInstance, field: 'quantity', 'error')} required">
	<label for="quantity">
		<g:message code="receivingReceiptItem.quantity.label" default="Quantity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="quantity" min="1" required="" value="${fieldValue(bean: receivingReceiptItemInstance, field: 'quantity')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: receivingReceiptItemInstance, field: 'discount1', 'error')} required">
	<label for="discount1">
		<g:message code="receivingReceiptItem.discount1.label" default="Discount1" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="discount1" min="0" required="" value="${fieldValue(bean: receivingReceiptItemInstance, field: 'discount1')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: receivingReceiptItemInstance, field: 'discount2', 'error')} required">
	<label for="discount2">
		<g:message code="receivingReceiptItem.discount2.label" default="Discount2" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="discount2" min="0" required="" value="${fieldValue(bean: receivingReceiptItemInstance, field: 'discount2')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: receivingReceiptItemInstance, field: 'discount3', 'error')} required">
	<label for="discount3">
		<g:message code="receivingReceiptItem.discount3.label" default="Discount3" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="discount3" min="0" required="" value="${fieldValue(bean: receivingReceiptItemInstance, field: 'discount3')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: receivingReceiptItemInstance, field: 'flatRateDiscount', 'error')} required">
	<label for="flatRateDiscount">
		<g:message code="receivingReceiptItem.flatRateDiscount.label" default="Flat Rate Discount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="flatRateDiscount" min="0" required="" value="${fieldValue(bean: receivingReceiptItemInstance, field: 'flatRateDiscount')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: receivingReceiptItemInstance, field: 'cost', 'error')} required">
	<label for="cost">
		<g:message code="receivingReceiptItem.cost.label" default="Cost" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="cost" required="" value="${fieldValue(bean: receivingReceiptItemInstance, field: 'cost')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: receivingReceiptItemInstance, field: 'product', 'error')} required">
	<label for="product">
		<g:message code="receivingReceiptItem.product.label" default="Product" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="product" name="product.id" from="${com.dumplingjoy.pos.Product.list()}" optionKey="id" required="" value="${receivingReceiptItemInstance?.product?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: receivingReceiptItemInstance, field: 'receivingReceipt', 'error')} required">
	<label for="receivingReceipt">
		<g:message code="receivingReceiptItem.receivingReceipt.label" default="Receiving Receipt" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="receivingReceipt" name="receivingReceipt.id" from="${com.dumplingjoy.pos.ReceivingReceipt.list()}" optionKey="id" required="" value="${receivingReceiptItemInstance?.receivingReceipt?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: receivingReceiptItemInstance, field: 'unit', 'error')} required">
	<label for="unit">
		<g:message code="receivingReceiptItem.unit.label" default="Unit" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="unit" from="${com.dumplingjoy.pos.Unit?.values()}" keys="${com.dumplingjoy.pos.Unit.values()*.name()}" required="" value="${receivingReceiptItemInstance?.unit?.name()}"/>
</div>

