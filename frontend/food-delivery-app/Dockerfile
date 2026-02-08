# Stage 1: Build the Angular app
FROM node:24.5.0-alpine AS build
WORKDIR /app

# Install dependencies first (better caching)
COPY package*.json ./
RUN npm install

# Copy the rest of the code and build
COPY . .
RUN npm run build -- --configuration production

# Stage 2: Serve the app with Nginx
FROM nginx:stable-alpine
# Copy the build output from the first stage
# Note: Angular 20 build output is usually in dist/food-delivery-app/browser
COPY --from=build /app/dist/food-delivery-app/browser /usr/share/nginx/html

# Copy a custom nginx config to handle Angular routing (Deep Linking)
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]