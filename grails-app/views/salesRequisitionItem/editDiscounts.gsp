

<%@ page import="com.dumplingjoy.pos.SalesRequisitionItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'salesRequisitionItem.label')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /> Discounts</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${salesRequisitionItemInstance}">
            <div class="errors">
            	<g:each var="error" in="${salesRequisitionItemInstance.errors.globalErrors}">
            		<ul><li><g:message error="${error}" /></li></ul>
            	</g:each>
                <g:renderErrors bean="${salesRequisitionItemInstance}" field="version" />
                <g:renderErrors bean="${salesRequisitionItemInstance}" field="quantity" />
                <g:renderErrors bean="${salesRequisitionItemInstance}" field="discount1" />
                <g:renderErrors bean="${salesRequisitionItemInstance}" field="discount2" />
                <g:renderErrors bean="${salesRequisitionItemInstance}" field="discount3" />
                <g:renderErrors bean="${salesRequisitionItemInstance}" field="flatRateDiscount" />
            </div>
            </g:hasErrors>
            <g:form method="post" autocomplete="off">
                <g:hiddenField name="id" value="${salesRequisitionItemInstance?.id}" />
                <g:hiddenField name="version" value="${salesRequisitionItemInstance?.version}" />
                <g:hiddenField name="salesRequisition.id" value="${salesRequisitionInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="salesRequisition.salesRequisitionNumber.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: salesRequisitionInstance, field: "salesRequisitionNumber")}</td>
	                        </tr>
	                    
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="salesRequisition.customer.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: salesRequisitionInstance, field: "customer.name")}</td>
	                        </tr>
	                        
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="salesRequisition.pricingScheme.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: salesRequisitionInstance, field: "pricingScheme.description")}</td>
	                        </tr>
	                    </tbody>
	                </table>
	            </div>
	            
                <div class="dialog" style="padding-top:5px;">
                    <table>
                        <tbody>
	                        <tr class="prop">
	                            <td valign="top" class="name">Product Code</td>
	                            <td valign="top" class="value">${fieldValue(bean: salesRequisitionItemInstance, field: "product.code")}</td>
	                        </tr>
                        
	                        <tr class="prop">
	                            <td valign="top" class="name">Product Description</td>
	                            <td valign="top" class="value">${fieldValue(bean: salesRequisitionItemInstance, field: "product.description")}</td>
	                        </tr>
                        
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="salesRequisitionItem.unit.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: salesRequisitionItemInstance, field: "unit")}</td>
	                        </tr>
                        
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="salesRequisitionItem.unitPrice.label" /></td>
	                            <td valign="top" class="value"><g:formatNumber number="${salesRequisitionItemInstance.unitPrice}" format="#,##0.00" /></td>
	                        </tr>
                        
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="salesRequisitionItem.quantity.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: salesRequisitionItemInstance, field: "quantity")}</td>
	                        </tr>
	                        
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="salesRequisitionItem.amount.label" /></td>
	                            <td valign="top" class="value"><g:formatNumber number="${salesRequisitionItemInstance.amount}" format="#,##0.00" /></td>
	                        </tr>
                        
                        </tbody>
                    </table>
                </div>
                
                <div class="dialog" style="padding-top:5px;">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="discount1"><g:message code="salesRequisitionItem.discount1.label" /> (%)</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: salesRequisitionItemInstance, field: 'discount1', 'errors')}">
                                    <g:textField name="discount1" value="${salesRequisitionItemInstance.discount1}" onblur="updateDiscountedAndNetAmounts();" />
                                </td>
                            </tr>
		                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="discount2"><g:message code="salesRequisitionItem.discount2.label" /> (%)</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: salesRequisitionItemInstance, field: 'discount2', 'errors')}">
                                    <g:textField name="discount2" value="${salesRequisitionItemInstance.discount2}" onblur="updateDiscountedAndNetAmounts();" />
                                </td>
                            </tr>
		                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="discount3"><g:message code="salesRequisitionItem.discount3.label" /> (%)</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: salesRequisitionItemInstance, field: 'discount3', 'errors')}">
                                    <g:textField name="discount3" value="${salesRequisitionItemInstance.discount3}" onblur="updateDiscountedAndNetAmounts();" />
                                </td>
                            </tr>
		                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="flatRateDiscount"><g:message code="salesRequisitionItem.flatRateDiscount.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: salesRequisitionItemInstance, field: 'flatRateDiscount', 'errors')}">
                                    <g:textField name="flatRateDiscount" value="${salesRequisitionItemInstance.flatRateDiscount}" onblur="updateDiscountedAndNetAmounts();" />
                                </td>
                            </tr>
		                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="discountedAmount">Discounted Amount</label>
                                </td>
                                <td valign="top" class="value">
                                	<span id="span_discountedAmount"><g:formatNumber number="${salesRequisitionItemInstance.discountedAmount}" format="#,##0.00" /></span>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="netAmount">Net Amount</label>
                                </td>
                                <td valign="top" class="value">
                                	<span id="span_netAmount"><g:formatNumber number="${salesRequisitionItemInstance.netAmount}" format="#,##0.00" /></span>
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="updateDiscounts" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
		        	<span class="button">
		        		<input type="button" value="Cancel" class="cancel" 
		        			onclick="window.location='<g:createLink controller='salesRequisition' action='show' id='${salesRequisitionInstance.id}' />'" />
		        	</span>
                </div>
            </g:form>
        </div>
        <g:javascript>
        	focusOnLoad("discount1")
        	
        	function updateDiscountedAndNetAmounts() {
        		var id = $("#id").val()
        		var discount1 = $("#discount1").val()
        		var discount2 = $("#discount2").val()
        		var discount3 = $("#discount3").val()
        		var flatRateDiscount = $("#flatRateDiscount").val()
        		
        		if (!isPositiveDecimal(discount1) || !isPositiveDecimal(discount2) || !isPositiveDecimal(discount3) || !isPositiveDecimal(flatRateDiscount)) {
        			$("#span_discountedAmount").html("-")
        			$("#span_netAmount").html("-")
        		} else {
	        		$.get("${createLink(controller: 'salesRequisitionItem', action: 'getDiscountedAndNetAmounts')}", 
	        			{id: id, discount1: discount1, discount2: discount2, discount3: discount3, flatRateDiscount: flatRateDiscount},
	        			function(data) {
	       					$("#span_discountedAmount").html(format("#,##0.00", data.discountedAmount));
	       					$("#span_netAmount").html(format("#,##0.00", data.netAmount));
	        			}
	        		);
        		}
        	}
        </g:javascript>
    </body>
</html>