
<%@ page import="com.dumplingjoy.pos.StockQuantityConversion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'stockQuantityConversion.label')}" />
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
            <g:hasErrors bean="${stockQuantityConversionInstance}">
            <div class="errors">
                <g:renderErrors bean="${stockQuantityConversionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="stockQuantityConversion.stockQuantityConversionNumber.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: stockQuantityConversionInstance, field: "stockQuantityConversionNumber")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="stockQuantityConversion.remarks.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: stockQuantityConversionInstance, field: "remarks")}</td>
                        </tr>
                        <g:if test="${stockQuantityConversionInstance.posted}">
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="stockQuantityConversion.posted.label" /></td>
	                            <td valign="top" class="value">${stockQuantityConversionInstance.posted ? "Yes" : "No"}</td>
	                        </tr>
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="stockQuantityConversion.postDate.label" /></td>
	                            <td valign="top" class="value"><g:formatDate date="${stockQuantityConversionInstance.postDate}" format="MM/dd/yyyy" /></td>
	                        </tr>
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="stockQuantityConversion.postedBy.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: stockQuantityConversionInstance, field: "postedBy")}</td>
	                        </tr>
                        </g:if>
                    </tbody>
                </table>
            </div>
            
            <g:if test="${!stockQuantityConversionInstance.posted}">
	            <div class="buttons">
	                <g:form>
	                    <g:hiddenField name="id" value="${stockQuantityConversionInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
	                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
	                </g:form>
	            </div>
	            <div class="buttons">
	                <g:form>
	                    <g:hiddenField name="id" value="${stockQuantityConversionInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="edit" action="postStockQuantityConversion" value="Post" onclick="return confirm('Are you sure you want to post this Stock Quantity Conversion');" /></span>
	                </g:form>
	            </div>
            </g:if>
 
            <g:if test="${stockQuantityConversionInstance.posted}">
	            <div class="buttons">
	                <g:form controller="report">
	                    <g:hiddenField name="id" value="${stockQuantityConversionInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="print" action="generateStockQuantityConversion" value="Print" /></span>
	                </g:form>
	            </div>
            </g:if>

			<br/><br/>
            
            <h3>Items</h3>
            <div class="list" style="padding-top:5px">
                <table>
                    <thead>
                        <tr>
                        	<th>Product Code</th>
                        	<th>Product Description</th>
                        	<th width="70">From Unit</th>
                        	<th width="60">To Unit</th>
                        	<th width="80">Quantity</th>
                        	<th width="130">Converted Quantity</th>
                        	<th width="90"></th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:if test="${!stockQuantityConversionInstance.items.empty}">
                    <g:each in="${stockQuantityConversionInstance.items.sort {it.product.code}}" status="i" var="item">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'} <g:if test="${item.hasPostError}">postError</g:if>" >
                        	<td>${fieldValue(bean: item, field: "product.code")}</td>
                        	<td>${fieldValue(bean: item, field: "product.description")}</td>
                        	<td>${fieldValue(bean: item, field: "fromUnit")}</td>
                        	<td>${fieldValue(bean: item, field: "toUnit")}</td>
                        	<td>${fieldValue(bean: item, field: "quantity")}</td>
                        	<td><g:formatNumber number="${item.convertedQuantity}" format="#0" /></td>
                        	<td style="text-align:center">
	                        	<g:if test="${!stockQuantityConversionInstance.posted}">
	                       			<input type="button" value="Edit" onclick="editStockQuantityConversionItem(${item.id})" />
	                       			<input type="button" value="Delete" onclick="deleteStockQuantityConversionItem(${item.id})" />
	                        	</g:if>
                        	</td>
                        </tr>
                    </g:each>
                    </g:if>
                    <g:else>
                    	<tr>
                    		<td colspan="6">No items</td>
                    	</tr>
                    </g:else>
                    </tbody>
                </table>
            </div>
            
            <g:if test="${!stockQuantityConversionInstance.posted}">
	            <div class="buttons">
	                <g:form controller="stockQuantityConversionItem">
	                    <g:hiddenField name="stockQuantityConversion.id" value="${stockQuantityConversionInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="edit"  action="create" value="Add Item" /></span>
	                </g:form>
	            </div>
	        </g:if>
            
        </div>
        
       	<g:form name="editStockQuantityConversionItemForm" controller="stockQuantityConversionItem" action="edit">
       		<g:hiddenField name="stockQuantityConversion.id" value="${stockQuantityConversionInstance.id}" />
       		<g:hiddenField name="id" />
       	</g:form>
       	<g:form name="deleteStockQuantityConversionItemForm" controller="stockQuantityConversionItem" action="delete">
       		<g:hiddenField name="stockQuantityConversion.id" value="${stockQuantityConversionInstance.id}" />
       		<g:hiddenField name="id" />
       	</g:form>
       	
        <g:javascript>
        	function editStockQuantityConversionItem(id) {
        		var form = document.editStockQuantityConversionItemForm;
        		form.id.value = id
        		form.submit()
        	}
        	function deleteStockQuantityConversionItem(id) {
        		if (confirm("Are you sure you want to remove this item?")) {
	        		var form = document.deleteStockQuantityConversionItemForm;
	        		form.id.value = id
	        		form.submit()
        		}
        	}
        </g:javascript>
        
    </body>
</html>
