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

# Install envsubst (standard in alpine)
RUN apk add --no-cache gettext

WORKDIR /usr/share/nginx/html
# Copy build output
COPY --from=build /app/dist/food-delivery-app/browser .

# 1. Move the real config to a template
RUN mv config.json config.template.json

# 2. Create a startup script
RUN echo 'envsubst < config.template.json > config.json && exec nginx -g "daemon off;"' > /entrypoint.sh

RUN chmod +x /entrypoint.sh

EXPOSE 80
ENTRYPOINT ["/bin/sh", "/entrypoint.sh"]
CMD ["nginx", "-g", "daemon off;"]