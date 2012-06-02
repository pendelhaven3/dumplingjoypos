<%@ page import="com.dumplingjoy.pos.SalesInvoice" %>



<div class="fieldcontain ${hasErrors(bean: salesInvoiceInstance, field: 'salesInvoiceNumber', 'error')} required">
	<label for="salesInvoiceNumber">
		<g:message code="salesInvoice.salesInvoiceNumber.label" default="Sales Invoice Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="salesInvoiceNumber" required="" value="${fieldValue(bean: salesInvoiceInstance, field: 'salesInvoiceNumber')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salesInvoiceInstance, field: 'customer', 'error')} required">
	<label for="customer">
		<g:message code="salesInvoice.customer.label" default="Customer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="customer" name="customer.id" from="${com.dumplingjoy.pos.Customer.list()}" optionKey="id" required="" value="${salesInvoiceInstance?.customer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salesInvoiceInstance, field: 'items', 'error')} ">
	<label for="items">
		<g:message code="salesInvoice.items.label" default="Items" />
		
	</label>
	<g:select name="items" from="${com.dumplingjoy.pos.SalesInvoiceItem.list()}" multiple="multiple" optionKey="id" size="5" value="${salesInvoiceInstance?.items*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salesInvoiceInstance, field: 'postDate', 'error')} required">
	<label for="postDate">
		<g:message code="salesInvoice.postDate.label" default="Post Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="postDate" precision="day"  value="${salesInvoiceInstance?.postDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: salesInvoiceInstance, field: 'postedBy', 'error')} ">
	<label for="postedBy">
		<g:message code="salesInvoice.postedBy.label" default="Posted By" />
		
	</label>
	<g:textField name="postedBy" value="${salesInvoiceInstance?.postedBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: salesInvoiceInstance, field: 'pricingScheme', 'error')} required">
	<label for="pricingScheme">
		<g:message code="salesInvoice.pricingScheme.label" default="Pricing Scheme" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="pricingScheme" name="pricingScheme.id" from="${com.dumplingjoy.pos.PricingScheme.list()}" optionKey="id" required="" value="${salesInvoiceInstance?.pricingScheme?.id}" class="many-to-one"/>
</div>

