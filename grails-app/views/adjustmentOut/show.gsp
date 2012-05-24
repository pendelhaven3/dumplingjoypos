
<%@ page import="com.dumplingjoy.pos.AdjustmentOut" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'adjustmentOut.label')}" />
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
                            <td valign="top" class="name"><g:message code="adjustmentOut.adjustmentOutNumber.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: adjustmentOutInstance, field: "adjustmentOutNumber")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="adjustmentOut.description.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: adjustmentOutInstance, field: "description")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="adjustmentOut.posted.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: adjustmentOutInstance, field: "posted").equals("Y") ? "Yes" : "No"}</td>
                        </tr>
                        <g:if test="${adjustmentOutInstance.posted}">
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="adjustmentOut.postDate.label" /></td>
	                            <td valign="top" class="value"><g:formatDate date="${adjustmentOutInstance.postDate}" format="MM/dd/yyyy" /></td>
	                        </tr>
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="adjustmentOut.postedBy.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: adjustmentOutInstance, field: "postedBy")}</td>
	                        </tr>
                        </g:if>
                    </tbody>
                </table>
            </div>
            
            <g:if test="${!adjustmentOutInstance.posted}">
	            <div class="buttons">
	                <g:form>
	                    <g:hiddenField name="id" value="${adjustmentOutInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
	                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
	                </g:form>
	            </div>
	            <div class="buttons">
	                <g:form>
	                    <g:hiddenField name="id" value="${adjustmentOutInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="edit" action="postAdjustmentOut" value="Post" onclick="return confirm('Are you sure you want to post this Adjustment Out?');" /></span>
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
                        	<th>Unit</th>
                        	<th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:if test="${!adjustmentOutInstance.items.empty}">
                    <g:each in="${adjustmentOutInstance.items}" status="i" var="item">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}"
                        	<g:if test="${!adjustmentOutInstance.posted}">
                        		clickable" onclick="window.location='<g:createLink controller='adjustmentOutItem' action='edit' id='${item.id}' />'"
                        	</g:if>
                        >
                        	<td>${item.product.code}</td>
                        	<td>${fieldValue(bean: item, field: "product.description")}</td>
                        	<td>${item.unit}</td>
                        	<td>${item.quantity}</td>
                        </tr>
                    </g:each>
                    </g:if>
                    <g:else>
                    	<tr>
                    		<td colspan="4">No items</td>
                    	</tr>
                    </g:else>
                    </tbody>
                </table>
            </div>
            
            <g:if test="${!adjustmentOutInstance.posted}">
	            <div class="buttons">
	                <g:form controller="adjustmentOutItem">
	                    <g:hiddenField name="adjustmentOutId" value="${adjustmentOutInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="edit"  action="create" value="Add Item" /></span>
	                </g:form>
	            </div>
	        </g:if>
            
        </div>
    </body>
</html>
