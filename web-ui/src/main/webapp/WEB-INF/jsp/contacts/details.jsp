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

<table class="table hor-minimalist-b">
    <thead>
    <tr>
        <th>Type</th>
        <th>Street and number</th>
        <th>Zipcode</th>
        <th>City</th>
        <th>&nbsp;</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${addresses}" var="address">
        <tr>
            <td><c:out value='${address.addressType}'/></td>
            <td><c:out value='${address.streetAndNumber}'/></td>
            <td><c:out value='${address.zipCode}'/></td>
            <td><c:out value='${address.city}'/></td>
            <td><a href="${ctx}/contacts/${identifier}/address/delete/${address.addressType}">Remove</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>