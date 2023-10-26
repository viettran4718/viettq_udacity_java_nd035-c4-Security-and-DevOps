pipeline {

    // agent {
    //     docker { image 'maven:3.9.4-openjdk-8-slim' }
    // }

    agent any
    tools {
        maven 'local_maven'
    }

    environment {
        // PROJECT_REPOSITORY_DIRECTORY = "starter_code"
        // APPLICATION_WAR_FILE = "target/ecommerce-0.0.1.war"
        // APPLICATION_CONTEXT = "$WORKSPACE/$PROJECT_REPOSITORY_DIRECTORY"
        APPLICATION_CONTEXT = "starter_code"
    }
    stages {

        stage ('Build') {

            steps {

                script {
                    sh 'cd $APPLICATION_CONTEXT && mvn -B -DskipTests clean package'
                }

            }

            post {
                success {
                    echo "Achiving the Artifacts"
                    achiveArtifacts artifacts: "**/*.war"
                }
            }

        }

        stage ('Deploy') {

            steps {

                deploy adapters: [tomcat8(credentialsId: '594a5d9e-3cda-4ee1-8adc-dbc274b3761a', path: '', url: 'http://171.224.24.124:8080/')], contextPath: 'starter_code', war: '**/*.war'

            }       // Steps

        } // Stage

    }   // Stages

}       // Pipeline