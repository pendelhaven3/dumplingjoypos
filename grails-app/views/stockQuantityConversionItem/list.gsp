
<%@ page import="com.dumplingjoy.pos.StockQuantityConversionItem" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-stockQuantityConversionItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-stockQuantityConversionItem" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="quantity" title="${message(code: 'stockQuantityConversionItem.quantity.label', default: 'Quantity')}" />
					
						<g:sortableColumn property="fromUnit" title="${message(code: 'stockQuantityConversionItem.fromUnit.label', default: 'From Unit')}" />
					
						<th><g:message code="stockQuantityConversionItem.product.label" default="Product" /></th>
					
						<g:sortableColumn property="toUnit" title="${message(code: 'stockQuantityConversionItem.toUnit.label', default: 'To Unit')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${stockQuantityConversionItemInstanceList}" status="i" var="stockQuantityConversionItemInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${stockQuantityConversionItemInstance.id}">${fieldValue(bean: stockQuantityConversionItemInstance, field: "quantity")}</g:link></td>
					
						<td>${fieldValue(bean: stockQuantityConversionItemInstance, field: "fromUnit")}</td>
					
						<td>${fieldValue(bean: stockQuantityConversionItemInstance, field: "product")}</td>
					
						<td>${fieldValue(bean: stockQuantityConversionItemInstance, field: "toUnit")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${stockQuantityConversionItemInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
