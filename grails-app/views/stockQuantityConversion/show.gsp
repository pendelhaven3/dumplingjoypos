
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
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="stockQuantityConversion.stockQuantityConversionNumber.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: stockQuantityConversionInstance, field: "stockQuantityConversionNumber")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="stockQuantityConversion.description.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: stockQuantityConversionInstance, field: "description")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="stockQuantityConversion.posted.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: stockQuantityConversionInstance, field: "posted").equals("Y") ? "Yes" : "No"}</td>
                        </tr>
                        <g:if test="${stockQuantityConversionInstance.posted}">
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

			<br/><br/>
            
            <h3>Items</h3>
            <div class="list" style="padding-top:5px">
                <table>
                    <thead>
                        <tr>
                        	<th>Product Code</th>
                        	<th>Product Description</th>
                        	<th>From Unit</th>
                        	<th>To Unit</th>
                        	<th>Quantity</th>
                        	<th>Converted Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:if test="${!stockQuantityConversionInstance.items.empty}">
                    <g:each in="${stockQuantityConversionInstance.items}" status="i" var="item">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}"
                        	<g:if test="${!stockQuantityConversionInstance.posted}">
                        		clickable" onclick="window.location='<g:createLink controller='stockQuantityConversionItem' action='edit' id='${item.id}' />'"
                        	</g:if>
                        >
                        	<td>${fieldValue(bean: item, field: "product.code")}</td>
                        	<td>${fieldValue(bean: item, field: "product.description")}</td>
                        	<td>${fieldValue(bean: item, field: "fromUnit")}</td>
                        	<td>${fieldValue(bean: item, field: "toUnit")}</td>
                        	<td>${fieldValue(bean: item, field: "quantity")}</td>
                        	<td>${fieldValue(bean: item, field: "convertedQuantity")}</td>
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
    </body>
</html>
