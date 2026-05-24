FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

#cache the dependencies unless the change has been detected
RUN mvn dependency:go-offline -B

# copy the src folder inside the docker container
COPY src ./src

# package everything up in a single jar
RUN mvn clean package -DSkipTests

# run our build code
FROM eclipse-temurin:21-jdk AS runner

WORKDIR /app

COPY --from=builder ./app/target/billing-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]


