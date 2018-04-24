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
            }
        }

//        stage('Back-end') {
//            agent {
//                docker { image 'maven:3-alpine' }
//            }
//            steps {
//                sh 'mvn --version'
//            }
//        }
//        stage('Front-end') {
//            agent {
//                docker { image 'node:7-alpine' }
//            }
//            steps {
//                sh 'node --version'
//            }
//        }
    }
}
