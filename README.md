# Introduction
This is a skeleton of a web application.

It is intended to run on the Google App Engine (GAE).

It uses Google App Engine and the Spring Framework for server side technologies, 
and AngularJS and Bootstrap for the client side technologies.

# Dependencies
This web application was created using the Google App Engine plugin for Eclipse.  

It requires the Google App Engine runtime libraries, which are automatically included by the Eclipse plugin.  

In addition, it requires the Spring and Jackson libraries.

Included is a Maven pom file that contains the Spring and Jackson dependencies.  Please note that I experienced issues within Eclipse trying to add Maven functionality to the GAE project.  So, I ended up writing a script to call Maven and then copy the dependencies to the WEB-INF\lib location.


# Server Side
Because the Google App Engine only supports Java Servlets 2.5 and below, 
the Spring servlet configuration is initialized via XML (instead of entirely in Java).  

Two different Spring servlet configurations are used:

- The API controller configurations are java based (beans defined by annotations and component scanning).
- The WEB controller configurations are XML based (beans defined in the XML initialization).

The API is configured to produce and consume JSON using the Jackson library and Spring configuration.

# Client Side
The AngularJS demonstrates the creation of a service that interacts with the server API 
through asynchronous calls, and two controllers that make use of the service.

# Live Example
There is a live example running on the Google App Engine here:
[https://ktm-beta.appspot.com/](https://ktm-beta.appspot.com/)
