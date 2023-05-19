FROM maven:3.8.3-openjdk-17

WORKDIR /estore
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run
