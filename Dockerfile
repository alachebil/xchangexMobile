# Use an appropriate base image with JDK installed
FROM openjdk:11-jdk AS build

# Set the working directory
WORKDIR /app

# Copy the build.xml and other necessary files
COPY build.xml ./
COPY codenameone_settings.properties ./
COPY CodeNameOneBuildClient.jar ./
COPY src ./src
COPY lib ./lib
COPY native ./native

# Install Ant
RUN apt-get update && \
    apt-get install -y ant

# Install any additional dependencies (if needed)
# RUN apt-get install -y <dependency>

# Run Ant build
RUN ant clean build-for-ios-device

# Use an appropriate base image for running your app
FROM openjdk:11-jre

# Set the working directory
WORKDIR /app

# Copy the built artifacts from the build stage
COPY --from=build /app/dist /app/dist

# Set environment variables (if needed)
# ENV ENV_VAR=value

# Expose any ports if necessary
# EXPOSE 8080

# Define the entry point or command
# CMD ["java", "-jar", "/app/dist/your-app.jar"]

# For mobile or other specific platforms, the entry point might differ
