

<%@ page import="com.dumplingjoy.pos.SalesRequisitionItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'salesRequisitionItem.label', default: 'SalesRequisitionItem')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1><g:message code="default.add.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${salesRequisitionItemInstance}">
            <div class="errors">
            	<g:each var="error" in="${salesRequisitionItemInstance.errors.globalErrors}">
            		<ul><li><g:message error="${error}" /></li></ul>
            	</g:each>
                <g:renderErrors bean="${salesRequisitionItemInstance}" field="product" />
                <g:renderErrors bean="${salesRequisitionItemInstance}" field="unit" />
                <g:renderErrors bean="${salesRequisitionItemInstance}" field="quantity" />
            </div>
            </g:hasErrors>
            <g:form action="save">
                <g:hiddenField name="salesRequisitionItem.id" value="${salesRequisitionItemInstance?.id}" />
                <g:hiddenField name="salesRequisition.id" value="${salesRequisitionInstance?.id}" />
                <g:hiddenField name="product.id" value="${salesRequisitionItemInstance?.product?.id}" />
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
                                <td valign="top" class="name">
                                    <label for="productCode">Product Code</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: salesRequisitionItemInstance, field: 'product', 'errors')}">
                                	<g:textField name="product.code" onblur="allCaps(this);getProduct(this.value)" style="text-transform:uppercase" 
                                		value="${salesRequisitionItemInstance?.product?.code}" />
                                	<input type="button" value="Select" onclick="openSelectProductDialog()" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="productDescription">Product Description</label>
                                </td>
                                <td valign="top" class="value">
                                	<span id="span_productDescription">
                                		<g:if test="${salesRequisitionItemInstance.product != null}">
                                			${fieldValue(bean: salesRequisitionItemInstance, field: "product.description")}
                                		</g:if>
                                		<g:else>-</g:else>
                                	</span>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="unit"><g:message code="salesRequisitionItem.unit.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: salesRequisitionItemInstance, field: 'unit', 'errors')}">
                                	<g:if test="${salesRequisitionItemInstance?.product == null}">
                                		<select name="unit" id="unit" onblur="updateAvailableQuantityAndUnitPrice()"></select>
                                	</g:if>
                                	<g:else>
                                		<g:select name="unit" from="${salesRequisitionItemInstance.product.units}" value="${salesRequisitionItemInstance.unit}" 
                                			noSelection="['':'']" onblur="updateAvailableQuantityAndUnitPrice()" />
                                	</g:else>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="availableQuantity">Available Quantity</label>
                                </td>
                                <td valign="top" class="value">
                                	<span id="span_availableQuantity">
                                		<g:if test="${salesRequisitionItemInstance.product != null && salesRequisitionItemInstance.unit != null}">
                                			${salesRequisitionItemInstance.product.unitQuantities.find{it.unit == salesRequisitionItemInstance.unit}.quantity}
                                		</g:if>
                                		<g:else>-</g:else>
                                	</span>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="unitPrice">Unit Price</label>
                                </td>
                                <td valign="top" class="value">
                                	<span id="span_unitPrice">
                                		<g:if test="${salesRequisitionItemInstance.product != null && salesRequisitionItemInstance.unit != null}">
                                			<g:formatNumber number="${salesRequisitionItemInstance.unitPrice}" format="#,##0.00" />
                                		</g:if>
                                		<g:else>-</g:else>
                                	</span>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="quantity"><g:message code="salesRequisitionItem.quantity.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: salesRequisitionItemInstance, field: 'quantity', 'errors')}">
                                    <g:textField name="quantity" value="${salesRequisitionItemInstance.quantity}" onblur="updateAmount()" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount">Amount</label>
                                </td>
                                <td valign="top" class="value">
                                	<span id="span_amount">
                                		<g:if test="${salesRequisitionItemInstance.product != null && salesRequisitionItemInstance.unit != null && salesRequisitionItemInstance.quantity != null}">
                                			<g:formatNumber number="${salesRequisitionItemInstance.amount}" format="#,##0.00" />
                                		</g:if>
                                		<g:else>-</g:else>
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
		        			onclick="window.location='<g:createLink controller='salesRequisition' action='show' id='${salesRequisitionInstance?.id}' />'" />
		        	</span>
                </div>
            </g:form>
        </div>
        <g:include view="common/includeSelectProduct.gsp" /> 
        <g:javascript>
        	focusOnLoad("product\\.code")        
        
        	function getProduct() {
        		var productCode = $("#product\\.code").val()
        		var pricingSchemeId = $("#pricingScheme\\.id").val()
        	
        		$.get("${createLink(controller: 'product', action: 'getProductByCode')}", 
        			{code: productCode, pricingSchemeId: pricingSchemeId},
        			function(product) {
        				if (!jQuery.isEmptyObject(product)) {
        					if ($("#product\\.id").val() != product.id) {
	        					$("#product\\.id").val(product.id)
	        					$("#span_productDescription").text(product.description);
	        					updateUnits(product.units);
        					}
        				} else {
        					$("#product\\.id").val("")
        					$("#span_productDescription").html("-");
        					$("#unit").html("");
        				}
        			}
        		);
        	}
        
        	function updateUnits(units) {
        		var doc = document;
        		var selectUnit = doc.getElementById("unit");
        		selectUnit.innerHTML = "";
        		
     		    var emptyOption = doc.createElement("OPTION");
     		    emptyOption.text = "";
     		    emptyOption.value = "";
     		    selectUnit.options.add(emptyOption);
        		
        		for (var i=0; i < units.length; i++) {
        			var option = doc.createElement("OPTION");
        			option.text = units[i]
        			option.value = units[i];
        			selectUnit.options.add(option);
        		}
        	}
        	
        	function updateAvailableQuantityAndUnitPrice() {
        		var code = $("#product\\.code").val()
        		var unit = $("#unit").val()
        		
        		if (code == "" || unit == "") {
       				$("#span_availableQuantity").html("-")
       				$("#span_unitPrice").html("-")
        		} else {
	        		$.get("${createLink(controller: 'product', action: 'getProductByCode')}", 
	        			{code: code, pricingSchemeId: ${salesRequisitionInstance.pricingScheme.id}},
	        			function(product) {
	        				if (!jQuery.isEmptyObject(product)) {
				        		for (var i=0; i < product.unitQuantities.length; i++) {
				        			var unitQuantity = product.unitQuantities[i]
				        			if (unit == unitQuantity.unit) {
				        				$("#span_availableQuantity").text(unitQuantity.quantity);
				        			}
				        		}
				        		for (var i=0; i < product.unitPrices.length; i++) {
				        			var unitPrice = product.unitPrices[i]
				        			if (unit == unitPrice.unit) {
				        				$("#span_unitPrice").text(unitPrice.formattedPrice);
						        		var quantity = $("#quantity").val()
						        		if (isInteger(quantity)) {
				        					$("#span_amount").text(format("#,##0.00", unitPrice.price * quantity));
						        		}
				        			}
				        		}
	        				}
	        			}
	        		);
        		}
			}
			
			function updateAmount() {
        		var code = $("#product\\.code").val()
        		var unit = $("#unit").val()
        		var quantity = $("#quantity").val()
        		
        		if (code == "" || unit == "" || !isInteger(quantity)) {
       				$("#span_amount").html("-")
        		} else {
	        		$.get("${createLink(controller: 'product', action: 'getProductByCode')}", 
	        			{code: code, pricingSchemeId: ${salesRequisitionInstance.pricingScheme.id}},
	        			function(product) {
	        				if (!jQuery.isEmptyObject(product)) {
				        		for (var i=0; i < product.unitPrices.length; i++) {
				        			var unitPrice = product.unitPrices[i]
				        			if (unit == unitPrice.unit) {
				        				$("#span_amount").text(format("#,##0.00", unitPrice.price * quantity));
				        			}
				        		}
	        				}
	        			}
	        		);
        		}
			}
			
        </g:javascript>
    </body>
</html>