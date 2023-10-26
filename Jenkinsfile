pipeline {

    agent any
    tools {
        maven 'maven_3_9_5'
    }
    stages {

        stage ('Build') {

            steps {

                script {
                    sh 'cd starter_code && mvn -B -DskipTests clean package'
                }

            }

            post {
                success {
                    echo "Achiving the Artifacts"
                    archiveArtifacts artifacts: '**/target/*.war'
                }
            }

        }

        stage ('Deploy') {

            steps {

                deploy adapters: [tomcat8(credentialsId: 'tomcat', path: '', url: 'http://171.224.24.124:8080/')], contextPath: null, war: '**/*.war'

            }       // Steps

        } // Stage

    }   // Stages

}       // Pipeline