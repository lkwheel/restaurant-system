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


## 📡 Service Registry & Ports

| Service | Port | DB Port (Host) |
| :--- | :--- | :--- |
| **Eureka Server** | 8761 | N/A |
| **Restaurant Service** | 9091 | 3306 (MySQL) |
| **Food Catalog Service** | 9092 | 3307 (MySQL) |
| **User Service** | 9093 | 3308 (MySQL) |
| **Order Service** | 9094 | 27017 (Mongo) |

### Frontend: 

- Angular 20
- Nginx

## ⚙️ Spring Profiles
- Local: Run services directly on your Mac (connects to localhost).
- Dev: Run services inside Docker (uses container networking).

## 🛠 Troubleshooting Databases

### 1. "Unable to determine Dialect" Error
This happens if the Java app starts before the Database is ready. 
- **Fix:** Ensure `SPRING_PROFILES_ACTIVE=dev` is set in your `docker-compose.yaml`.
- **Reset:** Run `docker-compose down -v` to clear stale volume states.

### 2. Connection Refused (Profile Mismatch)
- **Running on Mac (No Docker):** Use `-Dspring.profiles.active=default` (points to localhost).
- **Running in Docker:** Use `-Dspring.profiles.active=dev` (points to `catalog-db`).
- **Running in K8s:** Use `-Dspring.profiles.active=local` (points to K8s services).

### 3. Init Scripts Not Running
Docker only runs `init.sql` on the **first** creation of the volume. If you change your SQL, you must delete the volume:
`docker volume prune` or `docker-compose down -v`.