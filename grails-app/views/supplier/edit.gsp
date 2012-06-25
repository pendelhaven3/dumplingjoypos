

<%@ page import="com.dumplingjoy.pos.Supplier" %>
<%@ page import="com.dumplingjoy.pos.PaymentTerms" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'supplier.label', default: 'Supplier')}" />
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
            <g:hasErrors bean="${supplierInstance}">
            <div class="errors">
                <g:renderErrors bean="${supplierInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" autocomplete="off">
                <g:hiddenField name="id" value="${supplierInstance?.id}" />
                <g:hiddenField name="version" value="${supplierInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="supplier.name.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: supplierInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${fieldValue(bean: supplierInstance, field: 'name')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address"><g:message code="supplier.address.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: supplierInstance, field: 'address', 'errors')}">
                                    <g:textArea name="address" value="${fieldValue(bean: supplierInstance, field: 'address')}" rows="3" cols="40" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="contactNumber"><g:message code="supplier.contactNumber.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: supplierInstance, field: 'contactNumber', 'errors')}">
                                    <g:textField name="contactNumber" value="${fieldValue(bean: supplierInstance, field: 'contactNumber')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="contactPerson"><g:message code="supplier.contactPerson.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: supplierInstance, field: 'contactPerson', 'errors')}">
                                    <g:textField name="contactPerson" value="${fieldValue(bean: supplierInstance, field: 'contactPerson')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="faxNumber"><g:message code="supplier.faxNumber.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: supplierInstance, field: 'faxNumber', 'errors')}">
                                    <g:textField name="faxNumber" value="${fieldValue(bean: supplierInstance, field: 'faxNumber')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="emailAddress"><g:message code="supplier.emailAddress.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: supplierInstance, field: 'emailAddress', 'errors')}">
                                    <g:textField name="emailAddress" value="${fieldValue(bean: supplierInstance, field: 'emailAddress')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tin"><g:message code="supplier.tin.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: supplierInstance, field: 'tin', 'errors')}">
                                    <g:textField name="tin" value="${fieldValue(bean: supplierInstance, field: 'tin')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="terms"><g:message code="supplier.terms.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: supplierInstance, field: 'terms', 'errors')}">
                                	<g:select name="terms.id" from="${PaymentTerms.list([sort: "name", order: "asc"])}" value="${supplierInstance.terms?.id}" 
                                		optionKey="id" optionValue="name" noSelection="['':'']" />
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
		        			onclick="window.location='<g:createLink controller='supplier' action='show' id='${supplierInstance?.id}' />'" />
		        	</span>
                </div>
            </g:form>
        </div>
        <g:javascript>
        	focusOnLoad("name")
        </g:javascript>
    </body>
</html>