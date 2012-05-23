<%@ page import="com.dumplingjoy.pos.AdjustmentIn" %>



<div class="fieldcontain ${hasErrors(bean: adjustmentInInstance, field: 'adjustmentInNumber', 'error')} required">
	<label for="adjustmentInNumber">
		<g:message code="adjustmentIn.adjustmentInNumber.label" default="Adjustment In Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="adjustmentInNumber" required="" value="${fieldValue(bean: adjustmentInInstance, field: 'adjustmentInNumber')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adjustmentInInstance, field: 'items', 'error')} ">
	<label for="items">
		<g:message code="adjustmentIn.items.label" default="Items" />
		
	</label>
	<g:select name="items" from="${com.dumplingjoy.pos.AdjustmentInItem.list()}" multiple="multiple" optionKey="id" size="5" value="${adjustmentInInstance?.items*.id}" class="many-to-many"/>
</div>

