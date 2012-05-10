<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<content tag="title">Contacts</content>
<content tag="tagline">Search contact</content>
<content tag="breadcrumb">
    <ul class="breadcrumb">
        <li><a href="${ctx}/">Home</a> <span class="divider">/</span></li>
        <li><a href="${ctx}/contacts">Contacts</a> <span class="divider">/</span></li>
        <li class="active">Search for <c:out value='searchValue'/></li>
    </ul>
</content>

<form:form commandName="searchvalue">
    <input type="submit" name="submit" value="Search"/>
</form:form>