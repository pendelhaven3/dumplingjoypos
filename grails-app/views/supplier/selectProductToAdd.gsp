
<%@ page import="com.dumplingjoy.pos.Supplier" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'supplier.label')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1>Supplier - Add Product</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${supplierInstance}">
            <div class="errors">
                <g:renderErrors bean="${supplierInstance}" as="list" />
            </div>
            </g:hasErrors>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="supplier.name.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: supplierInstance, field: "name")}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="buttons">
	        	<span class="button">
	        		<input type="button" value="Back" class="back" 
	        			onclick="window.location='<g:createLink action='show' id='${supplierInstance.id}' />'" />
	        	</span>
            </div>
            
			<br/><br/>
            
            <h3>Products</h3>
            <div class="list" style="padding-top:5px">
            
            	<br/>
            	Description:&nbsp;&nbsp;&nbsp;<input type="text" name="description" id="description" value="${params.description}" />
            	<input type="button" value="Search" onclick="search()" />
            	<br/><br/>
            	
                <table>
                    <thead>
                        <tr>
                        	<th width="120">Code</th>
                        	<th>Description</th>
                        	<th width="60"></th>
                        </tr>
                    </thead>
                    <tbody>
	                    <g:each in="${productInstanceList}" status="i" var="product">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}" >
		                        <td>${fieldValue(bean: product, field: "code")}</td>
		                        <td>${fieldValue(bean: product, field: "description")}</td>
		                        <td class="center">
		                        	<input type="button" value="Add" onclick="addProduct(${product.id})"  />
		                        </td>
	                        </tr>
	                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate action="selectProductToAdd" total="${productInstanceTotal}" 
                	params="${[id: supplierInstance.id, description: params.description]}" />
            </div>
            
        </div>
        
       	<g:form name="addProductForm" action="addProduct">
       		<g:hiddenField name="id" value="${supplierInstance.id}" />
       		<g:hiddenField name="product.id" />
       		<g:hiddenField name="offset" value="${params.offset}" />
       		<g:hiddenField name="description" value="${params.description}" />
       	</g:form>
       	
       	<g:javascript>
       		focusOnLoad("description")
       	
        	function addProduct(id) {
        		var form = document.addProductForm;
        		form.elements["product.id"].value = id
        		form.submit()
        	}
        	
			$("#description").keydown(function (e){
			    if(e.keyCode == 13){
			    	search()
			    }
			})
        	
        	function search() {
        		var description = $("#description").val()
        		window.location = "${createLink(action: 'selectProductToAdd', id: supplierInstance.id)}?description=" + encodeURIComponent(description)
        	}
       	</g:javascript>
    </body>
</html>