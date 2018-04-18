FROM maven:3.3.9-jdk-8
#
##WORKDIR /usr/src/novaposhta
#WORKDIR /rest
#
# Allure report
RUN curl -o allure-2.6.0.tgz -Ls https://dl.bintray.com/qameta/generic/io/qameta/allure/allure/2.6.0/allure-2.6.0.tgz \
    && tar -zxvf allure-2.6.0.tgz -C /opt/ \
    && ln -s /opt/allure-2.6.0/bin/allure /usr/bin/allure && allure --version
#
#RUN mkdir -p rest-api
#RUN cp /home/tober/Desktop/rest/. allure-report:/rest-api
#
#ENTRYPOINT ["/"]
#ENTRYPOINT ["/usr/local/bin/mvn-entrypoint.sh"]
