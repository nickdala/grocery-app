FROM mcr.microsoft.com/openjdk/jdk:17-mariner AS build

WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

FROM mcr.microsoft.com/openjdk/jdk:17-mariner
RUN useradd -m webuser
USER webuser
EXPOSE 9001
COPY --from=build /workspace/app/target/groceryservice-0.0.1-SNAPSHOT.jar /app/groceryservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "/app/groceryservice-0.0.1-SNAPSHOT.jar"]