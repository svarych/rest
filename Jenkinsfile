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
//                checkout scm
                sh 'allure --version'
                sh 'mvn --version'
                sh 'mvn clean test'
            }
        }
    }
}
