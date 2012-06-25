
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
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="supplier.name.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: supplierInstance, field: "name")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="supplier.address.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: supplierInstance, field: "address")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="supplier.contactNumber.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: supplierInstance, field: "contactNumber")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="supplier.contactPerson.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: supplierInstance, field: "contactPerson")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="supplier.faxNumber.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: supplierInstance, field: "faxNumber")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="supplier.emailAddress.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: supplierInstance, field: "emailAddress")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="supplier.tin.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: supplierInstance, field: "tin")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="supplier.terms.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: supplierInstance, field: "terms.name")}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${supplierInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>

			<br/><br/>
            
            <h3>Products</h3>
            <div class="list" style="padding-top:5px">
                <table>
                    <thead>
                        <tr>
                        	<th width="120">Product Code</th>
                        	<th>Product Description</th>
                        	<th width="80"></th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:if test="${!supplierInstance.products.empty}">
                    <g:each in="${supplierInstance.products}" status="i" var="product">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}" > 
                        	<td>${fieldValue(bean: product, field: "code")}</td>
                        	<td>${fieldValue(bean: product, field: "description")}</td>
                        	<td class="center"><input type="button" value="Remove" onclick="removeProduct(${product.id})" /></td>
                        </tr>
                    </g:each>
                    </g:if>
                    <g:else>
                    	<tr>
                    		<td colspan="3">No products</td>
                    	</tr>
                    </g:else>
                    </tbody>
                </table>
            </div>
            
            <div class="buttons">
                <g:form controller="supplier">
                    <g:hiddenField name="id" value="${supplierInstance?.id}" />
                    <span class="button"><g:actionSubmit class="create" action="selectProductToAdd" value="Add Product" /></span>
                </g:form>
            </div>
	        
        </div>
        
       	<g:form name="removeProductForm" action="removeProduct">
       		<g:hiddenField name="id" value="${supplierInstance.id}" />
       		<g:hiddenField name="product.id" />
       	</g:form>
        
       	<g:javascript>
        	function removeProduct(id) {
        		var form = document.removeProductForm;
        		form.elements["product.id"].value = id
        		form.submit()
        	}
       	</g:javascript>
    </body>
</html>