FROM openjdk:21-jdk
ARG JAR_FILE=build/libs/one-click-recipe-v1.0.0.jar
COPY ${JAR_FILE} one-click-recipe.jar
ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "-jar", "-Dspring.profiles.active=prod", "/one-click-recipe.jar"]