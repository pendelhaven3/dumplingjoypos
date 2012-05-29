
<%@ page import="com.dumplingjoy.pos.ProductUnitPrice" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'productUnitPrice.label', default: 'ProductUnitPrice')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-productUnitPrice" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-productUnitPrice" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="price" title="${message(code: 'productUnitPrice.price.label', default: 'Price')}" />
					
						<th><g:message code="productUnitPrice.product.label" default="Product" /></th>
					
						<g:sortableColumn property="unit" title="${message(code: 'productUnitPrice.unit.label', default: 'Unit')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${productUnitPriceInstanceList}" status="i" var="productUnitPriceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${productUnitPriceInstance.id}">${fieldValue(bean: productUnitPriceInstance, field: "price")}</g:link></td>
					
						<td>${fieldValue(bean: productUnitPriceInstance, field: "product")}</td>
					
						<td>${fieldValue(bean: productUnitPriceInstance, field: "unit")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${productUnitPriceInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
