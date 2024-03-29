# SOLIDBankApp

A small banking application using Spring annotations based on the SOLID principle and a UML class diagram. This application features account management, transaction processing, is secured with JWT (JSON Web Tokens) authentication, and is fully documented with Swagger for ease of use and collaboration.

### Getting started
1. Clone the repository: git clone https://github.com/laurabissoltan/solidbankapp.git
2. Open the project
3. Navigate to the src/main/java/kz/singularity/solidbankapp1 directory
4. Run the main method in Solidbankapp1Application.java to start the program by executing the command mvn spring-boot:run in the project directory

### Features

**Account Management:** Allows users to create, manage, and view bank accounts.

**Transaction Processing:** Supports making and viewing transactions between accounts.

**Security:** Utilizes JWT for secure authentication and authorization of users.

**API Documentation:*** Fully documented APIs using Swagger for easy testing and integration.

***In-Memory Database:*** Utilizes H2 Database for quick setup and testing purposes, making it easy to prototype and develop without the need for external database installations.

***Database Migration:*** Incorporates Flyway for version-controlled database migrations, ensuring smooth and consistent database schema evolution across environments.


### Using the Swagger UI

To view and interact with the API documentation, navigate to (http://localhost:8090/swagger-ui/index.html) after starting the application. Here, you can test API endpoints directly through the browser.

