node {

    def container

    stage 'Checkout'{
        checkout scm
    }

    stage 'Build'{
        container = docker.build('rest')
    }

    stage 'Test'{
        sh 'mvn'
    }
}