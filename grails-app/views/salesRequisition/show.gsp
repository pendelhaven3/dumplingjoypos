
<%@ page import="com.dumplingjoy.pos.SalesRequisition" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'salesRequisition.label')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <style>
			.prop .name {
			    width: auto;
			}
			.prop .value {
			    width: auto;
			}
        </style>
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
            <g:hasErrors bean="${salesRequisitionInstance}">
            <div class="errors">
                <g:renderErrors bean="${salesRequisitionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name" style="width:150px;"><g:message code="salesRequisition.salesRequisitionNumber.label" /></td>
                            <td valign="top" class="value" style="width:450px;">${fieldValue(bean: salesRequisitionInstance, field: "salesRequisitionNumber")}</td>
                            <td valign="top" class="name" style="width:100px;"><g:message code="salesRequisition.createdBy.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: salesRequisitionInstance, field: "createdBy")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="salesRequisition.customer.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: salesRequisitionInstance, field: "customer.name")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="salesRequisition.pricingScheme.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: salesRequisitionInstance, field: "pricingScheme.description")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="salesRequisition.mode.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: salesRequisitionInstance, field: "mode")}</td>
                        </tr>
                        <g:if test="${salesRequisitionInstance.posted}">
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="salesRequisition.postDate.label" /></td>
	                            <td valign="top" class="value"><g:formatDate date="${salesRequisitionInstance.postDate}" format="MM/dd/yyyy" /></td>
	                        </tr>
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="salesRequisition.postedBy.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: salesRequisitionInstance, field: "postedBy")}</td>
	                        </tr>
                        </g:if>
                    </tbody>
                </table>
            </div>
            
            <g:if test="${!salesRequisitionInstance.posted}">
	            <div class="buttons">
	                <g:form>
	                    <g:hiddenField name="id" value="${salesRequisitionInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
	                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
	                </g:form>
	            </div>
	            <div class="buttons">
	                <g:form>
	                    <g:hiddenField name="id" value="${salesRequisitionInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="edit" action="postSalesRequisition" value="Post" onclick="return confirm('Are you sure you want to post this Sales Requisition?');" /></span>
	                </g:form>
	            </div>
            </g:if>
	            
			<br/><br/>
            
            <h3>Items</h3>
            <div class="list" style="padding-top:5px">
                <table>
                    <thead>
                        <tr>
                        	<th width="100">Product Code</th>
                        	<th>Product Description</th>
                        	<th width="30">Unit</th>
                        	<th width="50">Quantity</th>
                        	<th width="80">Unit Price</th>
                        	<th width="100">Amount</th>
                        	<th width="170"></th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:if test="${!salesRequisitionInstance.items.empty}">
                    <g:each in="${salesRequisitionInstance.items}" status="i" var="item">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'} <g:if test="${item.hasPostError}">postError</g:if>">
                        	<td>${item.product.code}</td>
                        	<td>${fieldValue(bean: item, field: "product.description")}</td>
                        	<td>${item.unit}</td>
                        	<td class="right">${item.quantity}</td>
                        	<td width="80" class="right"><g:formatNumber number="${item.unitPrice}" format="#,##0.00" /></td>
                        	<td width="80" class="right"><g:formatNumber number="${item.amount}" format="#,##0.00" /></td>
                        	<td style="text-align:center">
	                        	<g:if test="${!salesRequisitionInstance.posted}">
	                       			<input type="button" value="Edit" onclick="editSalesRequisitionItem(${item.id})" />
	                       			<input type="button" value="Discounts" onclick="editSalesRequisitionItemDiscounts(${item.id})" />
	                       			<input type="button" value="Delete" onclick="deleteSalesRequisitionItem(${item.id})" />
	                        	</g:if>
                        	</td>
                        </tr>
                    </g:each>
                    </g:if>
                    <g:else>
                    	<tr>
                    		<td colspan="7">No items</td>
                    	</tr>
                    </g:else>
                    </tbody>
                </table>
                
                <g:if test="${!salesRequisitionInstance.items.empty}">
	                <table style="margin-top:2px">
	                	<tr>
	                		<th class="right">Sub Total</th>
	                		<th width="100" class="right"><g:formatNumber number="${salesRequisitionInstance.totalAmount}" format="#,##0.00" /></th>
	                		<th width="170"></th>
	                	</tr>
	                	<tr>
	                		<th class="right">Discount</th>
	                		<th class="right"><g:formatNumber number="${salesRequisitionInstance.totalDiscountedAmount}" format="#,##0.00" /></th>
	                		<th></th>
	                	</tr>
	                	<tr>
	                		<th class="right">Net Amount</th>
	                		<th class="right"><g:formatNumber number="${salesRequisitionInstance.totalNetAmount}" format="#,##0.00" /></th>
	                		<th></th>
	                	</tr>
	                </table>
	            </g:if>
            </div>
            
            <g:if test="${!salesRequisitionInstance.posted}">
	            <div class="buttons">
	                <g:form controller="salesRequisitionItem">
	                    <g:hiddenField name="salesRequisition.id" value="${salesRequisitionInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="edit"  action="create" value="Add Item" /></span>
	                </g:form>
	            </div>
            </g:if>
            
        </div>
        
       	<g:form name="editSalesRequisitionItemForm" controller="salesRequisitionItem" action="edit">
       		<g:hiddenField name="salesRequisition.id" value="${salesRequisitionInstance.id}" />
       		<g:hiddenField name="id" />
       	</g:form>
       	<g:form name="editSalesRequisitionItemDiscountsForm" controller="salesRequisitionItem" action="editDiscounts">
       		<g:hiddenField name="salesRequisition.id" value="${salesRequisitionInstance.id}" />
       		<g:hiddenField name="id" />
       	</g:form>
       	<g:form name="deleteSalesRequisitionItemForm" controller="salesRequisitionItem" action="delete">
       		<g:hiddenField name="salesRequisition.id" value="${salesRequisitionInstance.id}" />
       		<g:hiddenField name="id" />
       	</g:form>
        
        <g:javascript>
        	function editSalesRequisitionItem(id) {
        		var form = document.editSalesRequisitionItemForm;
        		form.id.value = id
        		form.submit()
        	}
        	function deleteSalesRequisitionItem(id) {
        		if (confirm("Are you sure you want to remove this item?")) {
	        		var form = document.deleteSalesRequisitionItemForm;
	        		form.id.value = id
	        		form.submit()
        		}
        	}
        	function editSalesRequisitionItemDiscounts(id) {
        		var form = document.editSalesRequisitionItemDiscountsForm;
        		form.id.value = id
        		form.submit()
        	}
        </g:javascript>
    </body>
</html>