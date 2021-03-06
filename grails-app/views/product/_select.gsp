
<%@ page import="com.dumplingjoy.pos.Product" %>
        <div class="body" style="width:750px;">
			<table style="border:none">
				<tr>
					<td width="50" style="vertical-align:middle;" >Code</td>
					<td>
						<input type="text" id="__selectProductCode" style="text-transform:uppercase;" onkeyup="searchProducts(this.value.toUpperCase());" 
							value="${code}" />
					</td>
				</tr>
			</table>
			<br/>
            <div class="list" style="width:750px; height:375px; overflow-y:auto;">
                <table>
                    <thead>
                        <tr>
                        	<th width="100">Code</th>
                        	<th>Description</th>
                        </tr>
                    </thead>
                    <tbody id="productsTableBody">
                    	<tr>
                    		<td colspan="2" class="odd">Input code to search products</td>
                    	</tr>
                    </tbody>
                </table>
            </div>
            
            <br/><br/>
            
            <div style="width:750px;">
                <table>
                    <thead>
                        <tr>
                        	<th width="100">Unit</th>
                        	<th width="100">Unit Price</th>
                        	<th>Quantity Available</th>
                        </tr>
                    </thead>
                    <tbody id="unitPricesAndQuantitiesTableBody">
                    </tbody>
                </table>
            </div>
        </div>
        <g:javascript>
        	function displayUnitPricesAndQuantities(productCode) {
        		var salesRequisitionId = $("#salesRequisition\\.id").val()
        		var pricingSchemeId = $("#pricingScheme\\.id").val()
        		
				$.get("${createLink(controller: 'product', action: 'getProductByCode')}", 
					{code: productCode, pricingSchemeId: pricingSchemeId},
					function(product) {
						var tableBody = $("#unitPricesAndQuantitiesTableBody");
						tableBody.empty();
						
						var unitPricesAndQuantities = convertToUnitPricesAndQuantitiesMap(product)
						var doc = document;
						for (var i=0; i < unitPricesAndQuantities.length; i++) {
							var unitPriceAndQuantity = unitPricesAndQuantities[i];
							var tr = doc.createElement("TR");
							tr.appendChild(createTextColumn(unitPriceAndQuantity.unit));
							tr.appendChild(createTextColumn(unitPriceAndQuantity.unitPrice));
							tr.appendChild(createTextColumn(unitPriceAndQuantity.quantity));
							tableBody.append(tr);
						}
					}
				);
        	}
        	
        	function convertToUnitPricesAndQuantitiesMap(product) {
        		var unitMaps = []
        		for (var i=0; i < product.units.length; i++) {
        			var unit = product.units[i]
        			var unitMap = new Object()
        			unitMap.unit = unit
        			for (var j=0; j < product.unitPrices.length; j++) {
        				var unitPrice = product.unitPrices[j] 
        				if (unitPrice.unit == unit) {
        					unitMap.unitPrice = unitPrice.formattedPrice
        				}
        			}
        			for (var j=0; j < product.unitQuantities.length; j++) {
        				var unitQuantity = product.unitQuantities[j] 
        				if (unitQuantity.unit == unit) {
        					unitMap.quantity = unitQuantity.quantity
        				}
        			}
        			unitMaps.push(unitMap)
        		}
        		return unitMaps
        	}
        	
        	function createTextColumn(text) {
        		var doc = document;
        		var td = doc.createElement("TD");
        		td.appendChild(doc.createTextNode(text));
        		return td;
        	}
        	
        	function removeUnitPricesAndQuantities(productId) {
        		$("#unitPricesAndQuantitiesTableBody").empty();
        	}
        	
        	function __selectProduct(productCode) {
				if (typeof selectProduct == 'function') {
					selectProduct(productCode);
				} else {
					alert("selectProduct(productId) must be implemented!");
				}
				
				if (typeof closeDialog == 'function') {
					closeDialog();
				} else {
					alert("closeDialog() must be implemented!");
				}
        	}
        	
        	function searchProducts(code) {
				var tableBody = $("#productsTableBody");
				
        		var code = $.trim(code);
        		if (!code) {
					tableBody.empty();
					tableBody.html('<tr class="odd"><td colspan="2">Input code to search products</td></tr>');
        			return;
        		}
        		
        		var supplierId = $("#supplier\\.id").val()
        		
				$.get("${createLink(controller: 'product', action: 'searchProductsByCode')}",
					{code: code, supplierId: supplierId},
					function(products) {
						var doc = document;
						tableBody.empty();
						for (var i=0; i < products.length; i++) {
							var product = products[i];
							var tr = doc.createElement("TR");
							tr.className = (i % 2 == 0 ? "odd" : "even") + " clickable";
							tr.setAttribute("onmouseover", "displayUnitPricesAndQuantities('" + product.code + "')");
                        	tr.setAttribute("onmouseout", "removeUnitPricesAndQuantities()");
                        	tr.setAttribute("onclick", "__selectProduct('" + product.code + "')");							
							tr.appendChild(createTextColumn(product.code));
							tr.appendChild(createTextColumn(product.description));
							tableBody.append(tr);
						}
						if (products.length == 0) {
							tableBody.html('<tr class="odd"><td colspan="2">No products found</td></tr>');
						}
					}
				);
        	}
        	
       		$("#__selectProductCode").focus()
       		var code = "${code}"
       		if (code != "") {
       			searchProducts(code)
       		}
       		
        </g:javascript>
