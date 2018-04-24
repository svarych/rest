node {

    stage('Maven build') {

        checkout scm

        docker.build('rest')
        sh 'docker run -t rest mvn clean test'
    }

//    def container = docker.build('rest')
//
//    stage('STAGE') {
//        container.inside() {
//            sh "echo 'Starting tests'"
//        }
//    }
}
