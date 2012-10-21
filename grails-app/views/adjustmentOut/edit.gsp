

<%@ page import="com.dumplingjoy.pos.AdjustmentOut" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'adjustmentOut.label', default: 'AdjustmentOut')}" />
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
            <g:hasErrors bean="${adjustmentOutInstance}">
            <div class="errors">
                <g:renderErrors bean="${adjustmentOutInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" autocomplete="off">
                <g:hiddenField name="id" value="${adjustmentOutInstance?.id}" />
                <g:hiddenField name="version" value="${adjustmentOutInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="adjustmentOut.adjustmentOutNumber.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: adjustmentOutInstance, field: "adjustmentOutNumber")}</td>
	                        </tr>
	                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="remarks"><g:message code="adjustmentOut.remarks.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: adjustmentOutInstance, field: 'remarks', 'errors')}">
                                	<g:textField name="remarks" value="${adjustmentOutInstance.remarks}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="pricingScheme"><g:message code="adjustmentOut.pricingScheme.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: adjustmentOutInstance, field: 'pricingScheme', 'errors')}">
                                	<g:select name="pricingScheme.id" from="${com.dumplingjoy.pos.PricingScheme.list([sort: "description", order: "asc"])}" value="${adjustmentOutInstance.pricingScheme?.id}" 
                                		optionKey="id" optionValue="description" noSelection="['':'']" />
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
		        			onclick="window.location='<g:createLink controller='adjustmentOut' action='show' id='${adjustmentOutInstance?.id}' />'" />
		        	</span>
                </div>
            </g:form>
        </div>
        <g:javascript>
        	focusOnLoad("remarks")
        </g:javascript>
    </body>
</html>
