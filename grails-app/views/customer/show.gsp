
<%@ page import="com.dumplingjoy.pos.Customer" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'customer.label')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="customer.code.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: customerInstance, field: "code")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="customer.name.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: customerInstance, field: "name")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="customer.contactPerson.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: customerInstance, field: "contactPerson")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="customer.contactNumbers.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: customerInstance, field: "contactNumbers")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="customer.faxNumber.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: customerInstance, field: "faxNumber")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="customer.address.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: customerInstance, field: "address")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="customer.paymentMode.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: customerInstance, field: "paymentMode")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="customer.terms.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: customerInstance, field: "terms.name")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="customer.bank.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: customerInstance, field: "bank")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="customer.branch.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: customerInstance, field: "branch")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="customer.creditLine.label" /></td>
                            <td valign="top" class="value"><g:formatNumber number="${customerInstance.creditLine}" format="#,##0" /></td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="customer.remarks.label" /></td>
                            <td valign="top" class="value">${fieldValue(bean: customerInstance, field: "remarks").replace("\n","<br/>")}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${customerInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
