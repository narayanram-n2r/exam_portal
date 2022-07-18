FROM openjdk:18-jdk
WORKDIR usr/src
ENV MONGO_DATABASE articledb
ENV MONGO_URL mongodb://localhost:27017/articledb
ENV MYSQL_PASSWORD root
ENV MYSQL_CI_URL jdbc:mysql://localhost:3307/userDb
ADD ./target/FavouritesService-0.0.1-SNAPSHOT.jar usr/src/FavouritesService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","FavouritesService-0.0.1-SNAPSHOT.jar"]