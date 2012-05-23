

<%@ page import="com.dumplingjoy.pos.UnitConversion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'unitConversion.label')}" />
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
            <g:hasErrors bean="${productInstance}">
            <div class="errors">
                <g:renderErrors bean="${productInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>

	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="product.code.label" default="Code" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: productInstance, field: "code")}</td>
	                        </tr>
	                    
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="product.description.label" default="Description" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: productInstance, field: "description")}</td>
	                        </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fromUnit"><g:message code="unitConversion.fromUnit.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: unitConversionInstance, field: 'fromUnit', 'errors')}">
                                	<g:select name="fromUnit" from="${productInstance.units}" value="${unitConversionInstance.fromUnit}" noSelection="['':'']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="toUnit"><g:message code="unitConversion.toUnit.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: unitConversionInstance, field: 'toUnit', 'errors')}">
                                	<g:select name="toUnit" from="${productInstance.units}" value="${unitConversionInstance.toUnit}" noSelection="['':'']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="convertedQuantity"><g:message code="unitConversion.convertedQuantity.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: unitConversionInstance, field: 'convertedQuantity', 'errors')}">
                                    <g:textField name="convertedQuantity" value="${unitConversionInstance?.convertedQuantity}" />
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
    </body>
</html>
