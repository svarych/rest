node {
    stage 'Checkout'
    checkout scm

    stahe 'Build'
    docker.build('rest')

    stage 'Test'
    sh 'mvn clean test'
}