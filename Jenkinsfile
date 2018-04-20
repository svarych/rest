node {
    stage 'Checkout'
    checkout scm

    stage 'Build'
    docker.build('rest')

    stage 'Test'
    sh 'mvn'
}