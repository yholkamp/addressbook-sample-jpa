<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<content tag="title">Contacts</content>
<content tag="tagline">Add phone number to contact</content>
<content tag="breadcrumb">
<ul class="breadcrumb">
	<li><a href="${ctx}/">Home</a> <span class="divider">/</span></li>
	<li><a href="${ctx}/contacts">Contacts</a> <span class="divider">/</span></li>
	<li><a href="${ctx}/contacts/${contact.identifier}"><c:out
				value='${contact.firstName} ${contact.lastName}' /></a> <span
		class="divider">/</span></li>
	<li class="active">Add phone number</li>
</ul>
</content>

<form:form commandName="phoneNumberEntry" cssClass="form-horizontal">
	<fieldset>
		<div class="control-group">
			<form:label path="phoneNumber" cssClass="control-label">Phone number</form:label>
			<div class="controls">
				<form:input path="phoneNumber" />
				<form:errors path="phoneNumber" cssClass="error-inline" />
			</div>
		</div>

		<div class="control-group">
			<form:label path="phoneNumberType" cssClass="control-label">Phone number type</form:label>
			<div class="controls">
				<form:select path="phoneNumberType">
					<form:options items="${enumValues}" />
				</form:select>
				<form:errors path="phoneNumberType" cssClass="error-inline" />
			</div>
		</div>

		<div class="span8">
			<input type="submit" name="submit" value="Submit"
				class="btn btn-primary" />
		</div>
	</fieldset>
</form:form>