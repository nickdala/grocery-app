FROM mcr.microsoft.com/openjdk/jdk:17-mariner AS build

WORKDIR /workspace/app
EXPOSE 8080

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
COPY frontend frontend
COPY package-lock.json .
COPY package.json .
COPY tsconfig.json .
COPY types.d.ts .
COPY vite.config.ts .

RUN chmod +x ./mvnw
RUN ./mvnw clean package -Pproduction -DskipTests

FROM mcr.microsoft.com/openjdk/jdk:17-mariner
RUN useradd -m webuser
USER webuser
EXPOSE 8080
COPY --from=build /workspace/app/target/hilla-grocery-app-1.0-SNAPSHOT.jar /app/hilla-grocery-app-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "/app/hilla-grocery-app-1.0-SNAPSHOT.jar"]