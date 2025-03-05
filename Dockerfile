FROM openjdk

COPY rpn/. /app/

WORKDIR /app

CMD ["java", "-cp", "target/classes", "org.mrbriefcase.rpn"]