

<%@ page import="com.dumplingjoy.pos.AdjustmentOutItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'adjustmentOutItem.label')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${adjustmentOutItemInstance}">
            <div class="errors">
                <g:renderErrors bean="${adjustmentOutItemInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${adjustmentOutItemInstance?.id}" />
                <g:hiddenField name="version" value="${adjustmentOutItemInstance?.version}" />
                <g:hiddenField name="adjustmentOut.id" value="${adjustmentOutInstance?.id}" />
                <g:hiddenField name="product.id" value="${adjustmentOutItemInstance?.product?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="adjustmentOut.adjustmentOutNumber.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: adjustmentOutInstance, field: "adjustmentOutNumber")}</td>
	                        </tr>
	                        
	                        <tr class="prop">
	                            <td valign="top" class="name"><g:message code="adjustmentOut.description.label" /></td>
	                            <td valign="top" class="value">${fieldValue(bean: adjustmentOutInstance, field: "description")}</td>
	                        </tr>
	                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="product">Product Code</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: adjustmentOutItemInstance, field: 'product', 'errors')}">
                                	<g:textField name="product.code" onblur="allCaps(this);getProduct(this.value);" style="text-transform:uppercase" 
                                		value="${adjustmentOutItemInstance?.product?.code}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="productDescription">Product Description</label>
                                </td>
                                <td valign="top" class="value">
                                	<span id="span_productDescription">${fieldValue(bean: adjustmentOutItemInstance, field: "product.description")}</span>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="unit"><g:message code="adjustmentOutItem.unit.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: adjustmentOutItemInstance, field: 'unit', 'errors')}">
                                	<g:if test="${adjustmentOutItemInstance?.product == null}">
                                		<select name="unit" id="unit"></select>
                                	</g:if>
                                	<g:else>
                                		<g:select name="unit" from="${adjustmentOutItemInstance.product.units}" value="${adjustmentOutItemInstance.unit}" 
                                			noSelection="['':'']" />
                                	</g:else>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="quantity"><g:message code="adjustmentOutItem.quantity.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: adjustmentOutItemInstance, field: 'quantity', 'errors')}">
                                    <g:textField name="quantity" value="${adjustmentOutItemInstance.quantity}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
		        	<span class="button">
		        		<input type="button" value="Cancel" class="cancel" 
		        			onclick="window.location='<g:createLink controller='adjustmentOut' action='show' id='${adjustmentOutInstance.id}' />'" />
		        	</span>
                </div>
            </g:form>
        </div>
        <g:javascript>
        	focusOnLoad("product\\.code")
        	
        	function getProduct(code) {
        		$.get("${createLink(controller: 'product', action: 'getProductByCode')}", {code: code.toUpperCase()},
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
        			option.text = units[i].name
        			option.value = units[i].name;
        			selectUnit.options.add(option);
        		}
        	}
        </g:javascript>
    </body>
</html>