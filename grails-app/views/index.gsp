<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<sec:ifNotLoggedIn>
	<c:redirect url="/login" />
</sec:ifNotLoggedIn>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <style>
        	#selection td {
        		font: 12pt Verdana;
        		font-weight: bold;
        	}
        </style>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        
        <div class="list" style="margin-left:40px">
        
        	<br/><br/>
        	
        	<table id="selection" style="width:400px">
        		<tr>
        			<th>Menu Items</th>
        		</tr>
        		<tr class="odd clickable" onclick="window.location='<g:createLink controller="product" />'">
                    <td>Product Maintenance</td>
                </tr>
                <tr class="even clickable" onclick="window.location='<g:createLink controller="adjustmentIn" />'">
                    <td>Adjustment In</td>
                </tr>
                <tr class="odd clickable" onclick="window.location='<g:createLink controller="adjustmentOut" />'">
                    <td>Adjustment Out</td>
                </tr>
                <tr class="even clickable" onclick="window.location='<g:createLink controller="stockQuantityConversion" />'">
                    <td>Stock Quantity Conversion</td>
                </tr>
                <tr class="odd clickable" onclick="window.location='<g:createLink controller="pricingScheme" />'">
                    <td>Pricing Schemes</td>
                </tr>
                <tr class="even clickable" onclick="window.location='<g:createLink controller="customer" />'">
                    <td>Customers</td>
                </tr>
                <tr class="odd clickable" onclick="window.location='<g:createLink controller="salesRequisition" />'">
                    <td>Sales Requisition</td>
                </tr>
                <tr class="even clickable" onclick="window.location='<g:createLink controller="salesInvoice" />'">
                    <td>Sales Invoice</td>
                </tr>
                <tr class="odd clickable" onclick="window.location='<g:createLink controller="purchaseOrder" />'">
                    <td>Purchase Order</td>
                </tr>
                <tr class="even clickable" onclick="window.location='<g:createLink controller="receivingReceipt" />'">
                    <td>Receiving Receipt</td>
                </tr>
                <tr class="odd clickable" onclick="window.location='<g:createLink controller="receivingReceipt" />'">
                    <td>Reports (under construction)</td>
                </tr>
                <tr class="even clickable" onclick="window.location='<g:createLink controller="user" />'">
                    <td>User Maintenance (under construction)</td>
                </tr>
                <tr class="odd clickable" onclick="window.location='<g:createLink controller="supplier" />'">
                    <td>Suppliers</td>
                </tr>
                <tr class="even clickable" onclick="window.location='<g:createLink controller="manufacturer" />'">
                    <td>Manufacturers (under construction)</td>
                </tr>
                <tr class="odd clickable" onclick="window.location='<g:createLink controller="productCategory" />'">
                    <td>Product Categories (under construction)</td>
                </tr>
                <tr class="even clickable" onclick="window.location='<g:createLink controller="reinventory" />'">
                    <td>Re-Inventory (under construction)</td>
                </tr>
                <tr class="odd clickable" onclick="window.location='<g:createLink controller="discountTerms" />'">
                    <td>Discount Terms</td>
                </tr>
        	</table>
        </div>
    </body>
</html>