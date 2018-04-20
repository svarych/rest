//node {
//    checkout scm
//
//    stage('Novaposhta API tests') {
//
//        def allure_container = docker.build("allure")
//
//        allure_container.inside() {
//            mvn clean test
//        }
//    }
//}
//

pipeline {
//    def restContainer = docker.build("rest")

    node {
        checkout scm

        agent {
            docker { image 'rest' }
        }
        stages {
            stage('Tests') {
                steps {

                        sh 'mvn clean test'

                }
            }
        }
    }
}
