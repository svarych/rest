FROM node:7-alpine

RUN echo "HELLO, WORLD!"

RUN apt-get install curl

# Allure report
RUN curl -o allure-2.6.0.tgz -Ls https://dl.bintray.com/qameta/generic/io/qameta/allure/allure/2.6.0/allure-2.6.0.tgz \
    && tar -zxvf allure-2.6.0.tgz -C /opt\
    && ln -s /opt/allure-2.6.0/bin/allure /usr/bin/allure && allure --version