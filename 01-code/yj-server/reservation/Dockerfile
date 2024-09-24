FROM alibabadragonwell/dragonwell:17-anolis

RUN mkdir -p /reservation

WORKDIR /reservation

ARG JAR_FILE=target/reservation-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

EXPOSE 7676

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

CMD java $JAVA_OPTS -jar app.jar

