Addressbook Sample Applications
===========================

The Addressbook Sample MongoDB and JPA projects are sample applications implementing the same set of features; basic CRUD actions for contacts, adding and removing phone numbers to a contact as well as searching the list of contacts. These projects are created to function as sandbox applications for changes, new technologies and/or comparisons in development effort.


Key Features
-----------

- Seed data to provide a basic dataset for testing.
- CRUD actions for Contact entries.
- Create/delete actions for phone number entries.
- Search contacts by first and/or last name.


Used technologies
---------------

- Spring MVC
- Hibernate validator
- Jetty webserver
- Testing:
    - JUnit 4
    - Mockito for easy mocking of irrelevant parts of the system.
    - Sonar to monitor code quality.
- JPA version:
    - Hibernate ORM, Entitymanager.
    - H2 Database Engine as a basic in-memory testing database.
- MongoDB version:
    - Axon Framework to provide a CQRS and Event Sourcing toolkit.
    - MongoDB & Spring Java drivers.
    

Relevant projects
---------------

- [Addressbook Sample MongoDB](https://github.com/yholkamp/addressbook-sample-mongodb), the MongoDB-powered CQRS/ES version of this project.
- Vaadin's [JPAContainer Addressbook Demo](http://dev.vaadin.com/svn/addons/JPAContainer/trunk/jpacontainer-addressbook-demo/) served as a source of inspiration and as a starting point of the very first versions.
- Axon Framework's [Axon Trader](https://github.com/AxonFramework/Axon-trader) application provided insight and samples on how to configure Axon and set up the basic project structure. The [Addressbook Sample](https://github.com/AxonFramework/Addressbook-Sample) provided some additional information.
- Twitter's [Bootstrap](http://twitter.github.com/bootstrap/index.html) provided easy to use and pretty to look at building blocks the set up the web-ui for the project.
