pipeline{
  agent any
  stages{
    stage('checkout'){
      steps{
        echo 'Repository checked out successfully'
      }
    }
    stage('Build Employee Service'){
      steps{
        dir('Employee-Service'){
          bat 'mvnw.cmd clean install'
        }
      }
    }
    stage('Build Department Service'){
      steps{
        dir('Department-Service'){
          bat 'mvnw.cmd clean install'
        }
      }
    }
    stage('Build Project Service'){
      steps{
        dir('Project-Service'){
          bat 'mvnw.cmd clean install'
        }
      }
    }
    stage('SonarQube Analysis'){
      steps{
        dir('Employee-Service'){
          withSonarQubeEnv('SonarQube'){
            bat 'mvnw.cmd clean verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar'
          }
        }
      }
  }
}
  post {
    success{
      echo 'Build Successful'
    }
    failure {
      echo 'Build failed'
    }
  }
}
