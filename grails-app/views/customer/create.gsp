

<%@ page import="com.dumplingjoy.pos.Customer" %>
<%@ page import="com.dumplingjoy.pos.PaymentTerms" %>
<%@ page import="com.dumplingjoy.pos.PricingScheme" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'customer.label', default: 'Customer')}" />
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
            <g:hasErrors bean="${customerInstance}">
            <div class="errors">
                <g:renderErrors bean="${customerInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" autocomplete="off">
            	<g:hiddenField name="customer.id" value="${customerInstance.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="customer.code.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: customerInstance, field: 'code', 'errors')}">
                                    <g:textField name="code" value="${fieldValue(bean: customerInstance, field: 'code')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="customer.name.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: customerInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${fieldValue(bean: customerInstance, field: 'name')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="contactPerson"><g:message code="customer.contactPerson.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: customerInstance, field: 'contactPerson', 'errors')}">
                                    <g:textField name="contactPerson" value="${fieldValue(bean: customerInstance, field: 'contactPerson')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="contactNumbers"><g:message code="customer.contactNumbers.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: customerInstance, field: 'contactNumbers', 'errors')}">
                                    <g:textField name="contactNumbers" value="${fieldValue(bean: customerInstance, field: 'contactNumbers')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="faxNumber"><g:message code="customer.faxNumber.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: customerInstance, field: 'faxNumber', 'errors')}">
                                    <g:textField name="faxNumber" value="${fieldValue(bean: customerInstance, field: 'faxNumber')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address"><g:message code="customer.address.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: customerInstance, field: 'address', 'errors')}">
                                    <g:textArea name="address" value="${fieldValue(bean: customerInstance, field: 'address')}" rows="3" cols="40" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="paymentMode"><g:message code="customer.paymentMode.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: customerInstance, field: 'paymentMode', 'errors')}">
                                	<g:radio name="paymentMode" value="COD" checked="${customerInstance.paymentMode.equals('COD')}" />&nbsp;COD
                                	<g:radio name="paymentMode" value="Check" checked="${customerInstance.paymentMode.equals('Check')}" />&nbsp;Check
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="terms"><g:message code="customer.terms.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: customerInstance, field: 'terms', 'errors')}">
                                	<g:select name="terms.id" from="${PaymentTerms.list([sort: "name", order: "asc"])}" value="${customerInstance.terms?.id}" 
                                		optionKey="id" optionValue="name" noSelection="['':'']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                	Check Details
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <label for="bank"><g:message code="customer.bank.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: customerInstance, field: 'bank', 'errors')}">
                                    <g:textField name="bank" value="${fieldValue(bean: customerInstance, field: 'bank')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <label for="branch"><g:message code="customer.branch.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: customerInstance, field: 'branch', 'errors')}">
                                    <g:textField name="branch" value="${fieldValue(bean: customerInstance, field: 'branch')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <label for="creditLine"><g:message code="customer.creditLine.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: customerInstance, field: 'creditLine', 'errors')}">
                                    <g:textField name="creditLine" value="${fieldValue(bean: customerInstance, field: 'creditLine')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remarks"><g:message code="customer.remarks.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: customerInstance, field: 'remarks', 'errors')}">
                                    <g:textArea name="remarks" value="${fieldValue(bean: customerInstance, field: 'remarks')}" rows="3" cols="40" />
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
        	focusOnLoad("code")
        </g:javascript>
    </body>
</html>