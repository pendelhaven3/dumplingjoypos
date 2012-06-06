
<%@ page import="com.dumplingjoy.pos.PurchaseOrderItem" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-purchaseOrderItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-purchaseOrderItem" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="quantity" title="${message(code: 'purchaseOrderItem.quantity.label', default: 'Quantity')}" />
					
						<g:sortableColumn property="cost" title="${message(code: 'purchaseOrderItem.cost.label', default: 'Cost')}" />
					
						<th><g:message code="purchaseOrderItem.product.label" default="Product" /></th>
					
						<g:sortableColumn property="unit" title="${message(code: 'purchaseOrderItem.unit.label', default: 'Unit')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${purchaseOrderItemInstanceList}" status="i" var="purchaseOrderItemInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${purchaseOrderItemInstance.id}">${fieldValue(bean: purchaseOrderItemInstance, field: "quantity")}</g:link></td>
					
						<td>${fieldValue(bean: purchaseOrderItemInstance, field: "cost")}</td>
					
						<td>${fieldValue(bean: purchaseOrderItemInstance, field: "product")}</td>
					
						<td>${fieldValue(bean: purchaseOrderItemInstance, field: "unit")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${purchaseOrderItemInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
