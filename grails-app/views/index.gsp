<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<sec:ifNotLoggedIn>
	<c:redirect url="/login" />
</sec:ifNotLoggedIn>

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
        </div>
        <div class="body">
            <br/>

            <div id="controllerList" class="dialog">
                <ul>
                    <li class="controller"><g:link controller="product">Product Maintenance</g:link></li>
                </ul>
            </div>
            
        </div>
    </body>
</html>