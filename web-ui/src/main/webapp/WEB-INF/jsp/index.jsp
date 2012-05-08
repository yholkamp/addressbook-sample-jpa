<%@include file="include.jsp" %>
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
<content tag="title">Welcome</content>
<content tag="tagline">Have fun trying out the address book</content>

<div class="container">

    <!-- Main hero unit for a primary marketing message or call to action -->
    <div class="hero-unit">
        <h1>Address Book</h1>

        <p>
            A simple sample sandbox application utilizing either a standard JPA backend or CQRS/ES with a MongoDB
            powered backend. Developed as a research and sandbox project, two applications with similar functionality
            are created and their development time measured.
        </p>
    </div>

    <!-- Example row of columns -->
    <div class="row">
        <div class="span6">
            <h2>Open Source</h2>

            <p>This application and its counterpart have been developed using various open source applications as
                starting point and are
                therefore available under a license compatible with the Apache License v2.0.</p>

            <p>
                <a class="btn" href="https://github.com/nextpulse/addressbook-sample-mongodb">View
                    MongoDB-version &raquo;</a>
                &nbsp;
                <a class="btn" href="https://github.com/nextpulse/addressbook-sample-jpa">View JPA-version &raquo;</a>
            </p>
        </div>
        <div class="span4">
            <h2>Technologies used</h2>

            <p>
                Vaadin's <a href="http://dev.vaadin.com/svn/addons/JPAContainer/trunk/jpacontainer-addressbook-demo/">Addressbook
                Demo</a> and Axon's <a href="https://github.com/AxonFramework/Axon-trader">Trader</a> and
                <a href="https://github.com/AxonFramework/Addressbook-Sample">Addressbook</a> applications as starting
                points for both applications. <a href="http://twitter.github.com/bootstrap/index.html">Twitter's
                Bootstrap</a> for a little style.
            </p>

            <p>Below the surface the applications are powered by Spring MVC, Axon Framework, MongoDB and/or a wide
                variety of JPA adapters.</p>
        </div>
    </div>

    <hr>
</div>