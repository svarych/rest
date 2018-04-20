node {
    stage 'Checkout'
    checkout scm

    stage 'Build'
    def container = docker.build('rest')

    stage 'Test'
    container.inside() {
        sh 'mvn'
    }

}