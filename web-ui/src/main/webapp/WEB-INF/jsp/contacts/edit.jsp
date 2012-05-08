<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<content tag="title">Contacts</content>
<content tag="tagline">Update contact</content>
<content tag="breadcrumb">
    <ul class="breadcrumb">
        <li><a href="${ctx}/">Home</a> <span class="divider">/</span></li>
        <li><a href="${ctx}/contacts">Contacts</a> <span class="divider">/</span></li>
        <li class="active">Edit contact <c:out value='${contact.firstName} ${contact.lastName}'/></li>
    </ul>
</content>

<form:form commandName="contact" cssClass="form-horizontal">
    <form:hidden path="identifier"/>
    <%@include file="form.jsp" %>
</form:form>