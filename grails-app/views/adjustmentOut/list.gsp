
<%@ page import="com.dumplingjoy.pos.AdjustmentOut" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'adjustmentOut.label', default: 'AdjustmentOut')}" />
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
                        <th width="150"><g:message code="adjustmentOut.adjustmentOutNumber.label" /></th>
                        <th><g:message code="adjustmentOut.description.label" /></th>
                        <th width="120"><g:message code="adjustmentOut.posted.label" /></th>
                        <th width="120"><g:message code="adjustmentOut.postDate.label" /></th>
                        <th width="150"><g:message code="adjustmentOut.postedBy.label" /></th>
                    </tr>
                    <g:if test="${!adjustmentOutInstanceList.empty}">
	                    <g:each in="${adjustmentOutInstanceList}" status="i" var="adjustmentOutInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'} clickable" onclick="window.location='<g:createLink action='show' id='${adjustmentOutInstance.id}' />'">
	                        
	                            <td>${fieldValue(bean: adjustmentOutInstance, field: "adjustmentOutNumber")}</td>
	                            <td>${fieldValue(bean: adjustmentOutInstance, field: "description")}</td>
	                            <td>${adjustmentOutInstance.posted ? "Yes" : "No"}</td>
	                            <td><g:formatDate date="${adjustmentOutInstance.postDate}" format="MM/dd/yyyy" /></td>
	                            <td>${fieldValue(bean: adjustmentOutInstance, field: "postedBy")}</td>
	                        </tr>
	                    </g:each>
                    </g:if>
                    <g:else>
                    	<tr>
                    		<td colspan="5">No records found</td>
                    	</tr>
                    </g:else>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${adjustmentOutInstanceTotal}" />
            </div>
        </div>
    </body>
</html>