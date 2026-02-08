# Restaurant Service
Port: `9091` | DB: `MySQL`

## 📡 Key Endpoints (Swagger)
- `GET /api/v1/restaurant/fetchAllRestaurants` - List all.

## 💾 Database Details
- **Database:** `restaurant_db`
- **Initialization:** Data is pre-loaded via `db-init/init.sql`.

## 🧪 Local Dev
To run this service in isolation:
1. Start its local DB: `docker-compose up -d` (in this folder).
2. Run app: `mvn spring-boot:run`.