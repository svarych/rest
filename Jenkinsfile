node {
    checkout scm

    stage('Novaposhta API tests') {

        def allure_container = docker.build("allure")

        allure_container.inside() {
            sh 'mc'
        }
    }
}
