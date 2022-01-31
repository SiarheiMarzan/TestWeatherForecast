FROM openjdk:11
COPY . /tests
WORKDIR /tests
ENTRYPOINT ["./gradlew"]
CMD ["test", "-i"]