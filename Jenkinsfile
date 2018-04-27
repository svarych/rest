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
            if (env.ADDRESSES){
                echo 'ADDRESSES'
            }
        }
    }
}