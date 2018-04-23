node {

    checkout scm
    def container = docker.build('rest')
//
//    container.inside() {
        stage 'step' {
            sh 'mvn clean test'
        }
//    }
}