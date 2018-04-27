//pipeline {
//    agent none
//
//    stages {
//
//        stage('Build') {
//            agent {
//                dockerfile true
//            }
//            def environment = docker.build('api_tests_container')
//            environment.inside() {
//                stage('Maven test') {
//                    steps {
//                        sh 'rm allure-results/*.json && mvn clean test -Dmaven.test.failure.ignore=true' + model
//                        allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
//                    }
//                }
//            }
//        }
//    }
//}


node {
    stage('NP_API') {
        def container = docker.build('tob')
        container.inside() {
            String model = ''
            if (env.ADDRESSES.toBoolean()) {
                model += ' -Dtest=api2.LIVE.addresses.*'
            }
            if (env.CATALOGUE.toBoolean()) {
                model += ' -Dtest=api2.LIVE.catalogue.*'
            }
            if (env.COUNTERPARTIES.toBoolean()) {
                model += ' -Dtest=api2.LIVE.counterparties.*'
            }
            if (env.INTERNET_DOCUMENT.toBoolean()) {
                model += ' -Dtest=api2.LIVE.internetDocument.*'
            }
            if (env.REDIRECTING.toBoolean()) {
                model += ' -Dtest=api2.LIVE.redirecting.*'
            }
            if (env.REGISTRY.toBoolean()) {
                model += ' -Dtest=api2.LIVE.registry.*'
            }

            echo 'TOBERRRRRRRRRRRRRRRRRRRRRRR' + model

        }
    }
}