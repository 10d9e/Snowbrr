<%@ page import="snowbrr.Consumer" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'consumer.label', default: 'Consumer')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>

    <asset:stylesheet src="profile.css"/>
    <asset:javascript src="profile.js"/>
</head>

<body>
<a href="#show-consumer" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>

        <sec:ifAllGranted roles="ROLE_PROVIDER">
            <li><g:link class="edit" action="create" controller="consumerReview"
                        params="[reviewer: providerId, consumer: consumerInstance.id]">
                <g:message code="default.button.provider.complete.label" default="Rate Customer"/></g:link>
            </li>
        </sec:ifAllGranted>
    </ul>
</div>

<div id="show-consumer" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>

    <div class="col-lg-12 col-sm-12">
        <div class="card hovercard">
            <div class="card-background">
                <img class="card-bkimg" alt="" src="${assetPath(src: 'jay.png')}">
            </div>

            <div class="useravatar">
                <img alt="" src="${assetPath(src: 'jay.png')}">
            </div>
            <div class="type">
                <h2><span class="label label-info">Customer</span></h2>
            </div>
            <div class="card-info"> <span class="card-title">${consumerInstance?.user?.firstname} ${consumerInstance?.user?.lastname}</span>

            </div>
        </div>
        <div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
            <div class="btn-group" role="group">
                <button type="button" id="stars" class="btn btn-primary" href="#tab1" data-toggle="tab"><span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                    <div class="hidden-xs">Info</div>
                </button>
            </div>
            <div class="btn-group" role="group">
                <button type="button" id="favorites" class="btn btn-default" href="#tab2" data-toggle="tab"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
                    <div class="hidden-xs">Reviews</div>
                </button>
            </div>

        </div>

        <div class="well">
            <div class="tab-content">
                <div class="tab-pane fade in active" id="tab1">
                    <ul class="panel panel-default" style="list-style-type: none;">

                        <g:if test="${consumerInstance?.user}">
                            <li class="fieldcontain">
                                <span id="user-label" class="property-label"><g:message code="provider.user.label" default="User" /></span>

                                <span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${consumerInstance?.user?.id}">${consumerInstance?.user?.encodeAsHTML()}</g:link></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.username}">
                            <li class="fieldcontain">
                                <span id="username-label" class="property-label"><g:message code="user.username.label" default="Username" /></span>

                                <span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${consumerInstance?.user}" field="username"/></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.email}">
                            <li class="fieldcontain">
                                <span id="email-label" class="property-label"><g:message code="user.email.label" default="Email" /></span>

                                <span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${consumerInstance?.user}" field="email"/></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.firstname}">
                            <li class="fieldcontain">
                                <span id="firstname-label" class="property-label"><g:message code="user.firstname.label" default="Firstname" /></span>

                                <span class="property-value" aria-labelledby="firstname-label"><g:fieldValue bean="${consumerInstance?.user}" field="firstname"/></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.lastname}">
                            <li class="fieldcontain">
                                <span id="lastname-label" class="property-label"><g:message code="user.lastname.label" default="Lastname" /></span>

                                <span class="property-value" aria-labelledby="lastname-label"><g:fieldValue bean="${consumerInstance?.user}" field="lastname"/></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.address}">
                            <li class="fieldcontain">
                                <span id="address-label" class="property-label"><g:message code="user.address.label" default="Address" /></span>

                                <span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${consumerInstance?.user}" field="address"/></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.city}">
                            <li class="fieldcontain">
                                <span id="city-label" class="property-label"><g:message code="user.city.label" default="City" /></span>

                                <span class="property-value" aria-labelledby="city-label"><g:fieldValue bean="${consumerInstance?.user}" field="city"/></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.province}">
                            <li class="fieldcontain">
                                <span id="province-label" class="property-label"><g:message code="user.province.label" default="Province" /></span>

                                <span class="property-value" aria-labelledby="province-label"><g:fieldValue bean="${consumerInstance?.user}" field="province"/></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.country}">
                            <li class="fieldcontain">
                                <span id="country-label" class="property-label"><g:message code="user.country.label" default="Country" /></span>

                                <span class="property-value" aria-labelledby="country-label"><g:fieldValue bean="${consumerInstance?.user}" field="country"/></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.avatar}">
                            <li class="fieldcontain">
                                <span id="avatar-label" class="property-label"><g:message code="user.avatar.label" default="Avatar" /></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.latitude}">
                            <li class="fieldcontain">
                                <span id="latitude-label" class="property-label"><g:message code="user.latitude.label" default="Latitude" /></span>

                                <span class="property-value" aria-labelledby="latitude-label"><g:fieldValue bean="${consumerInstance?.user}" field="latitude"/></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.longitude}">
                            <li class="fieldcontain">
                                <span id="longitude-label" class="property-label"><g:message code="user.longitude.label" default="Longitude" /></span>

                                <span class="property-value" aria-labelledby="longitude-label"><g:fieldValue bean="${consumerInstance?.user}" field="longitude"/></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.phone}">
                            <li class="fieldcontain">
                                <span id="phone-label" class="property-label"><g:message code="user.phone.label" default="Phone" /></span>

                                <span class="property-value" aria-labelledby="phone-label"><g:fieldValue bean="${consumerInstance?.user}" field="phone"/></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.accountExpired}">
                            <li class="fieldcontain">
                                <span id="accountExpired-label" class="property-label"><g:message code="user.accountExpired.label" default="Account Expired" /></span>

                                <span class="property-value" aria-labelledby="accountExpired-label"><g:formatBoolean boolean="${consumerInstance?.user?.accountExpired}" /></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.accountLocked}">
                            <li class="fieldcontain">
                                <span id="accountLocked-label" class="property-label"><g:message code="user.accountLocked.label" default="Account Locked" /></span>

                                <span class="property-value" aria-labelledby="accountLocked-label"><g:formatBoolean boolean="${consumerInstance?.user?.accountLocked}" /></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.enabled}">
                            <li class="fieldcontain">
                                <span id="enabled-label" class="property-label"><g:message code="user.enabled.label" default="Enabled" /></span>

                                <span class="property-value" aria-labelledby="enabled-label"><g:formatBoolean boolean="${consumerInstance?.user?.enabled}" /></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.passwordExpired}">
                            <li class="fieldcontain">
                                <span id="passwordExpired-label" class="property-label"><g:message code="user.passwordExpired.label" default="Password Expired" /></span>

                                <span class="property-value" aria-labelledby="passwordExpired-label"><g:formatBoolean boolean="${consumerInstance?.user?.passwordExpired}" /></span>

                            </li>
                        </g:if>

                        <g:if test="${consumerInstance?.user?.transactions}">
                            <li class="fieldcontain">
                                <span id="transactions-label" class="property-label"><g:message code="provider.transactions.label" default="Transactions" /></span>

                                <g:each in="${consumerInstance?.user.transactions}" var="t">
                                    <span class="property-value" aria-labelledby="transactions-label"><g:link controller="transaction" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
                                </g:each>

                            </li>
                        </g:if>

                    </ul>
                </div>

                <div class="tab-pane fade in" id="tab2">

                    <g:render template="/shared/stats" model="[target: consumerInstance]" />
                    <g:render template="/shared/review" model="[target: consumerInstance]" />

                </div>

            </div>
        </div>

    </div>


    <g:form url="[resource: consumerInstance, action: 'delete']" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${consumerInstance}"><g:message code="default.button.edit.label"
                                                                                         default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
