# Use an appropriate base image with JDK installed
FROM openjdk:11-jdk-slim

# Install Ant
RUN apt-get update && apt-get install -y ant && rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy the necessary build files and directories
COPY build.xml ./
COPY codenameone_settings.properties ./
COPY CodeNameOneBuildClient.jar ./
COPY src ./src
COPY lib ./lib
COPY native ./native

# Command to run Ant build
CMD ["ant", "clean", "build-for-ios-device"]
