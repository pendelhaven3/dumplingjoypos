<div id="selectProductDialog" style="display:none" ></div>
<g:javascript>
   	function openSelectProductDialog() {
   		var dialogDiv = $("#selectProductDialog");
   		dialogDiv.dialog("open");
   		$.get("${createLink(controller: 'product', action: 'select')}", {code: $("#product\\.code").val()},
   			function(data) {
   				dialogDiv.html(data)
   			}
		);        	
	}
	
	$(document).ready(function() {
   		$("#selectProductDialog").dialog({
   			title: "Select Product",
   			autoOpen: false,
   			modal: true,
   			width: 800,
   			height: 640,
   			resizable: false
   		});
	});
	
	function selectProduct(productCode) {
   		$.get("${createLink(controller: 'product', action: 'getProductByCode')}", {code: productCode},
			function(product) {
				if (product.id != $("#product\\.id").val()) {
					$("#product\\.id").val(product.id)
					$("#product\\.code").val(product.code)
					$("#span_productDescription").text(product.description)
					updateUnits(product.units)
				}
				doAfterSelectProduct()
			}
		);        	
	}
	
	function doAfterSelectProduct() {
		$("#unit").focus()
	}	
	
	function closeDialog() {
		$("#selectProductDialog").dialog("close");
	}
</g:javascript>