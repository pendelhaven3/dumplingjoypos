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
        		font: 10pt Verdana;
        		font-weight: bold;
        	}
        </style>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        
        <%--
        <div class="body">
            <br/>

            <div id="controllerList" class="dialog">
                <ul>
                    <li class="controller"><g:link controller="product">Product Maintenance</g:link></li>
                    <li class="controller"><g:link controller="adjustmentIn">Adjustment In</g:link></li>
                    <li class="controller"><g:link controller="adjustmentOut">Adjustment Out</g:link></li>
                    <li class="controller"><g:link controller="stockQuantityConversion">Stock Quantity Conversion</g:link></li>
                </ul>
            </div>
            
        </div>
        --%>
        
        <div class="list" style="margin-left:40px">
        
        	<br/><br/>
        	
        	<table id="selection" style="width:300px">
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
        	</table>
        </div>
    </body>
</html>