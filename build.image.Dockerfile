FROM maven:3.8.3-openjdk-17-slim as buil
ENV REPOSITORY_DIR=/otus_highload
WORKDIR $REPOSITORY_DIR
COPY . .
RUN mvn clean package

FROM openjdk:17-oracle
ENV APP_HOME=/otus_highload_app
WORKDIR $APP_HOME
EXPOSE 8080

COPY --from=buil /otus_highload/target/socialnetwork-0.0.1-SNAPSHOT.jar $APP_HOME/app.jar

CMD ["java", "-jar", "app.jar"]

#docker build . -t otus-highload-study:homework1 -f build.image.Dockerfile