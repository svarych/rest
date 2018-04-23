node {

    checkout scm
    def container
    container = docker.build('rest')

    container.inside(){
        stage 'Test' {
            sh 'mvn'
        }
    }
}