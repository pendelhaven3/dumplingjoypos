

<%@ page import="com.dumplingjoy.pos.AdjustmentOut" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'adjustmentOut.label', default: 'AdjustmentOut')}" />
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
            <g:hasErrors bean="${adjustmentOutInstance}">
            <div class="errors">
                <g:renderErrors bean="${adjustmentOutInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
            	<g:hiddenField name="adjustmentOut.id" value="${adjustmentOutInstance.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="adjustmentOutNo"><g:message code="adjustmentOut.adjustmentOutNumber.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: adjustmentOutInstance, field: 'adjustmentOutNumber', 'errors')}">
                                	To Be Assigned
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="adjustmentOut.description.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: adjustmentOutInstance, field: 'description', 'errors')}">
                                    <g:textField name="description" value="${fieldValue(bean: adjustmentOutInstance, field: 'description')}" />
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
        	focusOnLoad("description")
        </g:javascript>
    </body>
</html>