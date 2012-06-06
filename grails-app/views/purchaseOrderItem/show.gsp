
<%@ page import="com.dumplingjoy.pos.PurchaseOrderItem" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-purchaseOrderItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-purchaseOrderItem" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list purchaseOrderItem">
			
				<g:if test="${purchaseOrderItemInstance?.quantity}">
				<li class="fieldcontain">
					<span id="quantity-label" class="property-label"><g:message code="purchaseOrderItem.quantity.label" default="Quantity" /></span>
					
						<span class="property-value" aria-labelledby="quantity-label"><g:fieldValue bean="${purchaseOrderItemInstance}" field="quantity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${purchaseOrderItemInstance?.cost}">
				<li class="fieldcontain">
					<span id="cost-label" class="property-label"><g:message code="purchaseOrderItem.cost.label" default="Cost" /></span>
					
						<span class="property-value" aria-labelledby="cost-label"><g:fieldValue bean="${purchaseOrderItemInstance}" field="cost"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${purchaseOrderItemInstance?.product}">
				<li class="fieldcontain">
					<span id="product-label" class="property-label"><g:message code="purchaseOrderItem.product.label" default="Product" /></span>
					
						<span class="property-value" aria-labelledby="product-label"><g:link controller="product" action="show" id="${purchaseOrderItemInstance?.product?.id}">${purchaseOrderItemInstance?.product?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${purchaseOrderItemInstance?.unit}">
				<li class="fieldcontain">
					<span id="unit-label" class="property-label"><g:message code="purchaseOrderItem.unit.label" default="Unit" /></span>
					
						<span class="property-value" aria-labelledby="unit-label"><g:fieldValue bean="${purchaseOrderItemInstance}" field="unit"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${purchaseOrderItemInstance?.id}" />
					<g:link class="edit" action="edit" id="${purchaseOrderItemInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
