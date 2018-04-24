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
                checkout scm
                sh 'mvn --version'
                sh 'mvn clean test'
            }
        }
    }
}
