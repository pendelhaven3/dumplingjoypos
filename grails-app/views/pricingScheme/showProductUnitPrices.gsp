
<%@ page import="com.dumplingjoy.pos.PricingScheme" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'pricingScheme.label')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /> - Product</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="pricingScheme.description.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: pricingSchemeInstance, field: "description")}</td>
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Product Code</td>
                            <td valign="top" class="value">${fieldValue(bean: productInstance, field: "code")}</td>
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Product Description</td>
                            <td valign="top" class="value">${fieldValue(bean: productInstance, field: "description")}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            
            <div class="buttons">
	        	<span class="button">
	        		<input type="button" value="Back to All Products" class="back" 
	        			onclick="window.location='<g:createLink action='show' id='${pricingSchemeInstance.id}' />'" />
	        	</span>
            </div>

			<br/><br/>
            
            <div class="list" style="padding-top:5px">
                <table>
                    <thead>
                        <tr>
                        	<th>Unit</th>
                        	<th>Selling Price</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:if test="${!unitPrices.empty}">
                    <g:each in="${unitPrices}" status="i" var="unitPrice">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'} clickable" onclick="editProductUnitPrice(${unitPrice.id})" >
                        	<td>${fieldValue(bean: unitPrice, field: "unit")}</td>
                        	<td><g:formatNumber number="${unitPrice.price}" format="#,##0.00" /></td>
                        </tr>
                    </g:each>
                    </g:if>
                    <g:else>
                    	<tr>
                    		<td colspan="2">No unit prices</td>
                    	</tr>
                    </g:else>
                    </tbody>
                </table>
            </div>
            
        </div>
        
       	<g:form name="editProductUnitPriceForm" controller="productUnitPrice" action="edit">
       		<g:hiddenField name="id" />
       	</g:form>
        
        <g:javascript>
        	function editProductUnitPrice(id) {
        		var form = document.editProductUnitPriceForm;
        		form.id.value = id
        		form.submit()
        	}
        </g:javascript>
    </body>
</html>