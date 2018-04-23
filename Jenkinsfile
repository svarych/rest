node {
    checkout scm

    stage('Maven build') {
        docker.build('rest')
        docker run -t rest sh mvn
    }

//    def container = docker.build('rest')
//
//    stage('STAGE') {
//        container.inside() {
//            sh "echo 'Starting tests'"
//        }
//    }
}
