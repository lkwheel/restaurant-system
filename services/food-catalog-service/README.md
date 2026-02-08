# Food Catalog Service
Port: `9092` | DB: `MySQL`

## 📡 Key Endpoints (Swagger)
- `GET /api/v1/foodCatalog/findRestaurantAndFoodItemsById/${id}` - List all food items by restaurant ID.

## 💾 Database Details
- **Database:** `food_catalog_db`
- **Initialization:** Data is pre-loaded via `db-init/init.sql`.

## 🧪 Local Dev
To run this service in isolation:
1. Start its local DB: `docker-compose up -d` (in this folder).
2. Run app: `mvn spring-boot:run`.