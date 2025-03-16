# 使用官方 OpenJDK 17 镜像作为基础
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 复制 JAR 文件到容器
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# 暴露端口（根据你的配置，可能为 80 或 8080）
EXPOSE 80

# 运行 JAR 文件
ENTRYPOINT ["java", "-jar", "app.jar"]