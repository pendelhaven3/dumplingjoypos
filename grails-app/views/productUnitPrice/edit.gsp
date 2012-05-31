

<%@ page import="com.dumplingjoy.pos.ProductUnitPrice" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'productUnitPrice.label')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${productUnitPriceInstance}">
            <div class="errors">
                <g:renderErrors bean="${productUnitPriceInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${productUnitPriceInstance?.id}" />
                <g:hiddenField name="version" value="${productUnitPriceInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="pricingScheme.description.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: productUnitPriceInstance, field: "pricingScheme.description")}</td>
	                        </tr>
	                        
	                        <tr class="prop">
	                            <td valign="top" class="name">Product Code</td>
	                            <td valign="top" class="value">${fieldValue(bean: productUnitPriceInstance, field: "product.code")}</td>
	                        </tr>
	                        
	                        <tr class="prop">
	                            <td valign="top" class="name">Product Description</td>
	                            <td valign="top" class="value">${fieldValue(bean: productUnitPriceInstance, field: "product.description")}</td>
	                        </tr>
	                        
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="productUnitPrice.unit.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: productUnitPriceInstance, field: "unit")}</td>
	                        </tr>
	                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="price"><g:message code="productUnitPrice.price.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productUnitPriceInstance, field: 'price', 'errors')}">
                                    <g:textField name="price" value="${productUnitPriceInstance.price}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
		        	<span class="button">
		        		<input type="button" value="Cancel" class="cancel" onclick="cancel()" />
		        	</span>
                </div>
            </g:form>
        </div>
        <g:javascript>
        	focusOnLoad("price")
        	
        	function cancel() {
				window.location = "<g:createLink controller='pricingScheme' action='showProductUnitPrices' 
					id='${productUnitPriceInstance.pricingScheme.id}' params='["product.id": "${productUnitPriceInstance.product.id}"]' />"        		
        	}
        </g:javascript>
    </body>
</html>
