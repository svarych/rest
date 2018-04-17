node {
    checkout scm

    stage('Novaposhta API tests') {
        def environment = docker.build('tober_test_rest_allure')
        environment.inside() {
//==============================================================================
            String commandParams = ''

// IGNORE FAILURES -------------------------------------------------------------
            if (env.MAVEN_IGNORE_FAILURES) {
                commandParams += " -Dmaven.test.failure.ignore=${env.MAVEN_IGNORE_FAILURES}"
            }
            // sh -c "mvn clean test" + commandParams
        }
    }
}
