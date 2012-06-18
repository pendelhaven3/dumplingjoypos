
<%@ page import="com.dumplingjoy.pos.PaymentTerms" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'paymentTerms.label', default: 'PaymentTerms')}" />
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
                        <th><g:message code="paymentTerms.name.label" /></th>
                        <th><g:message code="paymentTerms.numberOfDays.label" /></th>
                    </tr>
                    <g:if test="${!paymentTermsInstanceList.empty}">
	                    <g:each in="${paymentTermsInstanceList}" status="i" var="paymentTermsInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'} clickable" onclick="window.location='<g:createLink action='show' id='${paymentTermsInstance.id}' />'">
	                            <td>${fieldValue(bean: paymentTermsInstance, field: "name")}</td>
	                            <td>${fieldValue(bean: paymentTermsInstance, field: "numberOfDays")}</td>
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
                <g:paginate total="${paymentTermsInstanceTotal}" />
            </div>
        </div>
    </body>
</html>