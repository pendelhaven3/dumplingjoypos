

<%@ page import="com.dumplingjoy.pos.PricingScheme" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'pricingScheme.label', default: 'PricingScheme')}" />
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
            <g:hasErrors bean="${pricingSchemeInstance}">
            <div class="errors">
                <g:renderErrors bean="${pricingSchemeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" autocomplete="off">
                <g:hiddenField name="id" value="${pricingSchemeInstance?.id}" />
                <g:hiddenField name="version" value="${pricingSchemeInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
	                        <tr class="prop">
	                            <td valign="top" class="name">Id</td>
	                            <td valign="top" class="value">${fieldValue(bean: pricingSchemeInstance, field: "id")}</td>
	                        </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="description"><g:message code="pricingScheme.description.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: pricingSchemeInstance, field: 'description', 'errors')}">
                                	<g:textField name="description" value="${pricingSchemeInstance.description}" />
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
		        			onclick="window.location='<g:createLink controller='pricingScheme' action='show' id='${pricingSchemeInstance?.id}' />'" />
		        	</span>
                </div>
            </g:form>
        </div>
        <g:javascript>
        	focusOnLoad("description")
        </g:javascript>
    </body>
</html>
