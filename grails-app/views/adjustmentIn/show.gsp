
<%@ page import="com.dumplingjoy.pos.AdjustmentIn" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'adjustmentIn.label', default: 'AdjustmentIn')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-adjustmentIn" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-adjustmentIn" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list adjustmentIn">
			
				<g:if test="${adjustmentInInstance?.adjustmentInNumber}">
				<li class="fieldcontain">
					<span id="adjustmentInNumber-label" class="property-label"><g:message code="adjustmentIn.adjustmentInNumber.label" default="Adjustment In Number" /></span>
					
						<span class="property-value" aria-labelledby="adjustmentInNumber-label"><g:fieldValue bean="${adjustmentInInstance}" field="adjustmentInNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${adjustmentInInstance?.items}">
				<li class="fieldcontain">
					<span id="items-label" class="property-label"><g:message code="adjustmentIn.items.label" default="Items" /></span>
					
						<g:each in="${adjustmentInInstance.items}" var="i">
						<span class="property-value" aria-labelledby="items-label"><g:link controller="adjustmentInItem" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${adjustmentInInstance?.id}" />
					<g:link class="edit" action="edit" id="${adjustmentInInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
