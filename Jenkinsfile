node {
    checkout scm

    stage('Novaposhta API tests') {
        def environment = docker.build('allure-report')
//        environment.inside() {
//==============================================================================
            String commandParams = ''

// IGNORE FAILURES -------------------------------------------------------------
            if (env.MAVEN_IGNORE_FAILURES) {
                commandParams += " -Dmaven.test.failure.ignore=${env.MAVEN_IGNORE_FAILURES}"
            }

//            mvn clean test + commandParams
        }
    }
//}
