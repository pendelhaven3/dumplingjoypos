
<%@ page import="com.dumplingjoy.pos.ReceivingReceipt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'receivingReceipt.label')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <%--
        <style>
			.prop .name {
			    width: auto;
			}
			.prop .value {
			    width: auto;
			}
        </style>
        --%>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${receivingReceiptInstance}">
            <div class="errors">
                <g:renderErrors bean="${receivingReceiptInstance}" as="list" />
            </div>
            </g:hasErrors>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name" style="width:150px;"><g:message code="receivingReceipt.receivingReceiptNumber.label" /></td>
                            <td valign="top" class="value" style="width:450px;">${fieldValue(bean: receivingReceiptInstance, field: "receivingReceiptNumber")}</td>
                            <%--
                            <td valign="top" class="name" style="width:100px;"><g:message code="receivingReceipt.createdBy.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: receivingReceiptInstance, field: "createdBy")}</td>
                            --%>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="receivingReceipt.supplier.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: receivingReceiptInstance, field: "supplier.name")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="receivingReceipt.terms.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: receivingReceiptInstance, field: "terms.name")}</td>
                        </tr>
                        <%--
                        <g:if test="${receivingReceiptInstance.posted}">
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="receivingReceipt.postDate.label" /></td>
	                            <td valign="top" class="value"><g:formatDate date="${receivingReceiptInstance.postDate}" format="MM/dd/yyyy" /></td>
	                        </tr>
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="receivingReceipt.postedBy.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: receivingReceiptInstance, field: "postedBy")}</td>
	                        </tr>
                        </g:if>
                        --%>
                    </tbody>
                </table>
            </div>
            
            <g:if test="${!receivingReceiptInstance.posted}">
	            <div class="buttons">
	                <g:form>
	                    <g:hiddenField name="id" value="${receivingReceiptInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="edit" action="postReceivingReceipt" value="Post" onclick="return confirm('Are you sure you want to post this Receiving Receipt?');" /></span>
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
                        	<th width="50">Quantity</th>
                        	<th width="80">Cost</th>
                        	<th width="100">Amount</th>
                        	<th width="80"></th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:if test="${!receivingReceiptInstance.items.empty}">
                    <g:each in="${receivingReceiptInstance.items.sort {it.product.code}}" status="i" var="item">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'} <%--g:if test="${item.hasPostError}">postError</g:if--%>">
                        	<td>${fieldValue(bean: item, field: "product.code")}</td>
                        	<td>${fieldValue(bean: item, field: "product.description")}</td>
                        	<td class="right">${item.quantity}</td>
                        	<td class="right"><g:formatNumber number="${item.cost}" format="#,##0.00" /></td>
                        	<td class="right"><g:formatNumber number="${item.amount}" format="#,##0.00" /></td>
                        	<td style="text-align:center">
	                        	<g:if test="${!receivingReceiptInstance.posted}">
	                       			<input type="button" value="Discounts" onclick="editReceivingReceiptItemDiscounts(${item.id})" />
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
                
                <g:if test="${!receivingReceiptInstance.items.empty}">
	                <table style="margin-top:2px;">
	                	<tr class="odd">
	                		<td class="right bold">Sub Total</td>
	                		<td width="100" class="right bold"><g:formatNumber number="${receivingReceiptInstance.totalAmount}" format="#,##0.00" /></td>
	                		<td width="80"></td>
	                	</tr>
	                	<tr class="odd" style="color:red">
	                		<td class="right bold">Discount</td>
	                		<td class="right bold"><g:formatNumber number="${receivingReceiptInstance.totalDiscountedAmount}" format="#,##0.00" /></td>
	                		<td></td>
	                	</tr>
	                	<tr class="odd">
	                		<td class="right bold">Net Amount</td>
	                		<td class="right bold"><g:formatNumber number="${receivingReceiptInstance.totalNetAmount}" format="#,##0.00" /></td>
	                		<td></td>
	                	</tr>
	                </table>
	            </g:if>
            </div>
            
        </div>
        
       	<g:form name="editReceivingReceiptItemDiscountsForm" controller="receivingReceiptItem" action="editDiscounts">
       		<g:hiddenField name="receivingReceipt.id" value="${receivingReceiptInstance.id}" />
       		<g:hiddenField name="id" />
       	</g:form>
        
        <g:javascript>
        	function editReceivingReceiptItemDiscounts(id) {
        		var form = document.editReceivingReceiptItemDiscountsForm;
        		form.id.value = id
        		form.submit()
        	}
        </g:javascript>
    </body>
</html>