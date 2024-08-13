FROM openjdk:17-alpine

WORKDIR /app

RUN apt-get update && apt-get install -y tzdata
ENV TZ=Asia/Seoul

COPY build/libs/pilldispenser-0.0.1-SNAPSHOT.jar /app
COPY src/main/resources /app/resources

EXPOSE 443

ENTRYPOINT ["java", "-jar", "pilldispenser-0.0.1-SNAPSHOT.jar"]