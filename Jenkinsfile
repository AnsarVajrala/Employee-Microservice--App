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
