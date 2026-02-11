# 🚗 Car Management System

A Java-based RESTful web application for managing car inventory with multiple persistence layer implementations (Hibernate, JDBC, In-Memory).

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Database Setup](#database-setup)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Usage Examples](#usage-examples)

## 🎯 Overview

This project is a **Car Management System** built with Java Enterprise Edition (Jakarta EE). It demonstrates a clean architecture implementation with **Repository Pattern**, **Service Layer**, and **RESTful API** design. The application supports multiple data persistence strategies, making it flexible and easy to switch between different database implementations.

## ✨ Features

- **CRUD Operations**: Create, Read, Update, and Delete cars from the inventory
- **RESTful API**: JSON-based HTTP API using Jakarta Servlets
- **Multiple Persistence Layers**:
    - **Hibernate ORM** - Object-Relational Mapping with PostgreSQL
    - **JDBC** - Direct database connectivity
    - **In-Memory Map** - HashMap-based storage for testing
- **Jackson JSON Processing**: Automatic serialization/deserialization
- **Layered Architecture**: Clean separation of concerns (Controller → Service → Repository)

## 🛠 Technologies Used

### Backend
- **Java** (JDK 11+)
- **Jakarta EE** (Jakarta Servlet API 5.0)
- **Hibernate ORM** - For database persistence
- **PostgreSQL** - Relational database
- **Jackson Databind** - JSON processing
- **Maven** - Build and dependency management

### Additional
- **JSP** - JavaServer Pages for frontend
- **JDBC** - Java Database Connectivity

## 🏗 Architecture

The application follows a **3-tier architecture**:

```
┌─────────────────┐
│   Presentation  │  ← CarServlet (HTTP/JSON API)
│      Layer      │    Client.java (Console client)
└────────┬────────┘
         │
┌────────▼────────┐
│   Service       │  ← CarService / CarServiceImpl
│    Layer        │    (Business Logic)
└────────┬────────┘
         │
┌────────▼────────┐
│  Repository     │  ← CarRepository implementations:
│    Layer        │    - CarRepositoryHibernate
│                 │    - CarRepositoryJdbc
│                 │    - CarRepositoryMap
└────────┬────────┘
         │
┌────────▼────────┐
│   Database      │  ← PostgreSQL
└─────────────────┘
```

### Design Patterns Used

1. **Repository Pattern**: Abstracts data access logic
2. **Service Layer Pattern**: Encapsulates business logic
3. **Dependency Injection**: Loose coupling between layers
4. **Strategy Pattern**: Interchangeable repository implementations

## 📁 Project Structure

```
car-management-system/
│
├── src/main/java/
│   ├── app/
│   │   ├── constants/
│   │   │   └── Constants.java          # Database configuration constants
│   │   ├── controller/
│   │   │   ├── CarController.java      # Business controller
│   │   │   └── CarServlet.java         # HTTP REST API controller
│   │   ├── domain/
│   │   │   └── Car.java                # Car entity/model
│   │   ├── repository/
│   │   │   ├── CarRepository.java      # Repository interface
│   │   │   ├── CarRepositoryHibernate.java  # Hibernate implementation
│   │   │   ├── CarRepositoryJdbc.java       # JDBC implementation
│   │   │   └── CarRepositoryMap.java        # In-memory implementation
│   │   └── service/
│   │       ├── CarService.java         # Service interface
│   │       └── CarServiceImpl.java     # Service implementation
│   └── client/
│       └── Client.java                 # Standalone test client
│
├── src/main/resources/
│   ├── Car.xml                         # Hibernate mapping file
│   └── postgres_config.xml             # Hibernate configuration
│
├── src/main/webapp/
│   ├── WEB-INF/
│   │   └── web.xml                     # Servlet deployment descriptor
│   └── index.jsp                       # Landing page
│
└── pom.xml                             # Maven dependencies
```

## 📋 Prerequisites

Before running this application, ensure you have:

- **Java Development Kit (JDK)** 11 or higher
- **Apache Maven** 3.6+
- **PostgreSQL** 12+ installed and running
- **Apache Tomcat** 9+ or any Jakarta EE compatible servlet container
- **IDE** (IntelliJ IDEA, Eclipse, or VSCode with Java extensions)

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/car-management-system.git
cd car-management-system
```

### 2. Install Dependencies

```bash
mvn clean install
```

## 🗄 Database Setup

### 1. Create PostgreSQL Database

```sql
-- Connect to PostgreSQL
psql -U postgres

-- Create database
CREATE DATABASE db_connection_java_test;

-- Connect to the new database
\c db_connection_java_test

-- Create car table
CREATE TABLE car (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    year INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

-- Insert sample data (optional)
INSERT INTO car (brand, year, price) VALUES
    ('Volkswagen', 2010, 10000.00),
    ('Mazda', 2015, 20000.00),
    ('Honda', 2020, 30000.00);
```

### 2. Verify Database Connection

```bash
psql -U postgres -d db_connection_java_test -c "SELECT * FROM car;"
```

## ⚙️ Configuration

### Update Database Credentials

**File: `src/main/java/app/constants/Constants.java`**

```java
public interface Constants {
    String DB_DRIVER_PATH = "org.postgresql.Driver";
    String DB_URL = "jdbc:postgresql://localhost:5432/";
    String DB_NAME = "db_connection_java_test";
    String DB_USERNAME = "postgres";        // ← Change this
    String DB_PASSWORD = "your_password";   // ← Change this
}
```

**File: `src/main/resources/postgres_config.xml`**

```xml
<property name="hibernate.connection.username">postgres</property>        <!-- Change this -->
<property name="hibernate.connection.password">your_password</property>  <!-- Change this -->
```

### Switch Repository Implementation

**File: `src/main/java/app/service/CarServiceImpl.java`**

Change the repository implementation:

```java
// Option 1: Hibernate (default - uses PostgreSQL)
private final CarRepository repository = new CarRepositoryHibernate();

// Option 2: JDBC (direct PostgreSQL connection)
private final CarRepository repository = new CarRepositoryJdbc();

// Option 3: In-Memory (for testing, no database needed)
private final CarRepository repository = new CarRepositoryMap();
```

## ▶️ Running the Application

### Option 1: Run as Console Application

```bash
mvn exec:java -Dexec.mainClass="client.Client"
```

### Option 2: Deploy to Tomcat

1. **Build WAR file**:
   ```bash
   mvn clean package
   ```

2. **Deploy to Tomcat**:
    - Copy `target/car-management-system.war` to Tomcat's `webapps/` directory
    - Start Tomcat server

3. **Access the application**:
    - Homepage: `http://localhost:8080/car-management-system/`
    - API: `http://localhost:8080/car-management-system/cars`

### Option 3: Run in IDE

1. Configure Tomcat server in your IDE
2. Deploy the project
3. Run/Debug the application

## 🌐 API Endpoints

### Base URL
```
http://localhost:8080/car-management-system/cars
```

### Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| `GET` | `/cars` | Get all cars | - | `Car[]` |
| `GET` | `/cars?id={id}` | Get car by ID | - | `Car` |
| `POST` | `/cars` | Create new car | `Car` | `Car` |
| `DELETE` | `/cars?id={id}` | Delete car by ID | - | - |

### Car Object Structure

```json
{
  "id": 1,
  "brand": "Volkswagen",
  "year": 2010,
  "price": 10000.00
}
```

## 💡 Usage Examples

### Get All Cars

**Request:**
```bash
curl -X GET http://localhost:8080/car-management-system/cars
```

**Response:**
```json
[
  {
    "id": 1,
    "brand": "Volkswagen",
    "year": 2010,
    "price": 10000.00
  },
  {
    "id": 2,
    "brand": "Mazda",
    "year": 2015,
    "price": 20000.00
  }
]
```

### Get Car by ID

**Request:**
```bash
curl -X GET "http://localhost:8080/car-management-system/cars?id=1"
```

**Response:**
```json
{
  "id": 1,
  "brand": "Volkswagen",
  "year": 2010,
  "price": 10000.00
}
```

### Create New Car

**Request:**
```bash
curl -X POST http://localhost:8080/car-management-system/cars \
  -H "Content-Type: application/json" \
  -d '{
    "brand": "Toyota",
    "year": 2022,
    "price": 35000.00
  }'
```

**Response:**
```json
{
  "id": 3,
  "brand": "Toyota",
  "year": 2022,
  "price": 35000.00
}
```

### Delete Car

**Request:**
```bash
curl -X DELETE "http://localhost:8080/car-management-system/cars?id=3"
```

**Response:** `200 OK`

## 🧪 Testing with Client.java

The project includes a standalone console client for testing:

```java
public static void main(String[] args) {
    CarController controller = new CarController();
    
    // Save cars
    Car car1 = controller.save("Volkswagen", 2010, new BigDecimal(10000));
    Car car2 = controller.save("Mazda", 2015, new BigDecimal(20000));
    
    // Get all cars
    List<Car> cars = controller.getAll();
    cars.forEach(System.out::println);
}
```

Run it:
```bash
mvn exec:java -Dexec.mainClass="client.Client"
```

## 🔧 Troubleshooting

### Common Issues

1. **Database Connection Failed**
    - Verify PostgreSQL is running: `sudo systemctl status postgresql`
    - Check credentials in `Constants.java` and `postgres_config.xml`
    - Ensure database exists: `psql -U postgres -l`

2. **Port Already in Use**
    - Change Tomcat port in `server.xml`
    - Kill process using port 8080: `sudo lsof -t -i:8080 | xargs kill`

3. **ClassNotFoundException**
    - Ensure PostgreSQL JDBC driver is in classpath
    - Run `mvn clean install` to download dependencies

4. **Hibernate Mapping Error**
    - Verify `Car.xml` is in `src/main/resources/`
    - Check entity class annotations match database schema

## 📝 Future Enhancements

- [ ] Add UPDATE (PUT) endpoint
- [ ] Implement pagination for GET all cars
- [ ] Add input validation
- [ ] Implement exception handling with custom error responses
- [ ] Add unit tests (JUnit, Mockito)
- [ ] Add authentication & authorization
- [ ] Implement logging (Log4j, SLF4J)
- [ ] Create Docker containerization
- [ ] Add Swagger/OpenAPI documentation

## 👤 Author

Your Name - DiAmo89 (https://github.com/DiAmo89)

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgments

- Jakarta EE Community
- Hibernate ORM Documentation
- PostgreSQL Community

---

**Note**: This is an educational project demonstrating Java Enterprise patterns and persistence layer implementations.