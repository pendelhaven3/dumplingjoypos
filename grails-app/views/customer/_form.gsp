<%@ page import="com.dumplingjoy.pos.Customer" %>



<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="customer.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${customerInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'defaultPricingScheme', 'error')} required">
	<label for="defaultPricingScheme">
		<g:message code="customer.defaultPricingScheme.label" default="Default Pricing Scheme" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="defaultPricingScheme" name="defaultPricingScheme.id" from="${com.dumplingjoy.pos.PricingScheme.list()}" optionKey="id" required="" value="${customerInstance?.defaultPricingScheme?.id}" class="many-to-one"/>
</div>

