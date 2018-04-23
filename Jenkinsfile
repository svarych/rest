node {
    checkout scm

    def container = docker.build('rest')

    stage('STAGE') {
        container.inside() {
            sh "echo 'Starting tests'"
        }
    }
}
