node {

//    checkout scm
//
//    stage('Maven build') {
//
//        docker.build('rest')
//        sh 'docker run -t rest mvn clean test'
//    }

    def container = docker.build('rest')
//
    stage('STAGE') {
        container.inside() {
            sh "echo 'Starting tests'"
        }
    }
}
