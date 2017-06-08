# My test work for CHI Software

[![Build Status](https://travis-ci.org/yarikpavlin/RESTfull_CRUD.svg?branch=master)](https://travis-ci.org/yarikpavlin/RESTfull_CRUD)


|   Method     | Description                            |
|--------------|----------------------------------------|
|    @GET      | It is used for read                    |
|              | localhost:8080/rest/users              |
|              | localhost:8080/rest/users/id={id}      |
|              | localhost:8080/rest/users/name={name}  |
|              |                                        |
|    @POST     | It is used for create new date         |
|              | localhost:8080/rest/users              |
|              |                                        |
|    @PUT      |It is used for update date              |
|              | localhost:8080/rest/users              |
|              |                                        |
|    @DELETE   |It is used for delete date              |
|              | localhost:8080/rest/users/delete={id}  |


<h2>Used technology</h2>

* Java
* JDBC
* H2 DataBase in-memory
* Jersey
* JSON
* Maven

## How run project? ##
1. Choose Run | Edit Configuration on the main menu
2. In the Edit Configuration dialog box that opens, expand the Tomcat Server: Local node and click Tomcat 9.0.0M17 The right-hand pane shows the settings of the automatically generated run configuration.
3. Then open the Deployment tab where the RESTCRUDwar exploded is added to the Deploy on the server startup list.All the other fields are filled in automatically or are optional, so just click OK to save the run configuration.
4. Click Run on the toolbar.After your default web browser starts and you see the application there.
5. In the address bar, type the following URL: localhsot:8080/rest/users
