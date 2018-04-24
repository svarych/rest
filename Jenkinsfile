pipeline {
    agent none

    stages {

        stage('Maven test') {
            agent {
                dockerfile true
            }

            steps {
//                sh 'allure --version'
//                sh 'mvn --version'
                sh 'mvn clean test'

//                sh 'allure generate ./target/surefire-reports/ --clean'
//
//                publishHTML([reportName  : 'Api Tests Report', reportDir: 'allure-report', reportFiles: 'index.html',
//                             reportTitles: 'Novaposhta API tests', allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false])

                allure includeProperties: false, jdk: '', results: [[path: 'target/surefire-reports/']]

            }
        }
    }
}

