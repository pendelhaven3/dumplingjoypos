
<%@ page import="com.dumplingjoy.pos.Product" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
            
            	<br/>
            	Code:&nbsp;&nbsp;&nbsp;<input type="text" name="code" id="code" value="${params.code}" />
            	<input type="button" value="Search" onclick="search()" />
            	<br/><br/>
            	
                <table>
                    <thead>
                        <tr>
                        	<g:sortableColumn property="code" title="Code" width="100" params="${[description: params.description]}" />
                        	<g:sortableColumn property="description" title="Description" params="${[description: params.description]}" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:if test="${!productInstanceList.empty}">
                    <g:each in="${productInstanceList}" status="i" var="productInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'} clickable" onclick="window.location='<g:createLink action='show' id='${productInstance.id}' />'">
                        
                            <td>${fieldValue(bean: productInstance, field: "code")}</td>
                        
                            <td>${fieldValue(bean: productInstance, field: "description")}</td>
                        
                        </tr>
                    </g:each>
                    </g:if>
                    <g:else>
                    	<tr>
                    		<td colspan="2">No products found</td>
                    	</tr>
                    </g:else>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${productInstanceTotal}" params="${[code: params.code]}" />
            </div>
        </div>
        <g:javascript>
        	focusOnLoad("code")
        	
			$("#code").keydown(function (e){
			    if(e.keyCode == 13){
			    	search()
			    }
			})
        	
        	function search() {
        		var code = $.trim($("#code").val());
        		window.location = "${createLink(action: 'list')}?code=" + encodeURIComponent(code)
        	}
        </g:javascript>
    </body>
</html>