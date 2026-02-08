# Order Service
Port: `9094` | DB: `Mongo`

## 📡 Key Endpoints (Swagger)
- `POST /api/v1/order` - Create new.

## 💾 Database Details
- **Database:** `order_db`

## 🧪 Local Dev
To run this service in isolation:
1. Start its local DB: `docker-compose up -d` (in this folder).
2. Run app: `mvn spring-boot:run`.