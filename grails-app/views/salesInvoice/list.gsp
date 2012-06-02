
<%@ page import="com.dumplingjoy.pos.SalesInvoice" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'salesInvoice.label', default: 'SalesInvoice')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <tr>
                        <th width="150"><g:message code="salesInvoice.salesInvoiceNumber.label" /></th>
                        <th><g:message code="salesInvoice.customer.label" /></th>
                        <th width="120"><g:message code="salesInvoice.postDate.label" /></th>
                        <th width="150"><g:message code="salesInvoice.postedBy.label" /></th>
                    </tr>
                    <g:if test="${!salesInvoiceInstanceList.empty}">
	                    <g:each in="${salesInvoiceInstanceList}" status="i" var="salesInvoiceInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'} clickable" onclick="window.location='<g:createLink action='show' id='${salesInvoiceInstance.id}' />'">
	                        
	                            <td>${fieldValue(bean: salesInvoiceInstance, field: "salesInvoiceNumber")}</td>
	                            <td>${fieldValue(bean: salesInvoiceInstance, field: "customer.name")}</td>
	                            <td><g:formatDate date="${salesInvoiceInstance.postDate}" format="MM/dd/yyyy" /></td>
	                            <td>${fieldValue(bean: salesInvoiceInstance, field: "postedBy")}</td>
	                        </tr>
	                    </g:each>
                    </g:if>
                    <g:else>
                    	<tr>
                    		<td colspan="4">No records found</td>
                    	</tr>
                    </g:else>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${salesInvoiceInstanceTotal}" />
            </div>
        </div>
    </body>
</html>