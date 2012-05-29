<%@ page import="com.dumplingjoy.pos.PricingScheme" %>



<div class="fieldcontain ${hasErrors(bean: pricingSchemeInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="pricingScheme.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${pricingSchemeInstance?.description}"/>
</div>

