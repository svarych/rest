node {
    checkout scm

    stage('Novaposhta API tests') {

//        def environment = docker.build('allure-report')
        def env = docker.build("allure")

        env.inside() {
            sh 'mvn clean test'
        }
    }
}
