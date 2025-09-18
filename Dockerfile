FROM openjdk:21
WORKDIR /app
COPY user_service_spring/user_service_spring.jar user_service_spring.jar
CMD ["java", "-jar", "user_service_spring.jar"]
EXPOSE 8080
