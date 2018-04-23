node {

    checkout scm

    def container

    stage 'Build' {
        container = docker.build('rest')
    }

    stage 'Test' {
        sh 'mvn'
    }
}