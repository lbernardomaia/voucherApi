FROM maven:3.3-jdk-8

WORKDIR home/app

COPY . .

RUN mvn clean package

ENTRYPOINT java -jar ./target/voucher-api-client-0.0.1-SNAPSHOT.jar
