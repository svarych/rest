pipeline {
    agent none

    stages {

        stage('Maven test') {
            agent {
                dockerfile true
            }

            steps {
                sh 'allure --version'
                sh 'mvn --version'
                sh 'mvn clean test'

                sh 'allure generate ./target/surefire-reports/ --clean'

                publishHTML([reportName  : 'Api Tests Report', reportDir: 'allure-report', reportFiles: 'index.html',
                             reportTitles: 'Novaposhta API tests', allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false])

            }
        }
    }

    post {
        always {
            deleteDir()
        }
        failure {
            slackSend message: "${env.JOB_NAME} - #${env.BUILD_NUMBER} failed (<${env.BUILD_URL}|Open>)",
                    color: 'danger', teamDomain: 'qameta', channel: 'allure', tokenCredentialId: 'allure-channel'
        }
    }
}
