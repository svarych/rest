node {

    checkout scm
    def container = docker.build('rest').inside(){
        stage 'Test' {
            sh 'mvn'
        }
    }

//    container.inside(){
//        stage 'Test' {
//            sh 'mvn'
//        }
//    }
}