<%@include file="../include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<content tag="title">Contacts</content>
<content tag="tagline">View contact</content>
<content tag="breadcrumb">
<ul class="breadcrumb">
	<li><a href="${ctx}/">Home</a> <span class="divider">/</span></li>
	<li><a href="${ctx}/contacts">Contacts</a> <span class="divider">/</span></li>
	<li class="active">Details for <c:out
			value='${contact.firstName} ${contact.lastName}' /></li>
</ul>
</content>

<div class="row">
	<p class="span12">
		<a href="${ctx}/contacts/${identifier}/edit" class="btn">Edit Contact</a>

		<a href="${ctx}/contacts/${identifier}/phonenumbers/new"
			class="btn pull-right">Add Phone Number</a>
	</p>
</div>

<div class="row">
	<div class="span6">
		<table class="table table-striped">
			<tr>
				<td>First Name</td>
				<td><c:out value='${contact.firstName}' /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><c:out value='${contact.lastName}' /></td>
			</tr>
			<tr>
				<td>Street</td>
				<td><c:out value='${contact.street}' /></td>
			</tr>
			<tr>
				<td>City</td>
				<td><c:out value='${contact.city}' /></td>
			</tr>
			<tr>
				<td>Zip Code</td>
				<td><c:out value='${contact.zipCode}' /></td>
			</tr>
			<tr>
				<td>Department</td>
				<td><c:out value='${contact.department}' /></td>
			</tr>
		</table>
	</div>

	<div class="span6">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Phone number</th>
					<th>Type</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${contact.phoneNumberEntries}" var="phoneNumber">
					<tr>
						<td><c:out value='${phoneNumber.phoneNumber}' /></td>
						<td><c:out value='${phoneNumber.phoneNumberType}' /></td>
						<td><a
							href="${ctx}/contacts/${identifier}/phonenumbers/${phoneNumber.identifier}/delete">Remove</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>