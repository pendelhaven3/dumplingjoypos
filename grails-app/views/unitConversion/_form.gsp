<%@ page import="com.dumplingjoy.pos.UnitConversion" %>



<div class="fieldcontain ${hasErrors(bean: unitConversionInstance, field: 'fromUnit', 'error')} required">
	<label for="fromUnit">
		<g:message code="unitConversion.fromUnit.label" default="From Unit" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="fromUnit" from="${com.dumplingjoy.pos.Unit?.values()}" keys="${com.dumplingjoy.pos.Unit.values()*.name()}" required="" value="${unitConversionInstance?.fromUnit?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: unitConversionInstance, field: 'toUnit', 'error')} required">
	<label for="toUnit">
		<g:message code="unitConversion.toUnit.label" default="To Unit" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="toUnit" from="${com.dumplingjoy.pos.Unit?.values()}" keys="${com.dumplingjoy.pos.Unit.values()*.name()}" required="" value="${unitConversionInstance?.toUnit?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: unitConversionInstance, field: 'convertedQuantity', 'error')} required">
	<label for="convertedQuantity">
		<g:message code="unitConversion.convertedQuantity.label" default="Converted Quantity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="convertedQuantity" min="0" required="" value="${fieldValue(bean: unitConversionInstance, field: 'convertedQuantity')}"/>
</div>

