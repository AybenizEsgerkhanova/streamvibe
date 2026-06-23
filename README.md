# 🎬 StreamVibe Backend

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=black)

> A robust, RESTful backend application for a modern video streaming platform built with **Spring Boot** and **PostgreSQL**.

StreamVibe provides a comprehensive set of APIs to manage and serve landing page content, movies, TV shows, detailed content metadata, seasons, episodes, user reviews, pricing plans, and supported devices. The project strictly follows a layered architecture pattern to ensure clean separation of concerns, high maintainability, and scalability.

---

## 🚀 Features

### 🎞 Landing Page Content
* **Hero Slider:** Dynamic hero section management.
* **General Sections:** FAQs, genres, pricing plans, and supported devices APIs.

### 🍿 Movies & TV Shows Management
* **Content Discovery:** Featured content, top 10 trends, new releases, and must-watch recommendations.
* **Categorization:** Group content by genres and types.

### 📝 Content Details & Metadata
* **In-Depth Info:** Available languages, cast members, directors, music contributors, and trailer URLs.
* **Structured Viewing:** Ordered seasons and episode listings.
* **Community Driven:** User reviews, ratings, and publication dates tracking.

---

## 🛠 Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot, Spring Data JPA
* **Database:** PostgreSQL
* **Build Tool:** Gradle
* **Utilities:** Lombok (Boilerplate reduction)
* **API Documentation:** Swagger / OpenAPI 3.0

---

## 🏗 Architecture & Layer Responsibilities

The project leverages a standard **N-Tier (Layered) Architecture**:

```text
Client Request 
      │
      ▼
[ Controller ] ──> Handles incoming HTTP requests and structures responses.
      │
      ▼
[  Service   ] ──> Encapsulates business logic and application rules.
      │
      ▼
[ Repository ] ──> Interfaces with the database (Data Access Object).
      │
      ▼
[ PostgreSQL ] ──> Persistent data storage.


Entity: Maps Java objects to database tables.
DTO (Data Transfer Object): Transfers data safely between layers without exposing DB models.
Mapper: Handles conversion logic between Entities and DTOs.

📂 Project StructurePlaintextsrc/main/java/com/aybeniz/streamvibe
├── config/         # Application configurations (Swagger, Security, etc.)
├── controller/     # REST API endpoints
├── dto/              
│   └── response/   # Outgoing payload structures
├── entity/         # JPA Domain models
├── mapper/         # Entity-DTO mapping logic
├── repository/     # Spring Data JPA interfaces
└── service/        # Business logic implementation


🗄 Database ConfigurationThe application uses PostgreSQL. Ensure your local PostgreSQL server is running and create a database named streamvibe.
Update the src/main/resources/application.properties or application.yml file:Properties

spring.datasource.url=jdbc:postgresql://localhost:5432/streamvibe
spring.datasource.username=postgres
spring.datasource.password=your_secure_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

▶️ Getting Started1.
 Prerequisites
Java 17 or higher installed.
PostgreSQL installed and running.
Git installed.

2.Clone the Repository
Bash
git clone [https://github.com/your-username/streamvibe.git](https://github.com/your-username/streamvibe.git) cd streamvibe
3. Run the Application
Using Gradle CLI:Bash./gradlew bootRun
Using IntelliJ IDEA / Eclipse:Open the project as a Gradle project.Allow dependencies to resolve.Locate StreamvibeApplication.java and run it.The application will start on: http://localhost:8081
📡 Main API Endpoints

🏠 Landing PageMethodEndpointDescription
GET/api/heroRetrieve hero slider content
GET/api/genresGet all available genres
GET/api/faqsRetrieve FAQ section data
GET/api/plansGet subscription pricing plans
GET/api/devicesList supported devices

🎬 Movies & TV ShowsMethodEndpointDescription
GET/api/content/heroFetch featured hero content
GET/api/content/genresList available content genres
GET/api/content/top-tenGet top 10 trending content
GET/api/content Paginated content listing

ℹ️ Content DetailsMethodEndpointDescription
GET/api/content/{id}Get complete content metadata by ID
GET/api/content/{id}/seasonsList seasons and episodes for a TV Show
GET/api/content/{id}/reviewsFetch user reviews for specific content

📖 API DocumentationExplore and test the APIs directly via Swagger UI. Once the application is running, navigate to:
🔗 http://localhost:8081/swagger-ui/index.html

👩‍💻 AuthorAybəniz Əsgərxanova 
