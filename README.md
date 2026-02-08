# Food Delivery System (Monorepo)

A microservices-based food delivery platform built with **Java 25**, **Spring Boot 3.5.9**, and **Angular 20**.

## 🏗 Architecture
- **Services:** Restaurant, Food Catalog, User, and Order.
- **Shared:** `common-lib` (DTOs, Exceptions).
- **Frontend:** Angular SPA.

## 🚀 Quick Start (Mac/Linux)
Ensure Docker Desktop is running and you have Java 25 installed.

1. **Build & Launch:**
   ```bash
   chmod +x run-app.sh
   ./run-app.sh

2. Access Points:

- Frontend: http://localhost:4200
- Eureka: http://localhost:8761
- APIs: Swagger docs available at ports 9091-9094

## Tech Stack

### Backend

- Java 25
- Spring Data JPA/MongoDB
- Spring Cloud.

### Databases

- MySQL 9.1 (3 instances)
- MongoDB 7.0


|Service | Port | DB Port (Host) |
|--------|------|----------------|
Eureka | 8761 | N/A |
Restaurant | 9091 | 3306 |
Food Catalog | 9092 | 3307 |
User | 9093 | 3308 |
Order | 9094 | 27017 |

### Frontend: 

- Angular 20
- Nginx
