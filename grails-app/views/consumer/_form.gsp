<%@ page import="snowbrr.Consumer" %>

<div class="fieldcontain ${hasErrors(bean: consumerInstance, field: 'driveway', 'error')} required">
	<label for="driveway">
		<g:message code="consumer.driveway.label" default="Driveway" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="driveway" name="driveway.id" from="${snowbrr.Driveway.list()}" optionKey="id" required="" value="${consumerInstance?.driveway?.id}" class="many-to-one"/>

</div>

<!-- insert user stuff here -->

<div class="fieldcontain ${hasErrors(bean: providerInstance?.user, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${providerInstance?.user?.username}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: providerInstance?.user, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${providerInstance?.user?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: providerInstance?.user, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="user.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${providerInstance?.user?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: providerInstance?.user, field: 'firstname', 'error')} required">
	<label for="firstname">
		<g:message code="user.firstname.label" default="Firstname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstname" required="" value="${providerInstance?.user?.firstname}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: providerInstance?.user, field: 'lastname', 'error')} required">
	<label for="lastname">
		<g:message code="user.lastname.label" default="Lastname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastname" required="" value="${providerInstance?.user?.lastname}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: providerInstance?.user, field: 'address', 'error')} required">
	<label for="address">
		<g:message code="user.address.label" default="Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="address" required="" value="${providerInstance?.user?.address}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: providerInstance?.user, field: 'city', 'error')} required">
	<label for="city">
		<g:message code="user.city.label" default="City" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="city" required="" value="${providerInstance?.user?.city}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: providerInstance?.user, field: 'province', 'error')} required">
	<label for="province">
		<g:message code="user.province.label" default="Province" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="province" required="" value="${providerInstance?.user?.province}"/>

</div>



<div class="fieldcontain ${hasErrors(bean: providerInstance?.user, field: 'avatar', 'error')} ">
	<label for="avatar">
		<g:message code="user.avatar.label" default="Avatar" />

	</label>
	<input type="file" id="avatar" name="avatar" />

</div>

<div class="fieldcontain ${hasErrors(bean: providerInstance?.user, field: 'latitude', 'error')} required">
	<label for="latitude">
		<g:message code="user.latitude.label" default="Latitude" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="latitude" value="${fieldValue(bean: providerInstance?.user, field: 'latitude')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: providerInstance?.user, field: 'longitude', 'error')} required">
	<label for="longitude">
		<g:message code="user.longitude.label" default="Longitude" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="longitude" value="${fieldValue(bean: providerInstance?.user, field: 'longitude')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: providerInstance?.user, field: 'phone', 'error')} ">
	<label for="phone">
		<g:message code="user.phone.label" default="Phone" />

	</label>
	<g:textField name="phone" value="${providerInstance?.user?.phone}"/>

</div>



</div>

