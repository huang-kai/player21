FROM java:8-jre-alpine

RUN mkdir -p /app/log
COPY blackjack-player.jar  /app/blackjack-player.jar

WORKDIR /app/

ENTRYPOINT ["java","-jar","blackjack-player.jar"]
CMD []