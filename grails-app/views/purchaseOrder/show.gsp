
<%@ page import="com.dumplingjoy.pos.PurchaseOrder" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'purchaseOrder.label')}" />
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
            <g:hasErrors bean="${purchaseOrderInstance}">
            <div class="errors">
                <g:renderErrors bean="${purchaseOrderInstance}" as="list" />
            </div>
            </g:hasErrors>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name" style="width:150px;"><g:message code="purchaseOrder.purchaseOrderNumber.label" /></td>
                            <td valign="top" class="value" style="width:450px;">${fieldValue(bean: purchaseOrderInstance, field: "purchaseOrderNumber")}</td>
                            <td valign="top" class="name" style="width:100px;"><g:message code="purchaseOrder.createdBy.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: purchaseOrderInstance, field: "createdBy")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="purchaseOrder.supplier.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: purchaseOrderInstance, field: "supplier.name")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="purchaseOrder.terms.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: purchaseOrderInstance, field: "terms.name")}</td>
                        </tr>
                        <g:if test="${purchaseOrderInstance.ordered}">
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="purchaseOrder.orderDate.label" /></td>
	                            <td valign="top" class="value"><g:formatDate date="${purchaseOrderInstance.orderDate}" format="MM/dd/yyyy" /></td>
	                        </tr>
	                    </g:if>
                        <g:if test="${purchaseOrderInstance.posted}">
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="purchaseOrder.postDate.label" /></td>
	                            <td valign="top" class="value"><g:formatDate date="${purchaseOrderInstance.postDate}" format="MM/dd/yyyy" /></td>
	                            <td valign="top" class="name"><g:message code="purchaseOrder.postedBy.label" /></td>
                            	<td valign="top" class="value">${fieldValue(bean: purchaseOrderInstance, field: "postedBy")}</td>
	                        </tr>
	                        <tr class="prop">
	                            <td valign="top" class="name">Related Receiving Receipt No.</td>
	                            <td valign="top" class="value">${purchaseOrderInstance.relatedReceivingReceipt.receivingReceiptNumber}</td>
	                        </tr>
	                    </g:if>
                    </tbody>
                </table>
            </div>
            
            <g:if test="${!purchaseOrderInstance.posted}">
	            <div class="buttons">
	                <g:form>
	                    <g:hiddenField name="id" value="${purchaseOrderInstance?.id}" />
	           			<g:if test="${!purchaseOrderInstance.ordered}">
	                    	<span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
	                    </g:if>
	                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
	                </g:form>
	            </div>
	            <g:if test="${!purchaseOrderInstance.ordered}">
		            <div class="buttons">
		                <g:form>
		                    <g:hiddenField name="id" value="${purchaseOrderInstance?.id}" />
		                    <span class="button"><g:actionSubmit class="edit" action="markAsOrdered" value="Mark As Ordered" onclick="return confirm('Are you sure you want to mark this Purchase Order as ordered?');" /></span>
		                </g:form>
		            </div>
		        </g:if>
	            <g:if test="${purchaseOrderInstance.ordered}">
		            <div class="buttons">
		                <g:form>
		                    <g:hiddenField name="id" value="${purchaseOrderInstance?.id}" />
		                    <span class="button"><g:actionSubmit class="edit" action="postPurchaseOrder" value="Post" onclick="return confirm('Are you sure you want to post this Purchase Order?');" /></span>
		                </g:form>
		            </div>
		        </g:if>
	            <div class="buttons">
	                <g:form controller="report">
	                    <g:hiddenField name="id" value="${purchaseOrderInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="print" action="generatePurchaseOrder" value="Print" /></span>
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
                        	<th width="85">${purchaseOrderInstance.ordered ? "Ordered Qty" : "Quantity"}</th>
                        	<g:if test="${purchaseOrderInstance.ordered}">
                        		<th width="70">Actual Qty</th>
                        	</g:if>
                        	<th width="80">Cost</th>
                        	<th width="95">Amount</th>
                        	<g:if test="${!purchaseOrderInstance.posted}">
                        		<th width="100"></th>
                        	</g:if>
                        </tr>
                    </thead>
                    <tbody>
                    <g:if test="${!purchaseOrderInstance.items.empty}">
                    <g:each in="${purchaseOrderInstance.items.sort {it.product.code}}" status="i" var="item">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        	<td>${item.product.code}</td>
                        	<td>${fieldValue(bean: item, field: "product.description")}</td>
                        	<td>${item.unit}</td>
                        	<td class="right">${item.quantity}</td>
                        	<g:if test="${purchaseOrderInstance.ordered}">
                        		<td class="right">${fieldValue(bean: item, field: "actualQuantity")}</td>
                        	</g:if>
                        	<td width="80" class="right"><g:formatNumber number="${item.cost}" format="#,##0.00" /></td>
                        	<td width="80" class="right"><g:formatNumber number="${item.amount}" format="#,##0.00" /></td>
                        	<g:if test="${!purchaseOrderInstance.posted}">
	                        	<td style="text-align:center">
	                       			<input type="button" value="Edit" onclick="editPurchaseOrderItem(${item.id})" />
	           						<g:if test="${!(purchaseOrderInstance.ordered && item.quantity > 0)}">
	                       				<input type="button" value="Delete" onclick="deletePurchaseOrderItem(${item.id})" />
	                       			</g:if>
	                        	</td>
                        	</g:if>
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
                
                <g:if test="${!purchaseOrderInstance.items.empty}">
	                <table style="margin-top:2px;">
	                	<tr class="odd">
	                		<td class="right bold">Total Amount</td>
	                		<td width="95" class="right bold"><g:formatNumber number="${purchaseOrderInstance.totalAmount}" format="#,##0.00" /></td>
                        	<g:if test="${!purchaseOrderInstance.posted}">
	                			<td width="100"></td>
	                		</g:if>
	                	</tr>
	                </table>
	            </g:if>
	            
            </div>
            
            <g:if test="${!purchaseOrderInstance.posted}">
	            <div class="buttons">
	                <g:form controller="purchaseOrderItem">
	                    <g:hiddenField name="purchaseOrder.id" value="${purchaseOrderInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="edit"  action="create" value="Add Item" /></span>
	                </g:form>
	            </div>
	        </g:if>
            
        </div>
        
       	<g:form name="editPurchaseOrderItemForm" controller="purchaseOrderItem" action="edit">
       		<g:hiddenField name="purchaseOrder.id" value="${purchaseOrderInstance.id}" />
       		<g:hiddenField name="id" />
       	</g:form>
       	<g:form name="deletePurchaseOrderItemForm" controller="purchaseOrderItem" action="delete">
       		<g:hiddenField name="purchaseOrder.id" value="${purchaseOrderInstance.id}" />
       		<g:hiddenField name="id" />
       	</g:form>
        
        <g:javascript>
        	function editPurchaseOrderItem(id) {
        		var form = document.editPurchaseOrderItemForm;
        		form.id.value = id
        		form.submit()
        	}
        	function deletePurchaseOrderItem(id) {
        		if (confirm("Are you sure you want to remove this item?")) {
	        		var form = document.deletePurchaseOrderItemForm;
	        		form.id.value = id
	        		form.submit()
        		}
        	}
        </g:javascript>
    </body>
</html>