
<%@ page import="com.dumplingjoy.pos.SalesRequisition" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'salesRequisition.label', default: 'SalesRequisition')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <tr>
                        <th width="150"><g:message code="salesRequisition.salesRequisitionNumber.label" /></th>
                        <th><g:message code="salesRequisition.customer.label" /></th>
                        <th><g:message code="salesRequisition.posted.label" /></th>
                    </tr>
                    <g:if test="${!salesRequisitionInstanceList.empty}">
	                    <g:each in="${salesRequisitionInstanceList}" status="i" var="salesRequisitionInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'} clickable" onclick="window.location='<g:createLink action='show' id='${salesRequisitionInstance.id}' />'">
	                            <td>${fieldValue(bean: salesRequisitionInstance, field: "salesRequisitionNumber")}</td>
	                            <td>${fieldValue(bean: salesRequisitionInstance, field: "customer.name")}</td>
	                            <td>${salesRequisitionInstance.posted ? "Yes" : "No"}</td>
	                        </tr>
	                    </g:each>
                    </g:if>
                    <g:else>
                    	<tr>
                    		<td colspan="2">No records found</td>
                    	</tr>
                    </g:else>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${salesRequisitionInstanceTotal}" />
            </div>
        </div>
    </body>
</html>