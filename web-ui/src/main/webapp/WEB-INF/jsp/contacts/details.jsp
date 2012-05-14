<%@include file="../include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<content tag="title">Contacts</content>
<content tag="tagline">View contact</content>
<content tag="breadcrumb">
    <ul class="breadcrumb">
        <li><a href="${ctx}/">Home</a> <span class="divider">/</span></li>
        <li><a href="${ctx}/contacts">Contacts</a> <span class="divider">/</span></li>
        <li class="active">Details for <c:out value='${contact.firstName} ${contact.lastName}'/></li>
    </ul>
</content>

<table class="table table-striped">
    <tr>
        <td>First Name</td>
        <td><c:out value='${contact.firstName}'/></td>
    </tr>

    <tr>
        <td>Last Name</td>
        <td><c:out value='${contact.lastName}'/></td>
    </tr>

    <tr>
        <td>Phone Number</td>
        <td><c:out value='${contact.phoneNumber}'/></td>
    </tr>

    <tr>
        <td>Street</td>
        <td><c:out value='${contact.street}'/></td>
    </tr>

    <tr>
        <td>City</td>
        <td><c:out value='${contact.city}'/></td>
    </tr>

    <tr>
        <td>Zip Code</td>
        <td><c:out value='${contact.zipCode}'/></td>
    </tr>

    <tr>
        <td>Department</td>
        <td><c:out value='${contact.department}'/></td>
    </tr>
</table>


