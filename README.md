# 🎬 StreamVibe Backend

A robust and scalable RESTful backend application for a modern video streaming platform, built with **Java 17**, **Spring Boot**, and **PostgreSQL**.

The project provides a comprehensive API ecosystem for managing landing page content, movies, TV shows, content metadata, seasons, episodes, reviews, subscription plans, and supported devices. It follows a clean **Layered Architecture (N-Tier Architecture)** to ensure maintainability, scalability, and separation of concerns.

---

## 📌 Overview

StreamVibe serves as the backend infrastructure for a video streaming platform similar to Netflix, Disney+, or Amazon Prime Video. It exposes REST APIs that allow frontend applications to retrieve and manage streaming content efficiently.

### Key Capabilities

* Dynamic landing page management
* Movie and TV show catalog management
* Content categorization and filtering
* Season and episode management
* User reviews and ratings
* Subscription plan management
* Supported devices management
* Swagger/OpenAPI documentation

---

## 🛠 Technology Stack

| Category      | Technology          |
| ------------- | ------------------- |
| Language      | Java 17             |
| Framework     | Spring Boot         |
| Persistence   | Spring Data JPA     |
| Database      | PostgreSQL          |
| Build Tool    | Gradle              |
| Documentation | Swagger / OpenAPI 3 |
| Utilities     | Lombok              |

---

## 🚀 Features

### 🏠 Landing Page Management

Manage all public-facing homepage sections:

* Hero slider content
* Frequently Asked Questions (FAQ)
* Genres section
* Pricing plans
* Supported devices

### 🎬 Movies & TV Shows

Manage and retrieve streaming content:

* Featured content
* Trending Top 10
* New releases
* Must-watch recommendations
* Genre-based categorization
* Content pagination

### 📖 Content Details

Provide detailed information for each content item:

* Synopsis and descriptions
* Cast members
* Directors
* Music contributors
* Available languages
* Trailer URLs

### 📺 TV Show Structure

Support multi-season TV series:

* Seasons listing
* Episodes listing
* Episode ordering

### ⭐ Community Features

Enable audience interaction through:

* User reviews
* Ratings
* Publication dates
* Review history

---

## 🏗 Architecture

The application follows a layered architecture approach:

```text
Client
   │
   ▼
Controller Layer
   │
   ▼
Service Layer
   │
   ▼
Repository Layer
   │
   ▼
PostgreSQL Database
```

### Layer Responsibilities

#### Controller Layer

Handles HTTP requests and responses.

#### Service Layer

Contains business logic and application rules.

#### Repository Layer

Manages data access operations through Spring Data JPA.

#### Database Layer

Stores application data persistently in PostgreSQL.

---

## 📦 Core Components

### Entity

Represents database tables as Java objects using JPA annotations.

### DTO (Data Transfer Object)

Transfers data between layers without exposing internal entity structures.

### Mapper

Handles conversion between Entities and DTOs.

---

## 📂 Project Structure

```text
src/main/java/com/aybeniz/streamvibe
│
├── config/
│   └── Application configurations
│
├── controller/
│   └── REST API endpoints
│
├── dto/
│   └── response/
│       └── Response DTOs
│
├── entity/
│   └── JPA entities
│
├── mapper/
│   └── Entity ↔ DTO converters
│
├── repository/
│   └── Data access layer
│
├── service/
│   └── Business logic layer
│
└── StreamVibeApplication.java
```

---

## 🗄 Database Configuration

Create a PostgreSQL database named:

```sql
CREATE DATABASE streamvibe;
```

Configure the datasource inside:

```properties
src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/streamvibe
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## ⚙️ Getting Started

### Prerequisites

Before running the project, make sure you have:

* Java 17+
* PostgreSQL
* Git
* Gradle (optional)

---

### Clone the Repository

```bash
git clone https://github.com/your-username/streamvibe.git
cd streamvibe
```

---

### Run the Application

#### Using Gradle

```bash
./gradlew bootRun
```

#### Using IntelliJ IDEA

1. Open the project as a Gradle project.
2. Allow dependencies to download.
3. Run `StreamVibeApplication.java`.

---

### Application URL

```text
http://localhost:8081
```

---

## 📡 API Endpoints

### Landing Page APIs

| Method | Endpoint     | Description                  |
| ------ | ------------ | ---------------------------- |
| GET    | /api/hero    | Retrieve hero slider content |
| GET    | /api/genres  | Get available genres         |
| GET    | /api/faqs    | Retrieve FAQ data            |
| GET    | /api/plans   | Get subscription plans       |
| GET    | /api/devices | Get supported devices        |

---

### Content APIs

| Method | Endpoint             | Description               |
| ------ | -------------------- | ------------------------- |
| GET    | /api/content/hero    | Featured content          |
| GET    | /api/content/genres  | Content genres            |
| GET    | /api/content/top-ten | Top trending content      |
| GET    | /api/content         | Paginated content listing |

---

### Content Detail APIs

| Method | Endpoint                  | Description        |
| ------ | ------------------------- | ------------------ |
| GET    | /api/content/{id}         | Content details    |
| GET    | /api/content/{id}/seasons | Seasons & episodes |
| GET    | /api/content/{id}/reviews | User reviews       |

---

## 📖 API Documentation

Swagger UI is available after the application starts:

```text
http://localhost:8081/swagger-ui/index.html
```

Use Swagger to:

* Explore endpoints
* Test API requests
* View request/response schemas
* Validate integrations

---

## 🔮 Future Improvements

* JWT Authentication & Authorization
* User Management Module
* Watchlist Feature
* Favorites System
* Search & Filtering
* Recommendation Engine
* Cloud Deployment (AWS/Docker)
* CI/CD Pipeline

---

## 👩‍💻 Author

**Aybəniz Əsgərxanova**



