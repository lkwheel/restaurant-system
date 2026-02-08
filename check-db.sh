#!/bin/bash

# Colors for better readability
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m'

echo -e "${BLUE}--- Database Data Verification ---${NC}"

# Function to query MySQL containers
query_db() {
    local container=$1
    local db_name=$2
    local table_name=$3
    local root_pass=$4

    echo -ne "${YELLOW}Checking ${container} (${table_name})... ${NC}"

    # Execute the count query inside the container
    count=$(docker exec $container mysql -u root -p${root_pass} -D ${db_name} -se "SELECT COUNT(*) FROM ${table_name};" 2>/dev/null)

    if [ $? -eq 0 ]; then
        echo -e "${GREEN}SUCCESS: ${count} rows found.${NC}"
    else
        echo -e "\033[0;31mFAILED: Could not query table or table doesn't exist yet.${NC}"
    fi
}

# 1. Check Restaurant DB
query_db "restaurant-system-restaurant-db-1" "restaurant_db" "restaurant" "root"

# 2. Check Food Catalog DB
query_db "restaurant-system-catalog-db-1" "food_catalog_db" "food_item" "root"

# 3. Check User DB
query_db "restaurant-system-user-db-1" "user_db" "user" "root"

# 4. Check Order DB (MongoDB)
echo -ne "${YELLOW}Checking order-db (order collection)... ${NC}"
mongo_count=$(docker exec restaurant-system-order-db-1 mongosh --quiet --username admin --password password --authenticationDatabase admin --eval "db.getSiblingDB('order_db').order.countDocuments()" 2>/dev/null)

if [ $? -eq 0 ]; then
    echo -e "${GREEN}SUCCESS: ${mongo_count} documents found.${NC}"
else
    echo -e "\033[0;31mFAILED: Could not query MongoDB.${NC}"
fi

echo -e "${BLUE}--- Check Complete ---${NC}"