
<%@ page import="com.dumplingjoy.pos.ReceivingReceipt" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'receivingReceipt.label', default: 'ReceivingReceipt')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-receivingReceipt" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-receivingReceipt" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="receivingReceiptNumber" title="${message(code: 'receivingReceipt.receivingReceiptNumber.label', default: 'Receiving Receipt Number')}" />
					
						<g:sortableColumn property="receivedDate" title="${message(code: 'receivingReceipt.receivedDate.label', default: 'Received Date')}" />
					
						<th><g:message code="receivingReceipt.supplier.label" default="Supplier" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${receivingReceiptInstanceList}" status="i" var="receivingReceiptInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${receivingReceiptInstance.id}">${fieldValue(bean: receivingReceiptInstance, field: "receivingReceiptNumber")}</g:link></td>
					
						<td><g:formatDate date="${receivingReceiptInstance.receivedDate}" /></td>
					
						<td>${fieldValue(bean: receivingReceiptInstance, field: "supplier")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${receivingReceiptInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
