node {
    checkout scm

    stage('Novaposhta API tests') {
        def environment = docker.build('allure-report')
        environment.inside() {
            sh 'mvn clean test'
        }
    }
}
