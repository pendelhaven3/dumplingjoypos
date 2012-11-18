
<%@ page import="com.dumplingjoy.pos.SalesInvoice" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'salesInvoice.label')}" />
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
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${salesInvoiceInstance}">
            <div class="errors">
                <g:renderErrors bean="${salesInvoiceInstance}" as="list" />
            </div>
            </g:hasErrors>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name" style="width:150px;"><g:message code="salesInvoice.salesInvoiceNumber.label" /></td>
                            <td valign="top" class="value" style="width:450px;">${fieldValue(bean: salesInvoiceInstance, field: "salesInvoiceNumber")}</td>
                            <td valign="top" class="name" style="width:100px;"><g:message code="salesInvoice.encodedBy.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: salesInvoiceInstance, field: "encodedBy")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="salesInvoice.customer.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: salesInvoiceInstance, field: "customer.name")}</td>
                            <td valign="top" class="name"><g:message code="salesInvoice.postDate.label" /></td>
                            <td valign="top" class="value"><g:formatDate date="${salesInvoiceInstance.postDate}" format="MM/dd/yyyy" /></td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="salesInvoice.pricingScheme.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: salesInvoiceInstance, field: "pricingScheme.description")}</td>
                            <td valign="top" class="name"><g:message code="salesInvoice.postedBy.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: salesInvoiceInstance, field: "postedBy")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="salesInvoice.mode.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: salesInvoiceInstance, field: "mode")}</td>
                            <g:if test="${salesInvoiceInstance.cancelled}">
	                            <td valign="top" class="name"><g:message code="salesInvoice.cancelled.label" /></td>
	                            <td valign="top" class="value">${salesInvoiceInstance.cancelled ? "Yes" : "No"}</td>
                            </g:if>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="salesInvoice.paymentTerms.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: salesInvoiceInstance, field: "paymentTerms.name")}</td>
                            <g:if test="${salesInvoiceInstance.cancelled}">
	                            <td valign="top" class="name"><g:message code="salesInvoice.cancelledBy.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: salesInvoiceInstance, field: "cancelledBy")}</td>
                            </g:if>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="salesInvoice.remarks.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: salesInvoiceInstance, field: "remarks")}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            
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
                        	<th width="90">Amount</th>
                        	<th width="75"></th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:if test="${!salesInvoiceInstance.items.empty}">
                    <g:each in="${salesInvoiceInstance.items}" status="i" var="item">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        	<td>${item.product.code}</td>
                        	<td>${fieldValue(bean: item, field: "product.description")}</td>
                        	<td>${item.unit}</td>
                        	<td class="right">${item.quantity}</td>
                        	<td class="right"><g:formatNumber number="${item.unitPrice}" format="#,##0.00" /></td>
                        	<td class="right"><g:formatNumber number="${item.amount}" format="#,##0.00" /></td>
                        	<td class="center">
                        		<g:if test="${item.discounted}">
                        			<input type="button" value="Discounts" onclick="showItemDiscounts(${item.id})" />
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
                
                <g:if test="${!salesInvoiceInstance.items.empty}">
	                <table style="margin-top:2px">
	                	<tr class="odd">
	                		<td class="right bold">Sub Total</td>
	                		<td width="90" class="right bold"><g:formatNumber number="${salesInvoiceInstance.totalAmount}" format="#,##0.00" /></td>
	                		<td width="75"></td>
	                	</tr>
	                	<tr class="odd" style="color:red">
	                		<td class="right bold">Discount</td>
	                		<td class="right bold"><g:formatNumber number="${salesInvoiceInstance.totalDiscountedAmount}" format="#,##0.00" /></td>
	                		<td></td>
	                	</tr>
	                	<tr class="odd">
	                		<td class="right bold">Net Amount</td>
	                		<td class="right bold"><g:formatNumber number="${salesInvoiceInstance.totalNetAmount}" format="#,##0.00" /></td>
	                		<td></td>
	                	</tr>
	                </table>
	            </g:if>
            </div>
            
            <br/><br/>
            
            <g:if test="${!salesInvoiceInstance.cancelled}">
	            <div class="buttons">
		            <span class="button">
		            	<input type="button" value="Print" class="print"
		            		onclick="${remoteFunction(controller: 'report', action: 'generateSalesInvoice', id: salesInvoiceInstance.id)}" />
		            </span>
	            </div>
	            <div class="buttons">
	                <g:form>
	                    <g:hiddenField name="id" value="${salesInvoiceInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="cancel" action="cancelSalesInvoice" value="${message(code: 'default.button.cancel.label')}" /></span>
	                </g:form>
	            </div>
            </g:if>
        </div>  
        
        <g:form name="showItemDiscountsForm" controller="salesInvoiceItem" action="showDiscounts">
       		<g:hiddenField name="salesInvoice.id" value="${salesInvoiceInstance.id}" />
       		<g:hiddenField name="id" />
       	</g:form>
       	
        <g:javascript>
        	function showItemDiscounts(id) {
        		var form = document.showItemDiscountsForm;
        		form.id.value = id
        		form.submit()
        	}
        </g:javascript>
    </body>
</html>