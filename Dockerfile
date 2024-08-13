FROM openjdk:17-alpine

WORKDIR /app

RUN apk update && apk add tzdata
ENV TZ=Asia/Seoul

COPY build/libs/pilldispenser-0.0.1-SNAPSHOT.jar /app
COPY src/main/resources /app/resources

EXPOSE 443

ENTRYPOINT ["java", "-jar", "pilldispenser-0.0.1-SNAPSHOT.jar"]