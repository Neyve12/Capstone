# Claims Management System
A full-stack Spring Boot application designed to manage customer claims efficiently. This system allows users to file claims, track their statuses, and upload documents, while providing administrators with tools for claim resolution.

Features
User Management:

Create, update, view, and delete users.
Validation for user details (e.g., email format, mandatory fields).
Claims Management:

Create, view, update, and delete claims.
Associate claims with specific users.
View all claims or filter by user.
Security:

Role-based access control (Admin and Customer roles).
Spring Security integration for secure endpoints.
Database:

MySQL for storing user and claim data.
Hibernate ORM for database interaction.
Validation and Error Handling:

Input validation using annotations (@NotBlank, @Email).
Global exception handling for user-friendly error messages.
Technologies Used
Backend:

Java 17
Spring Boot (Web, Security, Data JPA)
Hibernate ORM
Frontend:

Thymeleaf (for server-side rendering)
CSS and JavaScript
Database:

MySQL
Tools:

Postman (for testing APIs)
IntelliJ IDEA (for development)
GitHub (for version control)
Getting Started
Prerequisites
Java 17 or higher installed
MySQL database set up
Maven installed
Clone the Repository
bash
Copy code
git clone https://github.com/your-username/claims-management-system.git
cd claims-management-system
Set Up MySQL Database
Create a database:
sql
Copy code
CREATE DATABASE claims_db;
Update the application.properties file with your database credentials:
properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/claims_db
spring.datasource.username=your-username
spring.datasource.password=your-password
Run the Application
Build the project:

bash
Copy code
mvn clean install
Start the application:

bash
Copy code
mvn spring-boot:run
Access the application:

Backend API: http://localhost:8080
Frontend (if applicable): http://localhost:8080/ui
API Endpoints
User Endpoints
HTTP Method	Endpoint	Description
POST	/api/users	Create a new user
GET	/api/users	Retrieve all users
GET	/api/users/{id}	Retrieve a user by ID
Claim Endpoints
HTTP Method	Endpoint	Description
POST	/api/claims	File a new claim
GET	/api/claims	Retrieve all claims
GET	/api/claims/user/{id}	Retrieve claims for a user
Testing
Use Postman to test the APIs.
Ensure validation errors and exceptions are handled gracefully.
Run unit tests using Maven:
bash
Copy code
mvn test
Future Enhancements
Add file upload support for claim documents.
Implement email notifications for claim status updates.
Deploy the application on AWS or Heroku.
Author
Neidys Velasquez