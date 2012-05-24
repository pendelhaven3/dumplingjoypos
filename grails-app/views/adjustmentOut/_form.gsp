<%@ page import="com.dumplingjoy.pos.AdjustmentOut" %>



<div class="fieldcontain ${hasErrors(bean: adjustmentOutInstance, field: 'adjustmentOutNumber', 'error')} required">
	<label for="adjustmentOutNumber">
		<g:message code="adjustmentOut.adjustmentOutNumber.label" default="Adjustment Out Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="adjustmentOutNumber" required="" value="${fieldValue(bean: adjustmentOutInstance, field: 'adjustmentOutNumber')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adjustmentOutInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="adjustmentOut.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${adjustmentOutInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adjustmentOutInstance, field: 'postDate', 'error')} ">
	<label for="postDate">
		<g:message code="adjustmentOut.postDate.label" default="Post Date" />
		
	</label>
	<g:datePicker name="postDate" precision="day"  value="${adjustmentOutInstance?.postDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: adjustmentOutInstance, field: 'postedBy', 'error')} ">
	<label for="postedBy">
		<g:message code="adjustmentOut.postedBy.label" default="Posted By" />
		
	</label>
	<g:textField name="postedBy" value="${adjustmentOutInstance?.postedBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adjustmentOutInstance, field: 'items', 'error')} ">
	<label for="items">
		<g:message code="adjustmentOut.items.label" default="Items" />
		
	</label>
	<g:select name="items" from="${com.dumplingjoy.pos.AdjustmentOutItem.list()}" multiple="multiple" optionKey="id" size="5" value="${adjustmentOutInstance?.items*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adjustmentOutInstance, field: 'posted', 'error')} ">
	<label for="posted">
		<g:message code="adjustmentOut.posted.label" default="Posted" />
		
	</label>
	<g:checkBox name="posted" value="${adjustmentOutInstance?.posted}" />
</div>

