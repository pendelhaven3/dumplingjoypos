

<%@ page import="com.dumplingjoy.pos.PricingScheme"%>
<%@ page import="com.dumplingjoy.pos.SalesRequisition" %>
<%@ page import="com.dumplingjoy.pos.Customer" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'salesRequisition.label', default: 'SalesRequisition')}" />
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
            <g:hasErrors bean="${salesRequisitionInstance}">
            <div class="errors">
            	<g:each var="error" in="${salesRequisitionInstance.errors.globalErrors}">
            		<ul><li><g:message error="${error}" /></li></ul>
            	</g:each>
                <g:renderErrors bean="${salesRequisitionInstance}" field="customer" />
                <g:renderErrors bean="${salesRequisitionInstance}" field="pricingScheme" />
                <g:renderErrors bean="${salesRequisitionInstance}" field="mode" />
                <g:renderErrors bean="${salesRequisitionInstance}" field="paymentTerms" />
                <g:renderErrors bean="${salesRequisitionInstance}" field="remarks" />
            </div>
            </g:hasErrors>
            <g:form action="save" autocomplete="off">
            	<g:hiddenField name="salesRequisition.id" value="${salesRequisitionInstance.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="salesRequisitionNumber"><g:message code="salesRequisition.salesRequisitionNumber.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: salesRequisitionInstance, field: 'salesRequisitionNumber', 'errors')}">
                                	To Be Assigned
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="customer"><g:message code="salesRequisition.customer.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: salesRequisitionInstance, field: 'customer', 'errors')}">
                                	<g:select name="customer.id" from="${Customer.list([sort: "name", order: "asc"])}" value="${salesRequisitionInstance.customer?.id}" 
                                		optionKey="id" optionValue="name" noSelection="['':'']" onblur="updatePricingScheme()" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="pricingScheme"><g:message code="salesRequisition.pricingScheme.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: salesRequisitionInstance, field: 'pricingScheme', 'errors')}">
                                	<g:select name="pricingScheme.id" from="${com.dumplingjoy.pos.PricingScheme.list([sort: "description", order: "asc"])}" value="${salesRequisitionInstance.pricingScheme?.id}" 
                                		optionKey="id" optionValue="description" noSelection="['':'']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="mode"><g:message code="salesRequisition.mode.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: salesRequisitionInstance, field: 'mode', 'errors')}">
                                	<g:select name="mode" from="${SalesRequisition.constraints.mode.inList}" value="${salesRequisitionInstance.mode}" 
                                		noSelection="['':'']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="paymentTerms"><g:message code="salesRequisition.paymentTerms.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: salesRequisitionInstance, field: 'paymentTerms', 'errors')}">
                                	<g:select name="paymentTerms.id" from="${com.dumplingjoy.pos.PaymentTerms.list([sort: "name", order: "asc"])}" value="${salesRequisitionInstance.paymentTerms?.id}" 
                                		optionKey="id" optionValue="name" noSelection="['':'']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remarks"><g:message code="salesRequisition.remarks.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: salesRequisitionInstance, field: 'remarks', 'errors')}">
                                    <g:textField name="remarks" value="${fieldValue(bean: salesRequisitionInstance, field: 'remarks')}" style="width:300px" />
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
        	focusOnLoad("customer\\.id")
        </g:javascript>
    </body>
</html>