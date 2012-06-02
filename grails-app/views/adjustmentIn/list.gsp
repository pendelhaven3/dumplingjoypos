
<%@ page import="com.dumplingjoy.pos.AdjustmentIn" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'adjustmentIn.label', default: 'AdjustmentIn')}" />
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
                        <th width="150"><g:message code="adjustmentIn.adjustmentInNumber.label" /></th>
                        <th><g:message code="adjustmentIn.remarks.label" /></th>
                        <th width="120"><g:message code="adjustmentIn.posted.label" /></th>
                        <th width="120"><g:message code="adjustmentIn.postDate.label" /></th>
                        <th width="150"><g:message code="adjustmentIn.postedBy.label" /></th>
                    </tr>
                    <g:if test="${!adjustmentInInstanceList.empty}">
	                    <g:each in="${adjustmentInInstanceList}" status="i" var="adjustmentInInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'} clickable" onclick="window.location='<g:createLink action='show' id='${adjustmentInInstance.id}' />'">
	                        
	                            <td>${fieldValue(bean: adjustmentInInstance, field: "adjustmentInNumber")}</td>
	                            <td>${fieldValue(bean: adjustmentInInstance, field: "remarks")}</td>
	                            <td>${adjustmentInInstance.posted ? "Yes" : "No"}</td>
	                            <td><g:formatDate date="${adjustmentInInstance.postDate}" format="MM/dd/yyyy" /></td>
	                            <td>${fieldValue(bean: adjustmentInInstance, field: "postedBy")}</td>
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
                <g:paginate total="${adjustmentInInstanceTotal}" />
            </div>
        </div>
    </body>
</html>s