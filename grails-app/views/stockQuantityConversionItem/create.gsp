

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
	            	<g:each var="error" in="${stockQuantityConversionItemInstance.errors.globalErrors}">
	            		<ul><li><g:message error="${error}" /></li></ul>
	            	</g:each>
	                <g:renderErrors bean="${stockQuantityConversionItemInstance}" field="product" />
	                <g:renderErrors bean="${stockQuantityConversionItemInstance}" field="fromUnit" />
	                <g:renderErrors bean="${stockQuantityConversionItemInstance}" field="toUnit" />
	                <g:renderErrors bean="${stockQuantityConversionItemInstance}" field="quantity" />
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
                                	<input type="button" value="Select" onclick="openSelectProductDialog()" />
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
                                	<g:if test="${stockQuantityConversionItemInstance?.product == null}">
                                		<select name="fromUnit" id="fromUnit" noSelection="['':'']" onblur="updateAvailableQuantity()"></select>
                                	</g:if>
                                	<g:else>
                                		<g:select name="fromUnit" from="${stockQuantityConversionItemInstance.product.units}" value="${stockQuantityConversionItemInstance.fromUnit}" 
                                			noSelection="['':'']" onblur="updateAvailableQuantity()" />
                                	</g:else>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="toUnit"><g:message code="stockQuantityConversionItem.toUnit.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: stockQuantityConversionItemInstance, field: 'toUnit', 'errors')}">
                                	<g:if test="${stockQuantityConversionItemInstance?.product == null}">
                                		<select name="toUnit" id="toUnit" onblur="updateConvertedQuantity()"></select>
                                	</g:if>
                                	<g:else>
                                		<g:select name="toUnit" from="${stockQuantityConversionItemInstance.product.units}" value="${stockQuantityConversionItemInstance.toUnit}" 
                                			noSelection="['':'']" onblur="updateConvertedQuantity()" />
                                	</g:else>
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
                                		<g:if test="${stockQuantityConversionItemInstance.product != null}">
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
        <g:include view="common/includeSelectProduct.gsp" /> 
        <g:javascript>
        	focusOnLoad("product\\.code");
        
        	function getProduct(code) {
        		$.get("${createLink(controller: 'product', action: 'getProductByCode')}", {code: code},
        			function(product) {
        				if (!jQuery.isEmptyObject(product)) {
        					if ($("#product\\.id").val() != product.id) {
	        					$("#product\\.id").val(product.id)
	        					$("#span_productDescription").html(product.description);
	        					updateUnits(product.units);
        					}
        				} else {
        					$("#product\\.id").val("")
        					$("#span_productDescription").html("");
        					
			        		$("#span_fromUnit").html("")
			        		$("#fromUnit").html("")
			        		$("#span_toUnit").html("")
			        		$("#toUnit").html("")
			        		
        					$("#span_availableQuantity").html("");
        					$("#span_convertedQuantity").html("");
        				}
        			}
        		);
        	}
        
        	function updateUnits(units) {
        		var fields = ["fromUnit", "toUnit"]
        		var doc = document;
        		for (var c=0; c < fields.length; c++) {
	        		var selectUnit = doc.getElementById(fields[c]);
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
        	}
        	
        	function updateAvailableQuantity() {
        		var productCode = $("#product\\.code").val()
        		var fromUnit = $("#fromUnit").val()
        		
        		if (productCode == "" || fromUnit == "") {
       				$("#span_availableQuantity").html("");
        		} else {
	        		$.get("${createLink(controller: 'product', action: 'getProductByCode')}", {code: productCode},
	        			function(product) {
	        				if (!jQuery.isEmptyObject(product)) {
				        		for (var i=0; i < product.unitQuantities.length; i++) {
				        			var unitQuantity = product.unitQuantities[i]
				        			if (fromUnit == unitQuantity.unit.name) {
				        				$("#span_availableQuantity").html(unitQuantity.quantity);
				        			}
				        		}
	        				}
	        			}
	        		);
	        	}
			}
			
			function updateConvertedQuantity() {
				var productCode = $("#product\\.code").val()
				var fromUnit = $("#fromUnit").val()
				var toUnit = $("#toUnit").val()
				var quantity = $("#quantity").val()
				
				if (productCode == "" || fromUnit == "" || toUnit == "" || !isInteger(quantity)) {
					$("#span_convertedQuantity").html("")
				} else {
	        		$.get("${createLink(controller: 'product', action: 'getProductByCode')}", {code: productCode},
	        			function(product) {
	        				if (!jQuery.isEmptyObject(product)) {
	        					for (var i=0; i < product.unitConversions.length; i++) {
	        						var unitConversion = product.unitConversions[i]
	        						if (unitConversion.fromUnit.name == fromUnit && unitConversion.toUnit.name == toUnit) {
	        							$("#span_convertedQuantity").html(unitConversion.convertedQuantity * quantity)
	        						}
	        					}
	        				}
	        			}
	        		);
				}
			}
        	
        	// override from includeSelectProduct.gsp
        	function doAfterSelectProduct() {
        		$("#fromUnit").focus()
        	}
        </g:javascript>
    </body>
</html>