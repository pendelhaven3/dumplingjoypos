
<%@ page import="com.dumplingjoy.pos.AdjustmentOutItem" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'adjustmentOutItem.label', default: 'AdjustmentOutItem')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-adjustmentOutItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-adjustmentOutItem" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="adjustmentOutItem.product.label" default="Product" /></th>
					
						<g:sortableColumn property="unit" title="${message(code: 'adjustmentOutItem.unit.label', default: 'Unit')}" />
					
						<g:sortableColumn property="quantity" title="${message(code: 'adjustmentOutItem.quantity.label', default: 'Quantity')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${adjustmentOutItemInstanceList}" status="i" var="adjustmentOutItemInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${adjustmentOutItemInstance.id}">${fieldValue(bean: adjustmentOutItemInstance, field: "product")}</g:link></td>
					
						<td>${fieldValue(bean: adjustmentOutItemInstance, field: "unit")}</td>
					
						<td>${fieldValue(bean: adjustmentOutItemInstance, field: "quantity")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${adjustmentOutItemInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
