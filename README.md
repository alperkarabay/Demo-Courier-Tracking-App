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



