
<%@ page import="com.dumplingjoy.pos.ReceivingReceiptItem" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'receivingReceiptItem.label', default: 'ReceivingReceiptItem')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-receivingReceiptItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-receivingReceiptItem" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="quantity" title="${message(code: 'receivingReceiptItem.quantity.label', default: 'Quantity')}" />
					
						<g:sortableColumn property="discount1" title="${message(code: 'receivingReceiptItem.discount1.label', default: 'Discount1')}" />
					
						<g:sortableColumn property="discount2" title="${message(code: 'receivingReceiptItem.discount2.label', default: 'Discount2')}" />
					
						<g:sortableColumn property="discount3" title="${message(code: 'receivingReceiptItem.discount3.label', default: 'Discount3')}" />
					
						<g:sortableColumn property="flatRateDiscount" title="${message(code: 'receivingReceiptItem.flatRateDiscount.label', default: 'Flat Rate Discount')}" />
					
						<g:sortableColumn property="cost" title="${message(code: 'receivingReceiptItem.cost.label', default: 'Cost')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${receivingReceiptItemInstanceList}" status="i" var="receivingReceiptItemInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${receivingReceiptItemInstance.id}">${fieldValue(bean: receivingReceiptItemInstance, field: "quantity")}</g:link></td>
					
						<td>${fieldValue(bean: receivingReceiptItemInstance, field: "discount1")}</td>
					
						<td>${fieldValue(bean: receivingReceiptItemInstance, field: "discount2")}</td>
					
						<td>${fieldValue(bean: receivingReceiptItemInstance, field: "discount3")}</td>
					
						<td>${fieldValue(bean: receivingReceiptItemInstance, field: "flatRateDiscount")}</td>
					
						<td>${fieldValue(bean: receivingReceiptItemInstance, field: "cost")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${receivingReceiptItemInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
