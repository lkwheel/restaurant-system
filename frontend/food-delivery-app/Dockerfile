# Stage 1: Build the Angular app
FROM node:24.5.0-alpine AS build

WORKDIR /app

# Install dependencies first (better caching)
COPY package*.json ./
RUN npm install

# Copy the rest of the code and build
COPY . .
# This command pulls values from src/environments/environment.prod.ts
RUN npm run build -- --configuration production

# Stage 2: Serve the app with Nginx
FROM nginx:stable-alpine

WORKDIR /usr/share/nginx/html

# Clean the default nginx directory
RUN rm -rf ./*

# Copy build output from Stage 1
# Note: Ensure the path 'dist/food-delivery-app/browser' matches your angular.json output path
COPY --from=build /app/dist/food-delivery-app/browser .

# Standard Nginx configuration is usually sufficient
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]