#!/bin/bash

# Define colors for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo -e "${BLUE}--- Starting Food Delivery App Orchestration ---${NC}"

# 1. Check if Docker is running
if ! docker info >/dev/null 2>&1; then
    echo -e "${RED}Error: Docker is not running. Please start Docker Desktop first.${NC}"
    exit 1
fi

# 2. Clean and Build the Java Monorepo
echo -e "${GREEN}Step 1: Building All Microservices and Common Lib...${NC}"
mvn clean package -DskipTests

if [ $? -ne 0 ]; then
    echo -e "${RED}Java build failed! Check your Maven logs.${NC}"
    exit 1
fi

# 3. Docker Orchestration
echo -e "${GREEN}Step 2: Refreshing Docker Environment...${NC}"
read -p "Do you want to reset databases (run init.sql scripts again)? (y/n) " -n 1 -r
echo
if [[ $REPLY =~ ^[Yy]$ ]]; then
    echo -e "${BLUE}Cleaning volumes and starting fresh...${NC}"
    docker-compose down -v
else
    echo -e "${BLUE}Restarting containers while keeping data...${NC}"
    docker-compose down
fi

# 4. Spin up the containers
echo -e "${GREEN}Step 3: Launching Services...${NC}"
docker-compose up --build -d

echo -e "${BLUE}--- System Status ---${NC}"
echo -e "Eureka Dashboard:  http://localhost:8761"
echo -e "Angular Frontend:  http://localhost:4200"
echo -e "----------------------------------------"
echo -e "Restaurant API:    http://localhost:9091/swagger-ui.html"
echo -e "Food Catalog API:  http://localhost:9092/swagger-ui.html"
echo -e "User Service API:  http://localhost:9093/swagger-ui.html"
echo -e "Order Service API: http://localhost:9094/swagger-ui.html"
echo -e "----------------------------------------"
echo -e "${GREEN}All services are initializing. Check Eureka to see them turn UP.${NC}"