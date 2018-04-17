node {
    checkout scm

    stage('Novaposhta tests') {
        def environment = docker.build('tober_test_rest_allure')
        environment.inside() {
//==============================================================================
            String commandParams = ''

// IGNORE FAILURES -------------------------------------------------------------
            if (env.MAVEN_IGNORE_FAILURES) {
                commandParams += " -Dmaven.test.failure.ignore=${env.MAVEN_IGNORE_FAILURES}"
            }

            sh "mvn clean test"
        }
    }

    stage('Results log') {
        junit allowEmptyResults: true, keepLongStdio: true, testResults: '**/target/surefire-reports/*.xml'
    }

    stage('Allure report') {
        sh allure generate -c target/surefire-reports
    }

//    stage('Allure publish') {
//        echo 'Publish allure report'
 //       publishHTML(
 //           target: [
 //               allowMissing         : false,
 //               alwaysLinkToLastBuild: false,
 //               keepAll              : true,
 //               reportDir            : 'allure-report',
 //               reportFiles          : 'index.html',
 //               reportName           : "Allure Report"
 //           ]
 //       )
 //   }

    stage('Artifacts') {
        archiveArtifacts 'build/reports/**/*'
    }
}