
<%@ page import="com.dumplingjoy.pos.ReceivingReceipt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'receivingReceipt.label', default: 'ReceivingReceipt')}" />
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
                        <th width="150"><g:message code="receivingReceipt.receivingReceiptNumber.label" /></th>
                        <th><g:message code="receivingReceipt.supplier.label" /></th>
                        <th width="120"><g:message code="receivingReceipt.receivedDate.label" /></th>
                        <th width="150"><g:message code="receivingReceipt.receivedBy.label" /></th>
                        <th width="120">Status</th>
                    </tr>
                    <g:if test="${!receivingReceiptInstanceList.empty}">
	                    <g:each in="${receivingReceiptInstanceList}" status="i" var="receivingReceiptInstance">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'} clickable" onclick="window.location='<g:createLink action='show' id='${receivingReceiptInstance.id}' />'">
	                        
	                            <td>${fieldValue(bean: receivingReceiptInstance, field: "receivingReceiptNumber")}</td>
	                            <td>${fieldValue(bean: receivingReceiptInstance, field: "supplier.name")}</td>
	                            <td><g:formatDate date="${receivingReceiptInstance.receivedDate}" format="MM/dd/yyyy" /></td>
	                            <td>${fieldValue(bean: receivingReceiptInstance, field: "receivedBy")}</td>
	                            <td>${receivingReceiptInstance.posted ? "Posted" : (receivingReceiptInstance.cancelled ? "Cancelled" : "New")}</td>
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
                <g:paginate total="${receivingReceiptInstanceTotal}" />
            </div>
        </div>
    </body>
</html>