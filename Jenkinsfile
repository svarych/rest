pipeline {
    agent none

    stages {

        stage('Maven test') {
            agent {
//                docker { image 'maven:3-alpine' }
                dockerfile true
            }

            steps {
                sh 'allure --version'
                sh 'mvn --version'
                sh 'mvn clean test'

                sh 'allure generate ./target/surefire-reports/'
            }
        }
    }
}
