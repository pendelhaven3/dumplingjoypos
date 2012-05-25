

<%@ page import="com.dumplingjoy.pos.UnitConversion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
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
            <g:hasErrors bean="${unitConversionInstance}">
            <div class="errors">
                <g:renderErrors bean="${unitConversionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${unitConversionInstance?.id}" />
                <g:hiddenField name="version" value="${unitConversionInstance?.version}" />
                <g:hiddenField name="productId" value="${productInstance?.id}" />
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
	                            <td valign="top" class="value">${fieldValue(bean: unitConversionInstance, field: "fromUnit")}</td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="toUnit"><g:message code="unitConversion.toUnit.label" /></label>
                                </td>
	                            <td valign="top" class="value">${fieldValue(bean: unitConversionInstance, field: "toUnit")}</td>
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
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
		        	<span class="button">
		        		<input type="button" value="Cancel" class="cancel" 
		        			onclick="window.location='<g:createLink controller="product" action='show' id='${productInstance.id}' />'" />
		        	</span>
                </div>
            </g:form>
        </div>
        <g:javascript>
        	focusOnLoad("convertedQuantity")
        </g:javascript>
    </body>
</html>
