FROM maven:3.8.4-jdk-11

ENV HOME=/app

RUN mkdir -p $HOME

WORKDIR $HOME

# 1. add pom.xml only here

ADD pom.xml $HOME

# 2. start downloading dependencies

RUN mvn dependency:resolve

# 3. add all source code and start compiling

COPY . $HOME

RUN mvn clean install

CMD mvn spring-boot:run