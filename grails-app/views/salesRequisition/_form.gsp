<%@ page import="com.dumplingjoy.pos.SalesRequisition" %>



<div class="fieldcontain ${hasErrors(bean: salesRequisitionInstance, field: 'salesRequisitionNumber', 'error')} required">
	<label for="salesRequisitionNumber">
		<g:message code="salesRequisition.salesRequisitionNumber.label" default="Sales Requisition Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="salesRequisitionNumber" required="" value="${fieldValue(bean: salesRequisitionInstance, field: 'salesRequisitionNumber')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salesRequisitionInstance, field: 'customer', 'error')} required">
	<label for="customer">
		<g:message code="salesRequisition.customer.label" default="Customer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="customer" name="customer.id" from="${com.dumplingjoy.pos.Customer.list()}" optionKey="id" required="" value="${salesRequisitionInstance?.customer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salesRequisitionInstance, field: 'items', 'error')} ">
	<label for="items">
		<g:message code="salesRequisition.items.label" default="Items" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${salesRequisitionInstance?.items?}" var="i">
    <li><g:link controller="salesRequisitionItem" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="salesRequisitionItem" action="create" params="['salesRequisition.id': salesRequisitionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem')])}</g:link>
</li>
</ul>

</div>

