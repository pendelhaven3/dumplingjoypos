

<%@ page import="com.dumplingjoy.pos.PurchaseOrder" %>
<%@ page import="com.dumplingjoy.pos.Supplier" %>
<%@ page import="com.dumplingjoy.pos.PaymentTerms" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'purchaseOrder.label', default: 'PurchaseOrder')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${purchaseOrderInstance}">
            <div class="errors">
            	<g:each var="error" in="${purchaseOrderInstance.errors.globalErrors}">
            		<ul><li><g:message error="${error}" /></li></ul>
            	</g:each>
                <g:renderErrors bean="${purchaseOrderInstance}" field="supplier" />
                <g:renderErrors bean="${purchaseOrderInstance}" field="terms" />
           </div>
            </g:hasErrors>
            <g:form action="save" autocomplete="off">
            	<g:hiddenField name="purchaseOrder.id" value="${purchaseOrderInstance.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="purchaseOrderNumber"><g:message code="purchaseOrder.purchaseOrderNumber.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseOrderInstance, field: 'purchaseOrderNumber', 'errors')}">
                                	To Be Assigned
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="supplier"><g:message code="purchaseOrder.supplier.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseOrderInstance, field: 'supplier', 'errors')}">
                                	<g:select name="supplier.id" from="${Supplier.list([sort: "name", order: "asc"])}" value="${purchaseOrderInstance.supplier?.id}" 
                                		optionKey="id" optionValue="name" noSelection="['':'']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="terms"><g:message code="purchaseOrder.terms.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseOrderInstance, field: 'terms', 'errors')}">
                                	<g:select name="terms.id" from="${PaymentTerms.list([sort: "name", order: "asc"])}" value="${purchaseOrderInstance.terms?.id}" 
                                		optionKey="id" optionValue="name" noSelection="['':'']" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
		        	<span class="button">
		        		<input type="button" value="Cancel" class="cancel" 
		        			onclick="window.location='<g:createLink action='list' />'" />
		        	</span>
                </div>
            </g:form>
        </div>
        <g:javascript>
        	focusOnLoad("supplier\\.id")
        </g:javascript>
    </body>
</html>