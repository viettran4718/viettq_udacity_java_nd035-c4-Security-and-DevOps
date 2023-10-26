pipeline {
  agent {
    docker {
      image 'maven:3.9.4-openjdk-8-slim'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'cd $APPLICATION_CONTEXT && mvn -B -DskipTests clean package'
      }
    }

  }
  environment {
    PROJECT_REPOSITORY_DIRECTORY = 'starter_code'
    APPLICATION_WAR_FILE = 'project4-app'
    APPLICATION_CONTEXT = '$WORKSPACE/$PROJECT_REPOSITORY_DIRECTORY'
  }
}