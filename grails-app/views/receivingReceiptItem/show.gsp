
<%@ page import="com.dumplingjoy.pos.ReceivingReceiptItem" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'receivingReceiptItem.label', default: 'ReceivingReceiptItem')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-receivingReceiptItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-receivingReceiptItem" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list receivingReceiptItem">
			
				<g:if test="${receivingReceiptItemInstance?.quantity}">
				<li class="fieldcontain">
					<span id="quantity-label" class="property-label"><g:message code="receivingReceiptItem.quantity.label" default="Quantity" /></span>
					
						<span class="property-value" aria-labelledby="quantity-label"><g:fieldValue bean="${receivingReceiptItemInstance}" field="quantity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${receivingReceiptItemInstance?.discount1}">
				<li class="fieldcontain">
					<span id="discount1-label" class="property-label"><g:message code="receivingReceiptItem.discount1.label" default="Discount1" /></span>
					
						<span class="property-value" aria-labelledby="discount1-label"><g:fieldValue bean="${receivingReceiptItemInstance}" field="discount1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${receivingReceiptItemInstance?.discount2}">
				<li class="fieldcontain">
					<span id="discount2-label" class="property-label"><g:message code="receivingReceiptItem.discount2.label" default="Discount2" /></span>
					
						<span class="property-value" aria-labelledby="discount2-label"><g:fieldValue bean="${receivingReceiptItemInstance}" field="discount2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${receivingReceiptItemInstance?.discount3}">
				<li class="fieldcontain">
					<span id="discount3-label" class="property-label"><g:message code="receivingReceiptItem.discount3.label" default="Discount3" /></span>
					
						<span class="property-value" aria-labelledby="discount3-label"><g:fieldValue bean="${receivingReceiptItemInstance}" field="discount3"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${receivingReceiptItemInstance?.flatRateDiscount}">
				<li class="fieldcontain">
					<span id="flatRateDiscount-label" class="property-label"><g:message code="receivingReceiptItem.flatRateDiscount.label" default="Flat Rate Discount" /></span>
					
						<span class="property-value" aria-labelledby="flatRateDiscount-label"><g:fieldValue bean="${receivingReceiptItemInstance}" field="flatRateDiscount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${receivingReceiptItemInstance?.cost}">
				<li class="fieldcontain">
					<span id="cost-label" class="property-label"><g:message code="receivingReceiptItem.cost.label" default="Cost" /></span>
					
						<span class="property-value" aria-labelledby="cost-label"><g:fieldValue bean="${receivingReceiptItemInstance}" field="cost"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${receivingReceiptItemInstance?.product}">
				<li class="fieldcontain">
					<span id="product-label" class="property-label"><g:message code="receivingReceiptItem.product.label" default="Product" /></span>
					
						<span class="property-value" aria-labelledby="product-label"><g:link controller="product" action="show" id="${receivingReceiptItemInstance?.product?.id}">${receivingReceiptItemInstance?.product?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${receivingReceiptItemInstance?.receivingReceipt}">
				<li class="fieldcontain">
					<span id="receivingReceipt-label" class="property-label"><g:message code="receivingReceiptItem.receivingReceipt.label" default="Receiving Receipt" /></span>
					
						<span class="property-value" aria-labelledby="receivingReceipt-label"><g:link controller="receivingReceipt" action="show" id="${receivingReceiptItemInstance?.receivingReceipt?.id}">${receivingReceiptItemInstance?.receivingReceipt?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${receivingReceiptItemInstance?.unit}">
				<li class="fieldcontain">
					<span id="unit-label" class="property-label"><g:message code="receivingReceiptItem.unit.label" default="Unit" /></span>
					
						<span class="property-value" aria-labelledby="unit-label"><g:fieldValue bean="${receivingReceiptItemInstance}" field="unit"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${receivingReceiptItemInstance?.id}" />
					<g:link class="edit" action="edit" id="${receivingReceiptItemInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
