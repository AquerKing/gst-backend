FROM eclipse-temurin:26-jdk AS build
WORKDIR /app

RUN apt-get update && apt-get install -y wget && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

RUN wget https://archive.apache.org/dist/maven/maven-3/3.9.16/binaries/apache-maven-3.9.16-bin.tar.gz && \
    tar -xzf apache-maven-3.9.16-bin.tar.gz -C /opt && \
    rm apache-maven-3.9.16-bin.tar.gz

ENV MAVEN_HOME=/opt/apache-maven-3.9.16
ENV PATH=$MAVEN_HOME/bin:$PATH

COPY pom.xml .
RUN mvn dependency:go-offline -DskipTests

COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:26-jre
WORKDIR /app

RUN addgroup -g 1001 -S grassit && \
    adduser -u 1001 -S grassit -G grassit

RUN mkdir -p /var/lib/grassit && \
    chown -R grassit:grassit /var/lib/grassit

USER grassit:grassit

COPY --from=build /app/target/app.jar /app/app.jar

EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]
