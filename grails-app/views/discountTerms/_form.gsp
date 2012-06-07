<%@ page import="com.dumplingjoy.pos.DiscountTerms" %>



<div class="fieldcontain ${hasErrors(bean: discountTermsInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="discountTerms.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${discountTermsInstance?.name}"/>
</div>

