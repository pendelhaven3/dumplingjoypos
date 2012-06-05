
<%@ page import="com.dumplingjoy.pos.Supplier" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'supplier.label', default: 'Supplier')}" />
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
                        <th><g:message code="supplier.name.label" /></th>
                    </tr>
                    <g:if test="${!supplierInstanceList.empty}">
	                    <g:each in="${supplierInstanceList}" status="i" var="supplierInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'} clickable" onclick="window.location='<g:createLink action='show' id='${supplierInstance.id}' />'">
	                            <td>${fieldValue(bean: supplierInstance, field: "name")}</td>
	                        </tr>
	                    </g:each>
                    </g:if>
                    <g:else>
                    	<tr>
                    		<td>No records found</td>
                    	</tr>
                    </g:else>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${supplierInstanceTotal}" />
            </div>
        </div>
    </body>
</html>