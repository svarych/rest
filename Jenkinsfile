pipeline {
    agent none
    stages {
        stage('Maven test') {
            agent {
//                docker {
//                    image 'maven:3-alpine'
//                }
//                docker {
//                    image 'maven:3.3.9-jdk-8'
//                }

//                docker {
//                    image 'openjdk:10-jdk'
//                }

                docker {
                    image 'openjdk:8-jdk'
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
