Courier Tracking System - README 

📌 Introduction
This is a RESTful web application built with Spring Boot that tracks couriers' locations in real-time.
It receives streaming geolocation data from couriers and provides useful functionalities such as:

Logging when a courier enters a 100-meter radius of a Migros store
Calculating the total distance a courier has traveled
Efficient caching with Redis to optimize performance
PostgreSQL as the main database
The application follows best practices by implementing Observer Pattern & Chain of Responsibility Pattern for flexibility and maintainability.

📌 Tech Stack
Technology	Usage
Java 21	Backend development
Spring Boot	Framework for REST API
PostgreSQL	Database to store courier and store data
Redis	Caching and session handling
Docker & Docker Compose	Containerized deployment
JUnit & Mockito	Unit testing


📌 Installation & Setup
1️⃣ Prerequisites
Docker & Docker Compose installed
Java 21 & Maven installed (if running without Docker)

2️⃣ Run the Application with Docker (Recommended)
Simply clone this repository and run the following command:
docker-compose up --build

This will:
✅ Start the Spring Boot Application
✅ Start a PostgreSQL Database (with default courier_tracking schema)
✅ Start a Redis Cache

Manual Setup (Without Docker)
📌 Database Setup
If you prefer to run the project without Docker, you need to set up PostgreSQL manually:

1️⃣ Start PostgreSQL and create a database
psql -U postgres
CREATE DATABASE courier_tracking;

2️⃣ Update application.properties with your database credentials:
spring.datasource.url=jdbc:postgresql://localhost:5432/courier_tracking
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

3️⃣ Run the application:
mvn clean package
java -jar target/courier-tracking.jar

📌 API Endpoints
Method	Endpoint	                        Description
POST	/api/couriers	                    Create a new courier
GET	    /api/couriers	                    Get all couriers
POST	/api/couriers/location	            Update courier location
GET	    /api/couriers/{id}/total-distance	Get total traveled distance of a courier
GET	    /api/stores	                        Get all Migros store locations

📌 Use tools like Postman or cURL to test these endpoints.

📌 Design Patterns Used
✅ Observer Pattern → To notify when a courier enters a store radius
✅ Chain of Responsibility Pattern → For sequential request handling (e.g., Redis cache first, then DB)

📌 Running Unit Tests
To run all tests:
mvn test

✅ Tests cover:
Courier creation and retrieval
Courier location updates
Distance calculation logic
Store entry validation

📌 Troubleshooting
Issue	                                                        Solution
Error: Unable to access jarfile target/courier-tracking.jar	    Ensure the JAR file is built with mvn package before running
PostgreSQL connection error	                                    Check if PostgreSQL is running and the credentials are correct in application.properties
Redis not connecting	                                        Ensure Redis is running (docker ps should show Redis)


📌 Contributors
👤 Developer: [Mehmet Alper Karabay]
🔗 Contact: alperkarabay0671@gmail.com


