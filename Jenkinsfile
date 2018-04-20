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
    checkout scm
    def restContainer = docker.build("rest")

    node {
        agent {
            docker { image 'rest' }
        }
        stages {
            stage('Tests') {
                steps {
                    restContainer.inside() {
                        sh 'mvn clean test'
                    }
                }
            }
        }
    }
}
