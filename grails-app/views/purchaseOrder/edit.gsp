

<%@ page import="com.dumplingjoy.pos.PurchaseOrder" %>
<%@ page import="com.dumplingjoy.pos.Supplier" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'purchaseOrder.label', default: 'PurchaseOrder')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${purchaseOrderInstance}">
            <div class="errors">
            	<g:each var="error" in="${purchaseOrderInstance.errors.globalErrors}">
            		<ul><li><g:message error="${error}" /></li></ul>
            	</g:each>
                <g:renderErrors bean="${purchaseOrderInstance}" field="version" />
                <g:renderErrors bean="${purchaseOrderInstance}" field="supplier" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${purchaseOrderInstance?.id}" />
                <g:hiddenField name="version" value="${purchaseOrderInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
	                            <td valign="top" class="name"><g:message code="purchaseOrder.purchaseOrderNumber.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: purchaseOrderInstance, field: "purchaseOrderNumber")}</td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="supplier"><g:message code="purchaseOrder.supplier.label" /></label>
                                </td>
                                <td valign="top" class="value">
                                	<g:select name="supplier.id" from="${Supplier.list([sort: "name", order: "asc"])}" value="${purchaseOrderInstance.supplier?.id}"
                                		noSelection="['':'']" optionKey="id" optionValue="name" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
		        	<span class="button">
		        		<input type="button" value="Cancel" class="cancel" 
		        			onclick="window.location='<g:createLink controller='purchaseOrder' action='show' id='${purchaseOrderInstance?.id}' />'" />
		        	</span>
                </div>
            </g:form>
        </div>
        <g:javascript>
        	focusOnLoad("supplier\\.id")
        </g:javascript>
    </body>
</html>