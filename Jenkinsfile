pipeline {
    agent none
    stages {

        stage('Maven test') {
            agent {
                docker {
                    image 'maven:3-alpine'
                }
            }

            steps {
                sh 'curl -o allure-2.6.0.tgz -Ls https://dl.bintray.com/qameta/generic/io/qameta/allure/allure/2.6.0/allure-2.6.0.tgz'
                sh 'tar -zxvf allure-2.6.0.tgz'
                sh 'ln -s allure-2.6.0/bin/allure /usr/bin/allure'
                sh 'allure --version'

                sh 'mvn --version'
                sh 'mvn clean test'
            }
        }
    }
}
