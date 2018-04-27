pipeline {
    agent none

    String model = ''
    if (env.ADDRESSES) {
        model += ' -Dtest=api2.LIVE.addresses.*'
    }
    if (env.CATALOGUE) {
        model += ' -Dtest=api2.LIVE.catalogue.*'
    }
    if (env.COUNTERPARTIES) {
        model += ' -Dtest=api2.LIVE.counterparties.*'
    }
    if (env.INTERNET_DOCUMENT) {
        model += ' -Dtest=api2.LIVE.internetDocument.*'
    }
    if (env.REDIRECTING) {
        model += ' -Dtest=api2.LIVE.redirecting.*'
    }
    if (env.REGISTRY) {
        model += ' -Dtest=api2.LIVE.registry.*'
    }

    stages {
        stage('Maven test') {
            agent {
                dockerfile true
            }
            steps {
                sh 'rm allure-results/*.json && mvn clean test -Dmaven.test.failure.ignore=true' + model
                allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
            }
        }
    }
}

