node {
    checkout scm

    stage('Novaposhta tests') {
        def environment = docker.build('tober_test_docker_build')
        environment.inside() {
//==============================================================================
            String commandParams = ''

// IGNORE FAILURES -------------------------------------------------------------
            if (env.MAVEN_IGNORE_FAILURES) {
                commandParams += " -Dmaven.test.failure.ignore=${env.MAVEN_IGNORE_FAILURES}"
            }
//==============================================================================
            String testName = ''
            if (env.TEST_CLASS) {
                testName += " -Dtest=${env.TEST_CLASS}"
            }

            if (env.TEST_NAME) {
                testName += "#${env.TEST_NAME}"
            }
//==============================================================================
            String allTests = ' -Dtest='
            if (env.SYSTEM) {
                allTests += "ua.novaposhta.test/${env.SYSTEM}"
            }
            if (env.ALL_TESTS) {
                allTests += ".${env.ALL_TESTS}"
            }

//          Удалять все говно!
            sh "echo 'Starting tests'"

//            sh "Xvfb :99 -ac -screen 0 1920x1080x24 &"
            sh "Xvfb :0 -ac -screen 0 1920x1080x24 &"
            sh "mvn clean test" + commandParams + testName + allTests
        }
    }
    stage('Results log') {
        junit allowEmptyResults: true, keepLongStdio: true, testResults: '**/target/surefire-reports/*.xml'
    }

//    stage('Allure report') {
//        sh allure generate -c target/surefire-reports
//    }

    stage('Allure publish') {
        echo 'Publish allure report'
        publishHTML(
            target: [
                allowMissing         : false,
                alwaysLinkToLastBuild: false,
                keepAll              : true,
                reportDir            : 'allure-report',
                reportFiles          : 'index.html',
                reportName           : "Allure Report"
            ]
        )
    }

    stage('Artifacts') {
        archiveArtifacts 'build/reports/**/*'
    }
}