pipeline {
    agent none

    stages {

        stage('Maven test') {
            agent {
                dockerfile true
            }

            steps {
                sh 'mvn clean test -Dmaven.test.failure.ignore=true && rm allure-results/*.json'
                allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
            }
        }
    }
}

