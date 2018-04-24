pipeline {
    agent none
    stages {
        stage('Maven test') {
            agent {
                docker {
                    docker.build('rest')
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
