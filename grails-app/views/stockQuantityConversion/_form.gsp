<%@ page import="com.dumplingjoy.pos.StockQuantityConversion" %>



<div class="fieldcontain ${hasErrors(bean: stockQuantityConversionInstance, field: 'stockQuantityConversionNumber', 'error')} required">
	<label for="stockQuantityConversionNumber">
		<g:message code="stockQuantityConversion.stockQuantityConversionNumber.label" default="Stock Quantity Conversion Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="stockQuantityConversionNumber" required="" value="${fieldValue(bean: stockQuantityConversionInstance, field: 'stockQuantityConversionNumber')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockQuantityConversionInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="stockQuantityConversion.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${stockQuantityConversionInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockQuantityConversionInstance, field: 'postDate', 'error')} ">
	<label for="postDate">
		<g:message code="stockQuantityConversion.postDate.label" default="Post Date" />
		
	</label>
	<g:datePicker name="postDate" precision="day"  value="${stockQuantityConversionInstance?.postDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: stockQuantityConversionInstance, field: 'postedBy', 'error')} ">
	<label for="postedBy">
		<g:message code="stockQuantityConversion.postedBy.label" default="Posted By" />
		
	</label>
	<g:textField name="postedBy" value="${stockQuantityConversionInstance?.postedBy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockQuantityConversionInstance, field: 'items', 'error')} ">
	<label for="items">
		<g:message code="stockQuantityConversion.items.label" default="Items" />
		
	</label>
	<g:select name="items" from="${com.dumplingjoy.pos.StockQuantityConversionItem.list()}" multiple="multiple" optionKey="id" size="5" value="${stockQuantityConversionInstance?.items*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockQuantityConversionInstance, field: 'posted', 'error')} ">
	<label for="posted">
		<g:message code="stockQuantityConversion.posted.label" default="Posted" />
		
	</label>
	<g:checkBox name="posted" value="${stockQuantityConversionInstance?.posted}" />
</div>

