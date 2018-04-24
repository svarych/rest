pipeline {
    agent none

    stages {

        stage('Maven test') {
            agent {
                dockerfile true
            }

            steps {
                sh 'mvn clean test'
                allure includeProperties: false, jdk: '', results: [[path: 'target/surefire-reports/']]
            }
        }
    }
}

