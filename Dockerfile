FROM openjdk:11
COPY . /tests
WORKDIR /tests
ENTRYPOINT ["/bin/bash","-c","/tests/gradlew \"$@\"","-i"]