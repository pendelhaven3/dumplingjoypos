
<%@ page import="com.dumplingjoy.pos.AdjustmentIn" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'adjustmentIn.label')}" />
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
                            <td valign="top" class="name"><g:message code="adjustmentIn.adjustmentInNumber.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: adjustmentInInstance, field: "adjustmentInNumber")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="adjustmentIn.remarks.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: adjustmentInInstance, field: "remarks")}</td>
                        </tr>
                        <g:if test="${adjustmentInInstance.posted}">
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="adjustmentIn.posted.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: adjustmentInInstance, field: "posted").equals("true") ? "Yes" : "No"}</td>
	                        </tr>
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="adjustmentIn.postDate.label" /></td>
	                            <td valign="top" class="value"><g:formatDate date="${adjustmentInInstance.postDate}" format="MM/dd/yyyy" /></td>
	                        </tr>
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="adjustmentIn.postedBy.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: adjustmentInInstance, field: "postedBy")}</td>
	                        </tr>
                        </g:if>
                    </tbody>
                </table>
            </div>
            
            <g:if test="${!adjustmentInInstance.posted}">
	            <div class="buttons">
	                <g:form>
	                    <g:hiddenField name="id" value="${adjustmentInInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
	                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
	                </g:form>
	            </div>
	            <div class="buttons">
	                <g:form>
	                    <g:hiddenField name="id" value="${adjustmentInInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="edit" action="postAdjustmentIn" value="Post" onclick="return confirm('Are you sure you want to post this Adjustment In?');" /></span>
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
                       		<th width="90"></th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:if test="${!adjustmentInInstance.items.empty}">
                    <g:each in="${adjustmentInInstance.items.sort{it.product.code}}" status="i" var="item">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}" > 
                        	<td>${item.product.code}</td>
                        	<td>${fieldValue(bean: item, field: "product.description")}</td>
                        	<td>${item.unit}</td>
                        	<td>${item.quantity}</td>
                        	<td style="text-align:center">
	                        	<g:if test="${!adjustmentInInstance.posted}">
	                       			<input type="button" value="Edit" onclick="editAdjustmentInItem(${item.id})" />
	                       			<input type="button" value="Delete" onclick="deleteAdjustmentInItem(${item.id})" />
	                        	</g:if>
                        	</td>
                        </tr>
                    </g:each>
                    </g:if>
                    <g:else>
                    	<tr>
                    		<td colspan="5">No items</td>
                    	</tr>
                    </g:else>
                    </tbody>
                </table>
            </div>
            
            <g:if test="${!adjustmentInInstance.posted}">
	            <div class="buttons">
	                <g:form controller="adjustmentInItem">
	                    <g:hiddenField name="adjustmentIn.id" value="${adjustmentInInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="edit"  action="create" value="Add Item" /></span>
	                </g:form>
	            </div>
	        </g:if>
	        
        </div>
        
       	<g:form name="editAdjustmentInItemForm" controller="adjustmentInItem" action="edit">
       		<g:hiddenField name="adjustmentIn.id" value="${adjustmentInInstance.id}" />
       		<g:hiddenField name="id" />
       	</g:form>
       	<g:form name="deleteAdjustmentInItemForm" controller="adjustmentInItem" action="delete">
       		<g:hiddenField name="adjustmentIn.id" value="${adjustmentInInstance.id}" />
       		<g:hiddenField name="id" />
       	</g:form>
        
        <g:javascript>
        	function editAdjustmentInItem(id) {
        		var form = document.editAdjustmentInItemForm;
        		form.id.value = id
        		form.submit()
        	}
        	function deleteAdjustmentInItem(id) {
        		if (confirm("Are you sure you want to remove this item?")) {
	        		var form = document.deleteAdjustmentInItemForm;
	        		form.id.value = id
	        		form.submit()
        		}
        	}
        </g:javascript>
    </body>
</html>
