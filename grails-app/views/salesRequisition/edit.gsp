

<%@ page import="com.dumplingjoy.pos.PricingScheme"%>
<%@ page import="com.dumplingjoy.pos.SalesRequisition" %>
<%@ page import="com.dumplingjoy.pos.Customer" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'salesRequisition.label', default: 'SalesRequisition')}" />
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
            <g:hasErrors bean="${salesRequisitionInstance}">
            <div class="errors">
            	<g:each var="error" in="${salesRequisitionInstance.errors.globalErrors}">
            		<ul><li><g:message error="${error}" /></li></ul>
            	</g:each>
                <g:renderErrors bean="${salesRequisitionInstance}" field="version" />
                <g:renderErrors bean="${salesRequisitionInstance}" field="customer" />
                <g:renderErrors bean="${salesRequisitionInstance}" field="pricingScheme" />
                <g:renderErrors bean="${salesRequisitionInstance}" field="orderType" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${salesRequisitionInstance?.id}" />
                <g:hiddenField name="version" value="${salesRequisitionInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
	                            <td valign="top" class="name"><g:message code="salesRequisition.salesRequisitionNumber.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: salesRequisitionInstance, field: "salesRequisitionNumber")}</td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="customer"><g:message code="salesRequisition.customer.label" /></label>
                                </td>
                                <td valign="top" class="value">
                                	<g:select name="customer.id" from="${Customer.list([sort: "name", order: "asc"])}" value="${salesRequisitionInstance.customer?.id}"
                                		noSelection="['':'']" optionKey="id" optionValue="name" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="salesRequisition.pricingScheme.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: salesRequisitionInstance, field: 'pricingScheme', 'errors')}">
                                	<g:select name="pricingScheme.id" from="${com.dumplingjoy.pos.PricingScheme.list([sort: "description", order: "asc"])}" value="${salesRequisitionInstance.pricingScheme?.id}" 
                                		optionKey="id" optionValue="description" noSelection="['':'']" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="orderType"><g:message code="salesRequisition.orderType.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: salesRequisitionInstance, field: 'orderType', 'errors')}">
                                	<g:select name="orderType" from="${SalesRequisition.constraints.orderType.inList}" value="${salesRequisitionInstance.orderType}" 
                                		noSelection="['':'']" />
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
		        			onclick="window.location='<g:createLink controller='salesRequisition' action='show' id='${salesRequisitionInstance?.id}' />'" />
		        	</span>
                </div>
            </g:form>
        </div>
        <g:javascript>
        	focusOnLoad("customer\\.id")
        	
        	function updatePricingScheme() {
        		var customerId = $("#customer\\.id").val()
        		var pricingSchemeId = $("#pricingScheme.id").val()
        	}
        </g:javascript>
    </body>
</html>