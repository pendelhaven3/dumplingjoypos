
<%@ page import="com.dumplingjoy.pos.PurchaseOrder" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'purchaseOrder.label')}" />
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
                        <th width="150"><g:message code="purchaseOrder.purchaseOrderNumber.label" /></th>
                        <th><g:message code="purchaseOrder.supplier.label" /></th>
                        <th>Status</th>
                    </tr>
                    <g:if test="${!purchaseOrderInstanceList.empty}">
	                    <g:each in="${purchaseOrderInstanceList}" status="i" var="purchaseOrderInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'} clickable" onclick="window.location='<g:createLink action='show' id='${purchaseOrderInstance.id}' />'">
	                        
	                            <td>${fieldValue(bean: purchaseOrderInstance, field: "purchaseOrderNumber")}</td>
	                            <td>${fieldValue(bean: purchaseOrderInstance, field: "supplier.name")}</td>
	                            <td>${fieldValue(bean: purchaseOrderInstance, field: "status")}</td>
	                        </tr>
	                    </g:each>
                    </g:if>
                    <g:else>
                    	<tr>
                    		<td colspan="3">No records found</td>
                    	</tr>
                    </g:else>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${purchaseOrderInstanceTotal}" />
            </div>
        </div>
    </body>
</html>