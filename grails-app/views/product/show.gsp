
<%@ page import="com.dumplingjoy.pos.Product" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.code.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: productInstance, field: "code")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.description.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: productInstance, field: "description")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.minimumLevel.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: productInstance, field: "minimumLevel")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.maximumLevel.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: productInstance, field: "maximumLevel")}</td>
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${productInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
            
            <br/>
            
            <div class="list" style="padding-top:5px">
	            <h3>Unit Quantities</h3>
                <table>
	                <tr>
	                	<th>Unit</th>
	                	<th>Available Quantity</th>
	                </tr>
	                <g:if test="${!productInstance.unitQuantities.empty}">
	                    <g:each in="${productInstance.unitQuantities.sort{it.unit}}" status="i" var="unitQuantity">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                            <td>${unitQuantity.unit}</td>
	                            <td>${unitQuantity.quantity}</td>
	                        </tr>
	                    </g:each>
                    </g:if>
                    <g:else>
                        <tr>
                            <td colspan="2">No units</td>
                        </tr>
                    </g:else>
                </table>
            </div>
            
            <br/>
            
            <div class="list" style="padding-top:5px">
	            <h3>Unit Conversions</h3>
                <table>
                <tr>
                	<th>From</th>
                	<th>To</th>
                	<th>Converted Quantity</th>
                	<th width="50"></th>
                </tr>
                <g:if test="${!productInstance.unitConversions.empty}">
                    <g:each in="${productInstance.unitConversions}" status="i" var="unitConversion">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>${unitConversion.fromUnit}</td>
                            <td>${unitConversion.toUnit}</td>
                            <td>${unitConversion.convertedQuantity}</td>
                            <td style="text-align:center">
                           		<input type="button" value="Edit" onclick="editUnitConversion(${unitConversion.id})" />
                            </td>
                        </tr>
                    </g:each>
                </g:if>
                <g:else>
                    <tr>
                        <td colspan="4">No unit conversions</td>
                    </tr>
                </g:else>
                </table>
            </div>
                        
        </div>
        
       	<g:form name="editUnitConversionForm" controller="unitConversion" action="edit">
       		<g:hiddenField name="productId" value="${productInstance.id}" />
       		<g:hiddenField name="id" />
       	</g:form>
        
        <g:javascript>
        	function editUnitConversion(id) {
        		var form = document.editUnitConversionForm;
        		form.id.value = id
        		form.submit()
        	}
        </g:javascript>
    </body>
</html>
