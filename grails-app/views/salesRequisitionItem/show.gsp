
<%@ page import="com.dumplingjoy.pos.SalesRequisitionItem" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-salesRequisitionItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-salesRequisitionItem" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list salesRequisitionItem">
			
				<g:if test="${salesRequisitionItemInstance?.quantity}">
				<li class="fieldcontain">
					<span id="quantity-label" class="property-label"><g:message code="salesRequisitionItem.quantity.label" default="Quantity" /></span>
					
						<span class="property-value" aria-labelledby="quantity-label"><g:fieldValue bean="${salesRequisitionItemInstance}" field="quantity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${salesRequisitionItemInstance?.product}">
				<li class="fieldcontain">
					<span id="product-label" class="property-label"><g:message code="salesRequisitionItem.product.label" default="Product" /></span>
					
						<span class="property-value" aria-labelledby="product-label"><g:link controller="product" action="show" id="${salesRequisitionItemInstance?.product?.id}">${salesRequisitionItemInstance?.product?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${salesRequisitionItemInstance?.salesRequisition}">
				<li class="fieldcontain">
					<span id="salesRequisition-label" class="property-label"><g:message code="salesRequisitionItem.salesRequisition.label" default="Sales Requisition" /></span>
					
						<span class="property-value" aria-labelledby="salesRequisition-label"><g:link controller="salesRequisition" action="show" id="${salesRequisitionItemInstance?.salesRequisition?.id}">${salesRequisitionItemInstance?.salesRequisition?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${salesRequisitionItemInstance?.unit}">
				<li class="fieldcontain">
					<span id="unit-label" class="property-label"><g:message code="salesRequisitionItem.unit.label" default="Unit" /></span>
					
						<span class="property-value" aria-labelledby="unit-label"><g:fieldValue bean="${salesRequisitionItemInstance}" field="unit"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${salesRequisitionItemInstance?.id}" />
					<g:link class="edit" action="edit" id="${salesRequisitionItemInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
