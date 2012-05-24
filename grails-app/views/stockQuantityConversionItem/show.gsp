
<%@ page import="com.dumplingjoy.pos.StockQuantityConversionItem" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-stockQuantityConversionItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-stockQuantityConversionItem" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list stockQuantityConversionItem">
			
				<g:if test="${stockQuantityConversionItemInstance?.quantity}">
				<li class="fieldcontain">
					<span id="quantity-label" class="property-label"><g:message code="stockQuantityConversionItem.quantity.label" default="Quantity" /></span>
					
						<span class="property-value" aria-labelledby="quantity-label"><g:fieldValue bean="${stockQuantityConversionItemInstance}" field="quantity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${stockQuantityConversionItemInstance?.fromUnit}">
				<li class="fieldcontain">
					<span id="fromUnit-label" class="property-label"><g:message code="stockQuantityConversionItem.fromUnit.label" default="From Unit" /></span>
					
						<span class="property-value" aria-labelledby="fromUnit-label"><g:fieldValue bean="${stockQuantityConversionItemInstance}" field="fromUnit"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${stockQuantityConversionItemInstance?.product}">
				<li class="fieldcontain">
					<span id="product-label" class="property-label"><g:message code="stockQuantityConversionItem.product.label" default="Product" /></span>
					
						<span class="property-value" aria-labelledby="product-label"><g:link controller="product" action="show" id="${stockQuantityConversionItemInstance?.product?.id}">${stockQuantityConversionItemInstance?.product?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${stockQuantityConversionItemInstance?.toUnit}">
				<li class="fieldcontain">
					<span id="toUnit-label" class="property-label"><g:message code="stockQuantityConversionItem.toUnit.label" default="To Unit" /></span>
					
						<span class="property-value" aria-labelledby="toUnit-label"><g:fieldValue bean="${stockQuantityConversionItemInstance}" field="toUnit"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${stockQuantityConversionItemInstance?.id}" />
					<g:link class="edit" action="edit" id="${stockQuantityConversionItemInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
