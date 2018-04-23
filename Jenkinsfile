node {

    checkout scm
    def container = docker.build('rest')

    container.inside() {
        stage 'Test' {
            sh 'mvn clean test'
        }
    }
}