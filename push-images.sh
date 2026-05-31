#!/bin/bash

# Configuration
DOCKER_USER="lkwheel"
TAG="1.0.1-SNAPSHOT"
SERVICES=("eureka-server" "restaurant-service" "food-catalog-service" "user-service" "order-service")

echo -e "\033[0;34m--- Phase 1: Building & Tagging Docker Images ---\033[0m"

# 1. Build the Backend Images
for SERVICE in "${SERVICES[@]}"
do
  echo -e "\033[0;32mBuilding $SERVICE...\033[0m"
  # This command sends the code to Docker, which compiles the JAR internally
  docker build -t ${SERVICE}:latest -f services/${SERVICE}/Dockerfile .

  # Tag for Docker Hub
  docker tag ${SERVICE}:latest ${DOCKER_USER}/food-delivery-${SERVICE}:${TAG}
  docker tag ${SERVICE}:latest ${DOCKER_USER}/food-delivery-${SERVICE}:latest
done

# 2. Build the Frontend Image
echo -e "\033[0;32mBuilding Frontend...\033[0m"
docker build -t frontend:latest ./frontend/food-delivery-app
docker tag frontend:latest ${DOCKER_USER}/food-delivery-frontend:${TAG}
docker tag frontend:latest ${DOCKER_USER}/food-delivery-frontend:latest

echo -e "\033[0;34m--- Phase 2: Pushing to Docker Hub ---\033[0m"
docker login

# Push all (Backend + Frontend)
ALL_REPOS=("${SERVICES[@]}" "frontend")
for REPO in "${ALL_REPOS[@]}"
do
  docker push ${DOCKER_USER}/food-delivery-${REPO}:${TAG}
  docker push ${DOCKER_USER}/food-delivery-${REPO}:latest
done

echo -e "\033[0;34m--- Release $TAG Complete! ---\033[0m"
