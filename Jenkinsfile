pipeline {

    agent {
        docker { image 'maven:3.9.4-openjdk-8-slim' }
    }

    environment {
        PROJECT_REPOSITORY_DIRECTORY = "starter_code"
        APPLICATION_WAR_FILE = "target/ecommerce-0.0.1.war"
        APPLICATION_CONTEXT = "$WORKSPACE/$PROJECT_REPOSITORY_DIRECTORY"
    }

    options {
        skipDefaultCheckout()
        skipStagesAfterUnstable()
    }

    stages {

        stage ('SCM Checkout') {

            steps {
                checkout scm
            }

        }

        stage ('Build') {

            steps {
                bat 'set'
                script {
                    sh 'cd $APPLICATION_CONTEXT && mvn -B -DskipTests clean package'
                }

            }

        }

        stage ('Deploy') {

            steps {

                script {
                    bat 'set'
                    def deploy_application = false

                    try {

                        echo 'Wait 5 minutes for the answer.'
                        timeout(time: 5, unit: 'MINUTES') {

                            deploy_application = input id: 'deploy_application',
                                message: 'Deploy the new version of the application?',
                                ok: 'Yes, deploy it.',
                                parameters: [
                                    [
                                        $class: 'BooleanParameterDefinition',
                                        defaultValue: true,
                                        successfulOnly: true,
                                        description: 'Please confirm you agree with this.',
                                        name: 'I agree with it.'
                                    ]
                                ]

                        }

                    } catch (error) {

                        def user = error.getCauses()[0].getUser()
                        if( 'SYSTEM' == user.toString() ) {
                            // The constant SYSTEM means that the build was cancelled by timeout.
                            echo 'No user interaction. Build timeout.'
                            currentBuild.result = 'ABORTED'
                            throw error
                        }
                        currentBuild.result = 'UNSTABLE'

                    }

                    if ( deploy_application == true ) {
                        // Deploying the application
                        sh 'cd $APPLICATION_CONTEXT && mvn clean package'

                        deploy adapters: [
                            tomcat8(url: 'http://udacity-tomcat:8080',
                            credentialsId: 'tomcat')
                        ],
                            war: 'starter_code/target/*.war',
                            contextPath: '/'

                    }

                }   // Script

            }       // Steps

        } // Stage

    }   // Stages

}       // Pipeline