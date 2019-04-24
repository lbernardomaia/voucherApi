FROM maven:3.3-jdk-8

WORKDIR home/app

COPY . .

RUN mvn clean package

CMD java -jar ./target/voucher-api-0.0.1-SNAPSHOT.jar

EXPOSE 8080