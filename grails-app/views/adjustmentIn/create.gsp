

<%@ page import="com.dumplingjoy.pos.AdjustmentIn" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'adjustmentIn.label', default: 'AdjustmentIn')}" />
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
            <g:hasErrors bean="${adjustmentInInstance}">
            <div class="errors">
                <g:renderErrors bean="${adjustmentInInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
            	<g:hiddenField name="adjustmentIn.id" value="${adjustmentInInstance.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="adjustmentInNo"><g:message code="adjustmentIn.adjustmentInNumber.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: adjustmentInInstance, field: 'adjustmentInNumber', 'errors')}">
                                	To Be Assigned
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remarks"><g:message code="adjustmentIn.remarks.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: adjustmentInInstance, field: 'remarks', 'errors')}">
                                    <g:textField name="remarks" value="${fieldValue(bean: adjustmentInInstance, field: 'remarks')}" />
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
        	focusOnLoad("remarks")
        </g:javascript>
    </body>
</html>
