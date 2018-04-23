node {
    checkout scm

    stage('RestAPI') {
        def container = docker.build('rest')
        container.inside(){
            sh "echo 'Starting tests'"
        }
    }
}

//node {
//
//    def credentialsId = '768af34d-1c49-4f1c-b347-19e5def9afaa'
//    def awisRep = 'ssh://git@stash.np.ua:7999/rpz/awis.git'
//    def buildPatch = pwd().replace('var', 'storage01')
//    def exakat = docker.image('exakat/exakat:1.2.3')
//    def command = "docker run -i -e JAVA_OPTIONS='-Xms1024m -Xmx8096m' -v $buildPatch/configs:/usr/src/exakat/config -v $buildPatch:/myhome -v $buildPatch/../projects:/usr/src/exakat/projects exakat/exakat "
//    stage('Prepare') {
//        clear()
//        git branch: 'master', credentialsId: credentialsId, url: awisRep
//        sh 'mkdir -p ' + pwd() + '/../projects'
//        sh 'rm -rf tests'
//        sh 'rm -rf solution/autogen'
//        sh 'rm -rf libraries/printipp'
//        sh 'rm -rf libraries/tcpdf'
//        sh 'rm -rf libraries/tcpdf_v6'
//        sh 'rm -rf static'
//        iniText = """
//graphdb = 'gsneo4j';
//
//; where is neo4j inside a gremlin server host
//gsneo4j_host     = '127.0.0.1';
//gsneo4j_port     = '8182';
//gsneo4j_folder   = 'tinkergraph';
//
//phpversion = 7.1
//
//php71 = /usr/local/bin/php
//
//token_limit = 10000000
//
//; Default themes to run
//project_themes[] = 'CompatibilityPHP56';
//project_themes[] = 'CompatibilityPHP70';
//project_themes[] = 'CompatibilityPHP71';
//project_themes[] = 'CompatibilityPHP72';
//project_themes[] = 'Analyze';
//project_themes[] = 'Preferences';
//project_themes[] = 'Appinfo';
//project_themes[] = 'Appcontent';
//project_themes[] = '"Dead code"';
//project_themes[] = 'Security';
//project_themes[] = 'Custom';
//
//; Default reports to generate
//project_reports[] = 'Ambassador';
//        """
//        sh 'mkdir -p configs'
//        writeFile file: 'configs/exakat.ini', text: iniText
//    }
//
//    stage('Init') {
//        sh command + 'version'
//        sh command + 'init -p awis -symlink -R /myhome -v'
//        sh command + 'update -p awis -v'
//    }
//
//    stage('Analyze') {
//        sh command + 'project -p awis -v'
//        sh command + 'report -p awis -format Ambassador -file awis_report'
//        publishHTML([
//                allowMissing: false,
//                alwaysLinkToLastBuild: false,
//                keepAll: false,
//                reportDir: pwd() + '/../projects/awis/awis_report/',
//                reportFiles: 'index.html',
//                reportName: 'Exakat Report', reportTitles: ''
//        ])
//    }
//}
//
//def clear() {
//    sh 'ls -a | grep -e "[^.git]" | xargs rm -rf'
//}