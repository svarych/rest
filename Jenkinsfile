pipeline {
    agent none

    stages {

        stage('Maven test') {
            agent {
                dockerfile true
            }

            steps {
                sh 'rm allure-results/* && mvn clean test -Dmaven.test.failure.ignore=true'
                allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
            }
        }
    }
}

