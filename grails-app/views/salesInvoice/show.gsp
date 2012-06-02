
<%@ page import="com.dumplingjoy.pos.SalesInvoice" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'salesInvoice.label', default: 'SalesInvoice')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-salesInvoice" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-salesInvoice" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list salesInvoice">
			
				<g:if test="${salesInvoiceInstance?.salesInvoiceNumber}">
				<li class="fieldcontain">
					<span id="salesInvoiceNumber-label" class="property-label"><g:message code="salesInvoice.salesInvoiceNumber.label" default="Sales Invoice Number" /></span>
					
						<span class="property-value" aria-labelledby="salesInvoiceNumber-label"><g:fieldValue bean="${salesInvoiceInstance}" field="salesInvoiceNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${salesInvoiceInstance?.customer}">
				<li class="fieldcontain">
					<span id="customer-label" class="property-label"><g:message code="salesInvoice.customer.label" default="Customer" /></span>
					
						<span class="property-value" aria-labelledby="customer-label"><g:link controller="customer" action="show" id="${salesInvoiceInstance?.customer?.id}">${salesInvoiceInstance?.customer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${salesInvoiceInstance?.items}">
				<li class="fieldcontain">
					<span id="items-label" class="property-label"><g:message code="salesInvoice.items.label" default="Items" /></span>
					
						<g:each in="${salesInvoiceInstance.items}" var="i">
						<span class="property-value" aria-labelledby="items-label"><g:link controller="salesInvoiceItem" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${salesInvoiceInstance?.postDate}">
				<li class="fieldcontain">
					<span id="postDate-label" class="property-label"><g:message code="salesInvoice.postDate.label" default="Post Date" /></span>
					
						<span class="property-value" aria-labelledby="postDate-label"><g:formatDate date="${salesInvoiceInstance?.postDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${salesInvoiceInstance?.postedBy}">
				<li class="fieldcontain">
					<span id="postedBy-label" class="property-label"><g:message code="salesInvoice.postedBy.label" default="Posted By" /></span>
					
						<span class="property-value" aria-labelledby="postedBy-label"><g:fieldValue bean="${salesInvoiceInstance}" field="postedBy"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${salesInvoiceInstance?.pricingScheme}">
				<li class="fieldcontain">
					<span id="pricingScheme-label" class="property-label"><g:message code="salesInvoice.pricingScheme.label" default="Pricing Scheme" /></span>
					
						<span class="property-value" aria-labelledby="pricingScheme-label"><g:link controller="pricingScheme" action="show" id="${salesInvoiceInstance?.pricingScheme?.id}">${salesInvoiceInstance?.pricingScheme?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${salesInvoiceInstance?.id}" />
					<g:link class="edit" action="edit" id="${salesInvoiceInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
