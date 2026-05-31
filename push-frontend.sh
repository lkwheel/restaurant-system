# Set your variables
DOCKER_USER="lkwheel"
TAG="1.0.1-SNAPSHOT"

echo "--- Building Frontend ---"
docker build -t frontend:latest ./frontend/food-delivery-app

echo "--- Tagging ---"
docker tag frontend:latest ${DOCKER_USER}/food-delivery-frontend:${TAG}
docker tag frontend:latest ${DOCKER_USER}/food-delivery-frontend:latest

echo "--- Pushing ---"
docker push ${DOCKER_USER}/food-delivery-frontend:${TAG}
docker push ${DOCKER_USER}/food-delivery-frontend:latest