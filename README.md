# Best Route Finder

## Deliverying goods

This application simulates a logistics system, and it aims to calculate the best route for a delivery between two points.

The user is responsible for filling the logistics maps giving the following information: origin point, destiny point, distance between two points in kilometers, and a name for the map.
Examples:
- A B 10 
- B D 15
- A C 20
- C D 30
- B E 50
- D E 30

To retrieve the best route, the user must inform some data: origin point, destiny point, vehicle autonomy, and fuel price per liter.
For example: originPoint = "Point A",  destinyPoint = "Point D", vehicleAutonomy: 10, fuelPrice = 2.5. For this question, the best route, with the lower cost is: A -> B -> D, costing de 6.25.

--------------------------------------------------------------------------------------------------------------

## Tools and technologies utilized in this project:
- Java EE Web API 7
- Spring Context
- Spring Web
- Spring Data JPA
- Spring Test
- Maven
- JPA
- Hibernate
- PostgreSQL
- H2
- Git
- JSONdoc (for REST API docummenting)
- jQuery
- IntelliJ IDEA

### Motivation
Despite working with robusts technologies such as JSF, EJB, Glassfish and Jersey REST API, I chose to use the above mentioned technologies because they are simpler and lighter, avoiding so much boilerplate code. Thus, even never had developed applications using Spring nor Jetty, this experiencie allowed me to try some good newer technologies that are warm in IT market. So, this way I can build cleaner, lighter and objective Web applications.


### How to deploy and execute the application:
- Pre-requisites: Git, Maven 3, JDK 7 or above.
- With your favorite IDE or via terminal, clone my Git repository through "https://github.com/romeromfm/walmart-logistics.git"
- If necessary, change the Java version in pom.xml through param value ${java-version}
- [Optional] After clonning the project, on the download path directory, run "mvn compile" to compile all Java classes and download all necessary dependencies
- [Optional] Run "mvn test" to execute the tests
- PostgreSQL is the default database, however you can use a lighter db like H2. If want to use H2, just follow the instructions on section [Configuring H2 database in embedded mode]
- Download and install PostgreSQL, create a new database named "route-finder". User and password are 'postgres', as defined in database.properties
- To start the application server, go to the project folder and run "mvn jetty:run". It will download the necessary dependencies, compile the project and execute the tests. You can do it on your favorite IDE if you prefer. It is not necessary to download Jetty server, all you need is the Jetty Maven plugin
- After server has started, open your browser and access "localhost:8080". The API REST documentation page will be loaded
- You can do REST requests by this page or by a REST Client from your choice
- First of all, you need to populate the database. For this, use the resource /delivery through the POST method to create some logistics maps
- Now you can calculate the routes through the resource /delivery using the GET method

## Extras

### Configuring H2 database in embedded mode
- Open the configuration file "database.properties", comment PostgreSQL configuration and uncomment the H2 configs
- Change "jdbc.username" to "sa" and "jdbc.password" to empty string
- Open the configuration file "hibernate-config.properties" and change the dialect to H2
- You can manage the database using a H2 client. Download the offical client on "http://h2database.com/html/links.html#tools". To connect, use the default values, that is "jdbc:h2~/test"
- After downloading it, open the .jar. A window will be opened on your default browser, finally, connect on the default url "jdbc:h2:~/test"
