node {
    checkout scm
    stage('NP_API') {
        def container = docker.build('tob')
        container.inside() {
            String model = ''
            if (env.ADDRESSES.toBoolean()) {
                model += 'api2.LIVE.addresses.*,'
            }
            if (env.CATALOGUE.toBoolean()) {
                model += 'api2.LIVE.catalogue.*,'
            }
            if (env.COUNTERPARTIES.toBoolean()) {
                model += 'api2.LIVE.counterparties.*,'
            }
            if (env.INTERNET_DOCUMENT.toBoolean()) {
                model += 'api2.LIVE.internetDocument.*,'
            }
            if (env.REDIRECTING.toBoolean()) {
                model += 'api2.LIVE.redirecting.*,'
            }
            if (env.REGISTRY.toBoolean()) {
                model += 'Dtest=api2.LIVE.registry.*,'
            }

            sh 'rm allure-results/*.json && mvn clean test -Dmaven.test.failure.ignore=true -Dtest=' + model
            allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
        }
    }
}