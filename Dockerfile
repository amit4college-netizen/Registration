# Build stage
FROM eclipse-temurin:21-jdk AS build

RUN apt-get update && apt-get install -y ant && rm -rf /var/lib/apt/lists/*
WORKDIR /app

COPY . .
RUN ant war

# Runtime stage
FROM tomcat:10.1-jdk21-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/dist/StudentRegistration.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]