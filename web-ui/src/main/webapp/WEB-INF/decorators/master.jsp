<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--
  ~ Copyright (c) 2010-2012. Axon Framework
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title><decorator:getProperty property="page.title"/></title>
    <meta name="description" content="Website contaning the Axon sample using a trader application">
    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
    <![endif]-->
    <link rel="stylesheet" href="${ctx}/style/bootstrap.css">
    <link rel="stylesheet" href="${ctx}/style/main.css"/>
    <script type="text/javascript" src="${ctx}/js/jquery-1.6.4.min.js"></script>

    <decorator:head/>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="${ctx}/">SimpleAddressbook</a>
            <ul class="nav">
                <li><a href="${ctx}/">Home</a></li>
                <li><a href="${ctx}/contacts">Contacts</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container">
    <decorator:getProperty property="page.herounit"/>

    <div class="content">
        <div class="page-header">
            <h1><decorator:getProperty property="page.title"/>
                <small><decorator:getProperty property="page.tagline"/></small>
            </h1>
        </div>
        <decorator:getProperty property="page.breadcrumb"/>
        <div class="row">
            <div class="span12">
                <decorator:body/>
            </div>
        </div>
    </div>

    <footer class="footer">
        <p>&copy; Gridshore 2011, ENovation 2012</p>
    </footer>

</div>
<!-- /container -->
</body>
</html>
