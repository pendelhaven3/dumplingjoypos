
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
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name">Id</td>
                            <td valign="top" class="value">${fieldValue(bean: pricingSchemeInstance, field: "id")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="pricingScheme.description.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: pricingSchemeInstance, field: "description")}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${pricingSchemeInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>

			<br/><br/>
            
            <h3>All Products</h3>
            <div class="list" style="padding-top:5px">
            
            	<br/>
            	Description:&nbsp;&nbsp;&nbsp;<input type="text" name="description" id="description" value="${params.description}" />
            	<input type="button" value="Search" onclick="search()" />
            	<br/><br/>
            
                <table>
                    <thead>
                        <tr>
                        	<th>Product Code</th>
                        	<th>Product Description</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:if test="${!productInstanceList.empty}">
                    <g:each in="${productInstanceList}" status="i" var="product">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'} clickable" onclick="showProductUnitPrices(${product.id})" >
                        	<td>${fieldValue(bean: product, field: "code")}</td>
                        	<td>${fieldValue(bean: product, field: "description")}</td>
                        </tr>
                    </g:each>
                    </g:if>
                    <g:else>
                    	<tr>
                    		<td colspan="2">No products</td>
                    	</tr>
                    </g:else>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${productInstanceTotal}" params="${[description: params.description, id: params.id]}" />
            </div>
            
        </div>
        
       	<g:form name="showProductUnitPricesForm" controller="pricingScheme" action="showProductUnitPrices">
       		<g:hiddenField name="id" value="${pricingSchemeInstance.id}" />
       		<g:hiddenField name="product.id" />
       	</g:form>
        
        <g:javascript>
        	focusOnLoad("description")
        
        	function showProductUnitPrices(productId) {
        		var form = document.showProductUnitPricesForm;
        		form.elements["product.id"].value = productId
        		form.submit()
        	}
        	
			$("#description").keydown(function (e){
			    if(e.keyCode == 13){
			    	search()
			    }
			})
        	
        	function search() {
        		var description = $("#description").val()
        		window.location = "${createLink(action: 'show', id: pricingSchemeInstance.id)}?description=" + encodeURIComponent(description)
        	}
        </g:javascript>
    </body>
</html>