
<%@ page import="com.dumplingjoy.pos.UnitConversion" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'unitConversion.label', default: 'UnitConversion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-unitConversion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-unitConversion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="fromUnit" title="${message(code: 'unitConversion.fromUnit.label', default: 'From Unit')}" />
					
						<g:sortableColumn property="toUnit" title="${message(code: 'unitConversion.toUnit.label', default: 'To Unit')}" />
					
						<g:sortableColumn property="convertedQuantity" title="${message(code: 'unitConversion.convertedQuantity.label', default: 'Converted Quantity')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${unitConversionInstanceList}" status="i" var="unitConversionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${unitConversionInstance.id}">${fieldValue(bean: unitConversionInstance, field: "fromUnit")}</g:link></td>
					
						<td>${fieldValue(bean: unitConversionInstance, field: "toUnit")}</td>
					
						<td>${fieldValue(bean: unitConversionInstance, field: "convertedQuantity")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${unitConversionInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
