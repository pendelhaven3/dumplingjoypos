
<%@ page import="com.dumplingjoy.pos.SalesInvoice" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'salesInvoice.label')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
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
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="salesInvoice.postDate.label" /></td>
                            <td valign="top" class="value"><g:formatDate date="${salesInvoiceInstance.postDate}" format="MM/dd/yyyy" /></td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="salesInvoice.postedBy.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: salesInvoiceInstance, field: "postedBy")}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form controller="report">
                    <g:hiddenField name="id" value="${salesInvoiceInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="generateSalesInvoice" value="Print" /></span>
                </g:form>
            </div>
            
			<br/><br/>
            
            <h3>Items</h3>
            <div class="list" style="padding-top:5px">
                <table>
                    <thead>
                        <tr>
                        	<th width="100">Product Code</th>
                        	<th>Product Description</th>
                        	<th width="80">Unit</th>
                        	<th width="80">Quantity</th>
                        	<th width="90">Unit Price</th>
                        	<th width="100">Amount</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:if test="${!salesInvoiceInstance.items.empty}">
                    <g:each in="${salesInvoiceInstance.items}" status="i" var="item">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        	<td>${item.product.code}</td>
                        	<td>${fieldValue(bean: item, field: "product.description")}</td>
                        	<td>${item.unit}</td>
                        	<td>${item.quantity}</td>
                        	<td><g:formatNumber number="${item.unitPrice}" format="#,##0.00" /></td>
                        	<td><g:formatNumber number="${item.amount}" format="#,##0.00" /></td>
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
	                	<tr>
	                		<th style="text-align:right">Total Amount</th>
	                		<th width="100"><g:formatNumber number="${salesInvoiceInstance.totalAmount}" format="#,##0.00" /></th>
	                	</tr>
	                </table>
	            </g:if>
            </div>
        </div>        
    </body>
</html>