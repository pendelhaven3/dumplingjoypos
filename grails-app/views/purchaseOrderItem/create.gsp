

<%@ page import="com.dumplingjoy.pos.PurchaseOrderItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem')}" />
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
            <g:hasErrors bean="${purchaseOrderItemInstance}">
            <div class="errors">
            	<g:each var="error" in="${purchaseOrderItemInstance.errors.globalErrors}">
            		<ul><li><g:message error="${error}" /></li></ul>
            	</g:each>
                <g:renderErrors bean="${purchaseOrderItemInstance}" field="product" />
                <g:renderErrors bean="${purchaseOrderItemInstance}" field="unit" />
                <g:renderErrors bean="${purchaseOrderItemInstance}" field="quantity" />
                <g:renderErrors bean="${purchaseOrderItemInstance}" field="cost" />
            </div>
            </g:hasErrors>
            <g:form action="save">
                <g:hiddenField name="purchaseOrder.id" value="${purchaseOrderInstance?.id}" />
                <g:hiddenField name="product.id" value="${purchaseOrderItemInstance?.product?.id}" />
                <g:hiddenField name="supplier.id" value="${purchaseOrderInstance?.supplier.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="purchaseOrder.purchaseOrderNumber.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: purchaseOrderInstance, field: "purchaseOrderNumber")}</td>
	                        </tr>
	                    
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="purchaseOrder.supplier.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: purchaseOrderInstance, field: "supplier.name")}</td>
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
                                <td valign="top" class="value ${hasErrors(bean: purchaseOrderItemInstance, field: 'product', 'errors')}">
                                	<g:textField name="product.code" onblur="allCaps(this);getProduct(this.value)" style="text-transform:uppercase" 
                                		value="${purchaseOrderItemInstance?.product?.code}" />
                                	<input type="button" value="Select" onclick="openSelectProductDialog()" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="productDescription">Product Description</label>
                                </td>
                                <td valign="top" class="value">
                                	<span id="span_productDescription">
                                		<g:if test="${purchaseOrderItemInstance.product != null}">
                                			${fieldValue(bean: purchaseOrderItemInstance, field: "product.description")}
                                		</g:if>
                                		<g:else>-</g:else>
                                	</span>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="unit"><g:message code="purchaseOrderItem.unit.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseOrderItemInstance, field: 'unit', 'errors')}">
                                	<g:if test="${purchaseOrderItemInstance?.product == null}">
                                		<select name="unit" id="unit" onblur="updateCost(); updateAmount();"></select>
                                	</g:if>
                                	<g:else>
                                		<g:select name="unit" from="${purchaseOrderItemInstance.product.units}" value="${purchaseOrderItemInstance.unit}" 
                                			noSelection="['':'']" onblur="updateCost(); updateAmount();" />
                                	</g:else>
                                </td>
                            </tr>
                        	
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="currentCost">Current Cost</label>
                                </td>
                                <td valign="top" class="value">
                                	<span id="span_currentCost">
                                		<g:if test="${purchaseOrderItemInstance.product != null && purchaseOrderItemInstance.unit != null}">
                                			<g:formatNumber number="${purchaseOrderItemInstance.currentCost}" format="#,##0.00" />
                                		</g:if>
                                		<g:else>-</g:else>
                                	</span>
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="quantity"><g:message code="purchaseOrderItem.quantity.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseOrderItemInstance, field: 'quantity', 'errors')}">
                                    <g:textField name="quantity" value="${purchaseOrderItemInstance.quantity}" onblur="updateAmount()" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cost"><g:message code="purchaseOrderItem.cost.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseOrderItemInstance, field: 'cost', 'errors')}">
                                    <g:textField name="cost" value="${purchaseOrderItemInstance.cost}" onblur="updateAmount()" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount">Amount</label>
                                </td>
                                <td valign="top" class="value">
                                	<span id="span_amount">
                                		<g:if test="${purchaseOrderItemInstance.quantity != null && purchaseOrderItemInstance.cost != null}">
                                			<g:formatNumber number="${purchaseOrderItemInstance.amount}" format="#,##0.00" />
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
		        			onclick="window.location='<g:createLink controller='purchaseOrder' action='show' id='${purchaseOrderInstance?.id}' />'" />
		        	</span>
                </div>
            </g:form>
        </div>
        <g:include view="common/includeSelectProduct.gsp" /> 
        <g:javascript>
        	focusOnLoad("product\\.code")        
        
        	function getProduct() {
        		var productCode = $("#product\\.code").val()
        	
        		$.get("${createLink(controller: 'product', action: 'getProductByCode')}", 
        			{code: productCode, supplierId: ${purchaseOrderInstance.supplier.id}},
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
        	
        	function updateCost() {
        		var code = $("#product\\.code").val()
        		var unit = $("#unit").val()
        		
        		if (code == "" || unit == "") {
       				$("#span_currentCost").html("-")
        		} else {
	        		$.get("${createLink(controller: 'product', action: 'getProductByCode')}", 
	        			{code: code},
	        			function(product) {
	        				if (!jQuery.isEmptyObject(product)) {
				        		for (var i=0; i < product.unitCosts.length; i++) {
				        			var unitCost = product.unitCosts[i]
				        			if (unit == unitCost.unit) {
				        				$("#span_currentCost").html(unitCost.formattedCost)
				        				if ($("#cost").val() == "") {
				        					$("#cost").val(unitCost.cost)
				        				}
				        			}
				        		}
	        				}
	        			}
	        		);
        		}
			}
			
			function updateAmount() {
        		var quantity = $("#quantity").val()
        		var cost = $("#cost").val()
        		
        		if (!isPositiveInteger(quantity) || !isPositiveDecimal(cost)) {
       				$("#span_amount").html("-")
        		} else {
        			$("#span_amount").html(format("#,##0.00", quantity * cost))
        		}
			}
			
        </g:javascript>
    </body>
</html>