# ========== 构建阶段 ==========
FROM eclipse-temurin:26-jdk AS build
WORKDIR /app

# 安装 wget 并下载 Maven
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

# 列出 target 目录内容，确认 JAR 文件存在及名称
RUN echo "=== Contents of /app/target/ ===" && ls -l /app/target/

# ========== 运行阶段 ==========
FROM eclipse-temurin:26-jre
WORKDIR /app

# 复制 JAR 并重命名为 app.jar（使用通配符）
COPY --from=build /app/target/app.jar /app/app.jar

# 列出 /app 目录内容，确认文件已复制
RUN echo "=== Contents of /app/ ===" && ls -l /app/

EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]
