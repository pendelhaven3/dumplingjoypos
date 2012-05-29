

<%@ page import="com.dumplingjoy.pos.StockQuantityConversionItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'stockQuantityConversionItem.label')}" />
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
                <g:renderErrors bean="${stockQuantityConversionItemInstance}" />
            </div>
            </g:hasErrors>
            <g:form action="save">
                <g:hiddenField name="stockQuantityConversion.id" value="${stockQuantityConversionInstance?.id}" />
                <g:hiddenField name="product.id" value="${stockQuantityConversionItemInstance?.product?.id}" />
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
                                	<g:textField name="product.code" onblur="allCaps(this);getProduct(this.value);" style="text-transform:uppercase" 
                                		value="${stockQuantityConversionItemInstance?.product?.code}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="productDescription">Product Description</label>
                                </td>
                                <td valign="top" class="value">
                                	<span id="span_productDescription">${stockQuantityConversionItemInstance?.product?.description}</span>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fromUnit"><g:message code="stockQuantityConversionItem.fromUnit.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: stockQuantityConversionItemInstance, field: 'fromUnit', 'errors')}">
                                	<span id="span_fromUnit">${stockQuantityConversionItemInstance?.fromUnit}</span>
                                	<g:hiddenField name="fromUnit" value="${stockQuantityConversionItemInstance?.fromUnit}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="toUnit"><g:message code="stockQuantityConversionItem.toUnit.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: stockQuantityConversionItemInstance, field: 'toUnit', 'errors')}">
                                	<span id="span_toUnit">${stockQuantityConversionItemInstance?.toUnit}</span>
                                	<g:hiddenField name="toUnit" value="${stockQuantityConversionItemInstance?.toUnit}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="availableQuantity">Available Quantity</label>
                                </td>
                                <td valign="top" class="value">
                                	<span id="span_availableQuantity">
                                		<g:if test="${stockQuantityConversionItemInstance.product != null && stockQuantityConversionItemInstance.fromUnit != null}">
                                			${stockQuantityConversionItemInstance.product.unitQuantities.find{it.unit == stockQuantityConversionItemInstance.fromUnit}.quantity}
                                		</g:if>
                                	</span>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="quantity"><g:message code="stockQuantityConversionItem.quantity.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: stockQuantityConversionItemInstance, field: 'quantity', 'errors')}">
                                    <g:textField name="quantity" value="${stockQuantityConversionItemInstance.quantity}" onblur="updateConvertedQuantity()" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="convertedQuantity">Converted Quantity</label>
                                </td>
                                <td valign="top" class="value">
                                	<span id="span_convertedQuantity">
                                		<g:if test="${stockQuantityConversionItemInstance.product != null && stockQuantityConversionItemInstance.quantity != null}">
                                			${stockQuantityConversionItemInstance.convertedQuantity}
                                		</g:if>
                                	</span>
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
        	focusOnLoad("product\\.code");
        
        	function getProduct(code) {
        		$.get("${createLink(controller: 'product', action: 'getProductByCode')}", {code: code.toUpperCase()},
        			function(product) {
        				if (!jQuery.isEmptyObject(product)) {
        					$("#product\\.id").val(product.id)
        					$("#span_productDescription").html(product.description);
        					updateUnits(product.units);
        					updateAvailableQuantity(product.unitQuantities)
        					updateConvertedQuantity();
        				} else {
        					$("#product\\.id").val("")
        					$("#span_productDescription").html("");
        					
			        		$("#span_fromUnit").html("")
			        		$("#fromUnit").val("")
			        		$("#span_toUnit").html("")
			        		$("#toUnit").val("")
			        		
        					$("#span_availableQuantity").html("");
        					$("#span_convertedQuantity").html("");
        				}
        			}
        		);
        	}
        
        	function updateUnits(units) {
        		if (units.length == 1) {
        			alert("Only one unit has been specified for product. No conversion needed.")
        			return
        		}
        		
        		var fromUnit = units[0].name
        		var toUnit = units[1].name
        		
        		$("#span_fromUnit").html(fromUnit)
        		$("#fromUnit").val(fromUnit)
        		$("#span_toUnit").html(toUnit)
        		$("#toUnit").val(toUnit)
        	}
        	
        	function updateAvailableQuantity(unitQuantities) {
        		var fromUnit = $("#fromUnit").val()
        		for (var i=0; i < unitQuantities.length; i++) {
        			var unitQuantity = unitQuantities[i]
        			if (fromUnit == unitQuantity.unit.name) {
        				$("#span_availableQuantity").text(unitQuantity.quantity);
        			}
        		}
			}
			
			function updateConvertedQuantity() {
				var productCode = $("#product\\.code").val()
				var fromUnit = $("#fromUnit").val()
				var quantity = $("#quantity").val()

				if (productCode != "" && isInteger(quantity)) {
	        		$.get("${createLink(controller: 'product', action: 'getProductByCode')}", {code: productCode},
	        			function(product) {
	        				if (!jQuery.isEmptyObject(product)) {
	        					for (var i=0; i < product.unitConversions.length; i++) {
	        						var unitConversion = product.unitConversions[i]
	        						if (unitConversion.fromUnit.name == fromUnit) {
	        							$("#span_convertedQuantity").html(unitConversion.convertedQuantity * quantity)
	        						}
	        					}
	        				} else {
       							$("#span_convertedQuantity").html("")
	        				}
	        			}
	        		);
				} else {
					$("#span_convertedQuantity").html("")
				}
			}
        	
        </g:javascript>
    </body>
</html>