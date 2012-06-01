
<%@ page import="com.dumplingjoy.pos.SalesRequisitionItem" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-salesRequisitionItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-salesRequisitionItem" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="quantity" title="${message(code: 'salesRequisitionItem.quantity.label', default: 'Quantity')}" />
					
						<th><g:message code="salesRequisitionItem.product.label" default="Product" /></th>
					
						<th><g:message code="salesRequisitionItem.salesRequisition.label" default="Sales Requisition" /></th>
					
						<g:sortableColumn property="unit" title="${message(code: 'salesRequisitionItem.unit.label', default: 'Unit')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${salesRequisitionItemInstanceList}" status="i" var="salesRequisitionItemInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${salesRequisitionItemInstance.id}">${fieldValue(bean: salesRequisitionItemInstance, field: "quantity")}</g:link></td>
					
						<td>${fieldValue(bean: salesRequisitionItemInstance, field: "product")}</td>
					
						<td>${fieldValue(bean: salesRequisitionItemInstance, field: "salesRequisition")}</td>
					
						<td>${fieldValue(bean: salesRequisitionItemInstance, field: "unit")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${salesRequisitionItemInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
