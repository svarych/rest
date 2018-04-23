node {
    checkout scm

    def container = docker.build('rest')
    container.inside() {
        sh "echo 'Starting tests'"
    }

    stage('STAGE') {

    }
}
