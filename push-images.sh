#!/bin/bash

# Configuration
DOCKER_USER="lkwheel"
TAG="1.0.0"
SERVICES=("eureka-server" "restaurant-service" "food-catalog-service" "user-service" "order-service" "frontend")

echo -e "\033[0;34m--- Starting Docker Hub Push for Version $TAG ---\033[0m"

# 1. Login to Docker Hub
echo "Logging into Docker Hub..."
docker login

# 2. Tag and Push each service
for SERVICE in "${SERVICES[@]}"
do
    echo -e "\033[0;32mProcessing $SERVICE...\033[0m"
    
    # Tag the local image for Docker Hub
    # Note: This assumes your local images are named after the service (check 'docker images')
    docker tag ${SERVICE}:latest ${DOCKER_USER}/food-delivery-${SERVICE}:${TAG}
    docker tag ${SERVICE}:latest ${DOCKER_USER}/food-delivery-${SERVICE}:latest

    # Push the versioned tag
    docker push ${DOCKER_USER}/food-delivery-${SERVICE}:${TAG}
    
    # Push the 'latest' tag
    docker push ${DOCKER_USER}/food-delivery-${SERVICE}:latest
done

echo -e "\033[0;34m--- Push Complete! ---\033[0m"