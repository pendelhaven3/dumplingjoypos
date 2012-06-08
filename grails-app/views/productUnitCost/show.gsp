
<%@ page import="com.dumplingjoy.pos.ProductUnitCost" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'productUnitCost.label', default: 'ProductUnitCost')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-productUnitCost" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-productUnitCost" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list productUnitCost">
			
				<g:if test="${productUnitCostInstance?.grossCost}">
				<li class="fieldcontain">
					<span id="grossCost-label" class="property-label"><g:message code="productUnitCost.grossCost.label" default="Gross Cost" /></span>
					
						<span class="property-value" aria-labelledby="grossCost-label"><g:fieldValue bean="${productUnitCostInstance}" field="grossCost"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productUnitCostInstance?.finalCost}">
				<li class="fieldcontain">
					<span id="finalCost-label" class="property-label"><g:message code="productUnitCost.finalCost.label" default="Final Cost" /></span>
					
						<span class="property-value" aria-labelledby="finalCost-label"><g:fieldValue bean="${productUnitCostInstance}" field="finalCost"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productUnitCostInstance?.product}">
				<li class="fieldcontain">
					<span id="product-label" class="property-label"><g:message code="productUnitCost.product.label" default="Product" /></span>
					
						<span class="property-value" aria-labelledby="product-label"><g:link controller="product" action="show" id="${productUnitCostInstance?.product?.id}">${productUnitCostInstance?.product?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${productUnitCostInstance?.unit}">
				<li class="fieldcontain">
					<span id="unit-label" class="property-label"><g:message code="productUnitCost.unit.label" default="Unit" /></span>
					
						<span class="property-value" aria-labelledby="unit-label"><g:fieldValue bean="${productUnitCostInstance}" field="unit"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${productUnitCostInstance?.id}" />
					<g:link class="edit" action="edit" id="${productUnitCostInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
