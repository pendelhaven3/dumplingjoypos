<%@ page import="com.dumplingjoy.pos.ReceivingReceipt" %>



<div class="fieldcontain ${hasErrors(bean: receivingReceiptInstance, field: 'receivingReceiptNumber', 'error')} required">
	<label for="receivingReceiptNumber">
		<g:message code="receivingReceipt.receivingReceiptNumber.label" default="Receiving Receipt Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="receivingReceiptNumber" min="0" required="" value="${fieldValue(bean: receivingReceiptInstance, field: 'receivingReceiptNumber')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: receivingReceiptInstance, field: 'items', 'error')} ">
	<label for="items">
		<g:message code="receivingReceipt.items.label" default="Items" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${receivingReceiptInstance?.items?}" var="i">
    <li><g:link controller="receivingReceiptItem" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="receivingReceiptItem" action="create" params="['receivingReceipt.id': receivingReceiptInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'receivingReceiptItem.label', default: 'ReceivingReceiptItem')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: receivingReceiptInstance, field: 'receivedDate', 'error')} required">
	<label for="receivedDate">
		<g:message code="receivingReceipt.receivedDate.label" default="Received Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="receivedDate" precision="day"  value="${receivingReceiptInstance?.receivedDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: receivingReceiptInstance, field: 'supplier', 'error')} required">
	<label for="supplier">
		<g:message code="receivingReceipt.supplier.label" default="Supplier" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="supplier" name="supplier.id" from="${com.dumplingjoy.pos.Supplier.list()}" optionKey="id" required="" value="${receivingReceiptInstance?.supplier?.id}" class="many-to-one"/>
</div>

