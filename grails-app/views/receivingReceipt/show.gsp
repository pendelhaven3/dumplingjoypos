
<%@ page import="com.dumplingjoy.pos.ReceivingReceipt" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'receivingReceipt.label', default: 'ReceivingReceipt')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-receivingReceipt" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-receivingReceipt" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list receivingReceipt">
			
				<g:if test="${receivingReceiptInstance?.receivingReceiptNumber}">
				<li class="fieldcontain">
					<span id="receivingReceiptNumber-label" class="property-label"><g:message code="receivingReceipt.receivingReceiptNumber.label" default="Receiving Receipt Number" /></span>
					
						<span class="property-value" aria-labelledby="receivingReceiptNumber-label"><g:fieldValue bean="${receivingReceiptInstance}" field="receivingReceiptNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${receivingReceiptInstance?.items}">
				<li class="fieldcontain">
					<span id="items-label" class="property-label"><g:message code="receivingReceipt.items.label" default="Items" /></span>
					
						<g:each in="${receivingReceiptInstance.items}" var="i">
						<span class="property-value" aria-labelledby="items-label"><g:link controller="receivingReceiptItem" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${receivingReceiptInstance?.receivedDate}">
				<li class="fieldcontain">
					<span id="receivedDate-label" class="property-label"><g:message code="receivingReceipt.receivedDate.label" default="Received Date" /></span>
					
						<span class="property-value" aria-labelledby="receivedDate-label"><g:formatDate date="${receivingReceiptInstance?.receivedDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${receivingReceiptInstance?.supplier}">
				<li class="fieldcontain">
					<span id="supplier-label" class="property-label"><g:message code="receivingReceipt.supplier.label" default="Supplier" /></span>
					
						<span class="property-value" aria-labelledby="supplier-label"><g:link controller="supplier" action="show" id="${receivingReceiptInstance?.supplier?.id}">${receivingReceiptInstance?.supplier?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${receivingReceiptInstance?.id}" />
					<g:link class="edit" action="edit" id="${receivingReceiptInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
