

<%@ page import="com.dumplingjoy.pos.SalesInvoiceItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'salesInvoiceItem.label')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /> Discounts</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                     <tr class="prop">
                         <td valign="top" class="name"><g:message code="salesInvoice.salesInvoiceNumber.label" /></td>
                         <td valign="top" class="value">${fieldValue(bean: salesInvoiceInstance, field: "salesInvoiceNumber")}</td>
                     </tr>
                 
                     <tr class="prop">
                         <td valign="top" class="name"><g:message code="salesInvoice.customer.label" /></td>
                         <td valign="top" class="value">${fieldValue(bean: salesInvoiceInstance, field: "customer.name")}</td>
                     </tr>
                     
                     <tr class="prop">
                         <td valign="top" class="name"><g:message code="salesInvoice.pricingScheme.label" /></td>
                         <td valign="top" class="value">${fieldValue(bean: salesInvoiceInstance, field: "pricingScheme.description")}</td>
                     </tr>
                 </tbody>
             </table>
         </div>
         
            <div class="dialog" style="padding-top:5px;">
                <table>
                    <tbody>
                     <tr class="prop">
                         <td valign="top" class="name">Product Code</td>
                         <td valign="top" class="value">${fieldValue(bean: salesInvoiceItemInstance, field: "product.code")}</td>
                     </tr>
                    
                     <tr class="prop">
                         <td valign="top" class="name">Product Description</td>
                         <td valign="top" class="value">${fieldValue(bean: salesInvoiceItemInstance, field: "product.description")}</td>
                     </tr>
                    
                     <tr class="prop">
                         <td valign="top" class="name"><g:message code="salesInvoiceItem.unit.label" /></td>
                         <td valign="top" class="value">${fieldValue(bean: salesInvoiceItemInstance, field: "unit")}</td>
                     </tr>
                    
                     <tr class="prop">
                         <td valign="top" class="name"><g:message code="salesInvoiceItem.unitPrice.label" /></td>
                         <td valign="top" class="value"><g:formatNumber number="${salesInvoiceItemInstance.unitPrice}" format="#,##0.00" /></td>
                     </tr>
                    
                     <tr class="prop">
                         <td valign="top" class="name"><g:message code="salesInvoiceItem.quantity.label" /></td>
                         <td valign="top" class="value">${fieldValue(bean: salesInvoiceItemInstance, field: "quantity")}</td>
                     </tr>
                     
                     <tr class="prop">
                         <td valign="top" class="name"><g:message code="salesInvoiceItem.amount.label" /></td>
                         <td valign="top" class="value"><g:formatNumber number="${salesInvoiceItemInstance.amount}" format="#,##0.00" /></td>
                     </tr>
                    
                    </tbody>
                </table>
            </div>
            
            <div class="dialog" style="padding-top:5px;">
                <table>
                    <tbody>
	                     <tr class="prop">
                            <td valign="top" class="name"><g:message code="salesInvoiceItem.discount1.label" /> (%)</td>
	                         <td valign="top" class="value"><g:formatNumber number="${salesInvoiceItemInstance.discount1}" format="#,##0.00" /></td>
	                     </tr>
                    
	                     <tr class="prop">
                            <td valign="top" class="name"><g:message code="salesInvoiceItem.discount2.label" /> (%)</td>
	                         <td valign="top" class="value"><g:formatNumber number="${salesInvoiceItemInstance.discount2}" format="#,##0.00" /></td>
	                     </tr>
	                     
	                     <tr class="prop">
                            <td valign="top" class="name"><g:message code="salesInvoiceItem.discount3.label" /> (%)</td>
	                         <td valign="top" class="value"><g:formatNumber number="${salesInvoiceItemInstance.discount3}" format="#,##0.00" /></td>
	                     </tr>
	                     
	                     <tr class="prop">
                            <td valign="top" class="name"><g:message code="salesInvoiceItem.flatRateDiscount.label" /></td>
	                         <td valign="top" class="value"><g:formatNumber number="${salesInvoiceItemInstance.flatRateDiscount}" format="#,##0.00" /></td>
	                     </tr>
	                     
	                     <tr class="prop">
                            <td valign="top" class="name"><g:message code="salesInvoiceItem.discountedAmount.label" /></td>
	                         <td valign="top" class="value"><g:formatNumber number="${salesInvoiceItemInstance.discountedAmount}" format="#,##0.00" /></td>
	                     </tr>
	                     
	                     <tr class="prop">
                            <td valign="top" class="name"><g:message code="salesInvoiceItem.netAmount.label" /></td>
	                         <td valign="top" class="value"><g:formatNumber number="${salesInvoiceItemInstance.netAmount}" format="#,##0.00" /></td>
	                     </tr>
                    
                    </tbody>
                </table>
            </div>
            
			<div class="buttons">
				<span class="button">
					<input type="button" value="Back" class="back" 
						onclick="window.location='<g:createLink controller='salesInvoice' action='show' id='${salesInvoiceInstance.id}' />'" />
				</span>
			</div>
        </div>
    </body>
</html>