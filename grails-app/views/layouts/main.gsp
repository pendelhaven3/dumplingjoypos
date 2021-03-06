<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'jquery-ui-1.8.20.custom.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
		<g:javascript src="jquery/jquery-1.7.2.min.js" />
		<g:javascript src="jquery/jquery-ui-1.8.20.custom.min.js" />
		<g:javascript src="numberformat.js" />
		<g:javascript src="dumplingjoy.js" />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="grailsLogo">
        	<img src="${resource(dir:'images',file:'jcharmonypos_header.png')}" alt="Dumpling Joy Grocery]" border="0" />
        	<span id="programVersion">v<g:meta name="app.version" /></span>
        </div>
        <div class="loginHeader">
    		<sec:ifLoggedIn>
    			<sec:loggedInUserInfo field="username" /> | <a class="log" href="${createLink(uri: '/logout/index')}">Logout</a>
			</sec:ifLoggedIn>
		</div>
        <g:layoutBody />
    </body>
</html>