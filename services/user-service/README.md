# User Service
Port: `9093` | DB: `MySQL`

## 📡 Key Endpoints (Swagger)
- `GET /api/v1/user/fetchAllUsers` - List all.
- `GET /api/v1/user//fetchById/{userId}` - Fetch a single User by ID.
- `POST /api/v1/user/addUser` - Create new.

## 💾 Database Details
- **Database:** `user_db`
- **Initialization:** Data is pre-loaded via `db-init/init.sql`.

## 🧪 Local Dev
To run this service in isolation:
1. Start its local DB: `docker-compose up -d` (in this folder).
2. Run app: `mvn spring-boot:run`.