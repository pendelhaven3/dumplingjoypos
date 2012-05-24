

<%@ page import="com.dumplingjoy.pos.StockQuantityConversionItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'stockQuantityConversionItem.label', default: 'StockQuantityConversionItem')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${stockQuantityConversionItemInstance}">
            <div class="errors">
                <g:renderErrors bean="${stockQuantityConversionItemInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save">
                <g:hiddenField name="stockQuantityConversionId" value="${stockQuantityConversionInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="stockQuantityConversion.stockQuantityConversionNumber.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: stockQuantityConversionInstance, field: "stockQuantityConversionNumber")}</td>
	                        </tr>
	                    
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="stockQuantityConversion.description.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: stockQuantityConversionInstance, field: "description")}</td>
	                        </tr>
	                    
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="productCode">Product Code</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: stockQuantityConversionItemInstance, field: 'product', 'errors')}">
                                	<g:textField name="productCode" onblur="allCaps(this);getProduct(this.value);" style="text-transform:uppercase" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="productDescription">Product Description</label>
                                </td>
                                <td valign="top" class="value">
                                	<span id="span_productDescription"></span>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fromUnit"><g:message code="stockQuantityConversionItem.fromUnit.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: stockQuantityConversionItemInstance, field: 'fromUnit', 'errors')}">
                                	<select name="fromUnit" id="fromUnit" />
                                	<%--
                                	<g:select name="unit" from="${com.dumplingjoy.pos.Unit.values()}" value="${stockQuantityConversionItemInstance.unit}" 
                                		noSelection="['':'']" />
                               		--%>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="toUnit"><g:message code="stockQuantityConversionItem.toUnit.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: stockQuantityConversionItemInstance, field: 'toUnit', 'errors')}">
                                	<select name="toUnit" id="toUnit" />
                                	<%--
                                	<g:select name="unit" from="${com.dumplingjoy.pos.Unit.values()}" value="${stockQuantityConversionItemInstance.unit}" 
                                		noSelection="['':'']" />
                               		--%>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="quantity"><g:message code="stockQuantityConversionItem.quantity.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: stockQuantityConversionItemInstance, field: 'quantity', 'errors')}">
                                    <g:textField name="quantity" value="${stockQuantityConversionItemInstance.quantity}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
		        	<span class="button">
		        		<input type="button" value="Cancel" class="cancel" 
		        			onclick="window.location='<g:createLink controller='stockQuantityConversion' action='show' id='${stockQuantityConversionInstance?.id}' />'" />
		        	</span>
                </div>
            </g:form>
        </div>
        <g:javascript>
        	focusOnLoad("productCode");
        
        	function getProduct(code) {
        		if (code != "") {
	        		$.get("${createLink(controller: 'product', action: 'getProductByCode')}", {code: code.toUpperCase()},
	        			function(product) {
	        				if (!jQuery.isEmptyObject(product)) {
	        					$("#span_productDescription").html(product.description);
	        					updateUnits("fromUnit", product.units);
	        					updateUnits("toUnit", product.units);
	        				} else {
	        					alert("wala e")
	        				}
	        			}
	        		);
        		}
        	}
        
        	function updateUnits(field, units) {
        		var doc = document;
        		var selectUnit = doc.getElementById(field);
        		selectUnit.innerHTML = "";
        		
     		    var emptyOption = doc.createElement("OPTION");
     		    emptyOption.text = "";
     		    emptyOption.value = "";
     		    selectUnit.options.add(emptyOption);
        		
        		for (var i=0; i < units.length; i++) {
        			var option = doc.createElement("OPTION");
        			option.text = units[i].name
        			option.value = units[i].name;
        			selectUnit.options.add(option);
        		}
        	}
        	
        </g:javascript>
    </body>
</html>