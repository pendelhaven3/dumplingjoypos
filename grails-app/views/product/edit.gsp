

<%@ page import="com.dumplingjoy.pos.Product" %>
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
            <g:hasErrors bean="${productInstance}">
            <div class="errors">
            	<g:each var="error" in="${productInstance.errors.globalErrors}">
            		<ul><li><g:message error="${error}" /></li></ul>
            	</g:each>
                <g:renderErrors bean="${productInstance}" field="version" />
                <g:renderErrors bean="${productInstance}" field="code" />
                <g:renderErrors bean="${productInstance}" field="description" />
                <g:renderErrors bean="${productInstance}" field="minimumLevel" />
                <g:renderErrors bean="${productInstance}" field="maximumLevel" />
                <g:renderErrors bean="${productInstance}" field="manufacturer" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${productInstance?.id}" />
                <g:hiddenField name="version" value="${productInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="code"><g:message code="product.code.label" default="Code" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'code', 'errors')}">
                                    <g:textField name="code" value="${productInstance?.code}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="description"><g:message code="product.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'description', 'errors')}">
                                    <g:textField name="description" value="${productInstance?.description}" style="width:500px" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="units"><g:message code="product.units.label" default="Units" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'units', 'errors')}">
                                    <g:each in="${com.dumplingjoy.pos.Unit.values()}" var="unit">
                                    	<g:checkBox name="productUnits" value="${unit}" checked="${productInstance.units.contains(unit)}" />&nbsp;${unit}
                                    </g:each>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="minimumLevel"><g:message code="product.minimumLevel.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'minimumLevel', 'errors')}">
                                    <g:textField name="minimumLevel" value="${productInstance.minimumLevel}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="maximumLevel"><g:message code="product.maximumLevel.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'maximumLevel', 'errors')}">
                                    <g:textField name="maximumLevel" value="${productInstance.maximumLevel}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="manufacturer"><g:message code="product.manufacturer.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'manufacturer', 'errors')}">
                                	<g:select name="manufacturer.id" from="${com.dumplingjoy.pos.Manufacturer.list([sort: "name", order: "asc"])}" value="${productInstance.manufacturer?.id}" 
                                		optionKey="id" optionValue="name" noSelection="['':'']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="category"><g:message code="product.category.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'category', 'errors')}">
                                	<g:select name="category.id" from="${com.dumplingjoy.pos.ProductCategory.list([sort: "name", order: "asc"])}" value="${productInstance.category?.id}" 
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
		        			onclick="window.location='<g:createLink action='show' id='${productInstance.id}' />'" />
		        	</span>
                </div>
            </g:form>
        </div>
        <g:javascript>
        	focusOnLoad("code")
        </g:javascript>
    </body>
</html>
