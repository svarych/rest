pipeline {
    agent none
    stages {

        stage('Docker build') {
            docker.build('rest')
        }

        stage('Maven test') {
            agent {
                docker {
//                    image 'maven:3-alpine'
                    image 'rest'
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
