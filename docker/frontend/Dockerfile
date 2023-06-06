# Building the angular application #
# get node and set workdir #
FROM node:14-alpine as build
WORKDIR /app

# Install Angular CLI  to run build #
RUN npm install -g @angular/cli

# Copy package.json and package-lock.json to install dependencies #
COPY ./package.json .

# install dependencies #
RUN npm install

# Copy all files to the container #
COPY . .

# Build the app #
RUN ng build --configuration production

# running the application
FROM nginx as runtime

COPY --from=build /app/dist/frontend-eretekens /usr/share/nginx/html

