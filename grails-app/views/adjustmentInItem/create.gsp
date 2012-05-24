

<%@ page import="com.dumplingjoy.pos.AdjustmentInItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'adjustmentInItem.label', default: 'AdjustmentInItem')}" />
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
            <g:hasErrors bean="${adjustmentInItemInstance}">
            <div class="errors">
                <g:renderErrors bean="${adjustmentInItemInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save">
                <g:hiddenField name="adjustmentInId" value="${adjustmentInInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="adjustmentIn.adjustmentInNumber.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: adjustmentInInstance, field: "adjustmentInNumber")}</td>
	                        </tr>
	                    
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="adjustmentIn.description.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: adjustmentInInstance, field: "description")}</td>
	                        </tr>
	                    
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="productCode">Product Code</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: adjustmentInItemInstance, field: 'product', 'errors')}">
                                	<g:textField name="productCode" onblur="getProduct(this.value)" style="text-transform:uppercase" />
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
                                    <label for="unit"><g:message code="adjustmentInItem.unit.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: adjustmentInItemInstance, field: 'unit', 'errors')}">
                                	<select name="unit" id="unit" />
                                	<%--
                                	<g:select name="unit" from="${com.dumplingjoy.pos.Unit.values()}" value="${adjustmentInItemInstance.unit}" 
                                		noSelection="['':'']" />
                               		--%>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="quantity"><g:message code="adjustmentInItem.quantity.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: adjustmentInItemInstance, field: 'quantity', 'errors')}">
                                    <g:textField name="quantity" value="${adjustmentInItemInstance.quantity}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
		        	<span class="button">
		        		<input type="button" value="Cancel" class="cancel" 
		        			onclick="window.location='<g:createLink controller='adjustmentIn' action='show' id='${adjustmentInInstance?.id}' />'" />
		        	</span>
                </div>
            </g:form>
        </div>
        <g:javascript>
        	function getProduct(code) {
        		$.get("${createLink(controller: 'product', action: 'getProductByCode')}", {code: code.toUpperCase()},
        			function(product) {
        				if (!jQuery.isEmptyObject(product)) {
        					$("#span_productDescription").html(product.description);
        					updateUnits(product.units);
        				} else {
        					alert("wala e")
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
        			option.text = units[i].name
        			option.value = units[i].name;
        			selectUnit.options.add(option);
        		}
        	}
        	
        	function getAvailableQuantity() {
        		var productId = document.getElementById("product.id").value;
        		var unit = document.getElementById("unit").value;
        	
				new Ajax.Request(
					'/pos/product/getAvailableQuantity',
					{asynchronous:true,evalScripts:true,onSuccess:function(e){updateAvailableQuantity(e.responseText)},
					parameters:'id=' + productId + '&unit=' + unit});        	
			}
			
        </g:javascript>
    </body>
</html>