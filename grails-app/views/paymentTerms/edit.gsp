

<%@ page import="com.dumplingjoy.pos.PaymentTerms" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'paymentTerms.label', default: 'PaymentTerms')}" />
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
            <g:hasErrors bean="${paymentTermsInstance}">
            <div class="errors">
                <g:renderErrors bean="${paymentTermsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" autocomplete="off">
                <g:hiddenField name="id" value="${paymentTermsInstance?.id}" />
                <g:hiddenField name="version" value="${paymentTermsInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="paymentTerms.name.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: paymentTermsInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${fieldValue(bean: paymentTermsInstance, field: 'name')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="paymentTerms.numberOfDays.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: paymentTermsInstance, field: 'numberOfDays', 'errors')}">
                                    <g:textField name="numberOfDays" value="${fieldValue(bean: paymentTermsInstance, field: 'numberOfDays')}" />
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
		        			onclick="window.location='<g:createLink controller='paymentTerms' action='show' id='${paymentTermsInstance?.id}' />'" />
		        	</span>
                </div>
            </g:form>
        </div>
        <g:javascript>
        	focusOnLoad("name")
        </g:javascript>
    </body>
</html>