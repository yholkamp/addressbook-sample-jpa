<%@include file="../include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<content tag="title">Contacts</content>
<content tag="tagline">Manage your contacts</content>
<content tag="breadcrumb">
    <ul class="breadcrumb">
        <li><a href="${ctx}/">Home</a> <span class="divider">/</span></li>
        <li class="active">Contacts</li>
    </ul>
</content>

<p><a href="${ctx}/contacts/new">Create a new contact</a></p>

<table class="table table-striped">
    <thead>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone Number</th>
        <th>City</th>
        <th>Department</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${contacts}" var="contact">
        <tr>
            <td><c:out value='${contact.firstName}'/></td>
            <td><c:out value='${contact.lastName}'/></td>
            <td><c:out value='${contact.phoneNumber}'/></td>
            <td><c:out value='${contact.city}'/></td>
            <td><c:out value='${contact.department}'/></td>
            <td>
                <a href="${ctx}/contacts/<c:out value='${contact.identifier}'/>">details</a> &nbsp;
                <a href="${ctx}/contacts/<c:out value='${contact.identifier}'/>/edit">edit</a> &nbsp;
                <a href="${ctx}/contacts/<c:out value='${contact.identifier}'/>/delete">delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>