node {
    checkout scm

    stage('RestAPI') {
        def container = docker.build('rest')
        container.inside(){
            sh "echo 'Starting tests'"
        }
    }
}
