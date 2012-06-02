
<%@ page import="com.dumplingjoy.pos.SalesInvoice" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'salesInvoice.label', default: 'SalesInvoice')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-salesInvoice" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-salesInvoice" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="salesInvoiceNumber" title="${message(code: 'salesInvoice.salesInvoiceNumber.label', default: 'Sales Invoice Number')}" />
					
						<th><g:message code="salesInvoice.customer.label" default="Customer" /></th>
					
						<g:sortableColumn property="postDate" title="${message(code: 'salesInvoice.postDate.label', default: 'Post Date')}" />
					
						<g:sortableColumn property="postedBy" title="${message(code: 'salesInvoice.postedBy.label', default: 'Posted By')}" />
					
						<th><g:message code="salesInvoice.pricingScheme.label" default="Pricing Scheme" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${salesInvoiceInstanceList}" status="i" var="salesInvoiceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${salesInvoiceInstance.id}">${fieldValue(bean: salesInvoiceInstance, field: "salesInvoiceNumber")}</g:link></td>
					
						<td>${fieldValue(bean: salesInvoiceInstance, field: "customer")}</td>
					
						<td><g:formatDate date="${salesInvoiceInstance.postDate}" /></td>
					
						<td>${fieldValue(bean: salesInvoiceInstance, field: "postedBy")}</td>
					
						<td>${fieldValue(bean: salesInvoiceInstance, field: "pricingScheme")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${salesInvoiceInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
