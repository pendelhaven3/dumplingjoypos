
<%@ page import="com.dumplingjoy.pos.PricingScheme" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'pricingScheme.label', default: 'PricingScheme')}" />
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
                        <th><g:message code="pricingScheme.description.label" /></th>
                    </tr>
                    <g:if test="${!pricingSchemeInstanceList.empty}">
	                    <g:each in="${pricingSchemeInstanceList}" status="i" var="pricingSchemeInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'} clickable" onclick="window.location='<g:createLink action='show' id='${pricingSchemeInstance.id}' />'">
	                            <td>${fieldValue(bean: pricingSchemeInstance, field: "description")}</td>
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
                <g:paginate total="${pricingSchemeInstanceTotal}" />
            </div>
        </div>
    </body>
</html>