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
        dir('Employee-Serivce'){
          bat 'dir'
          bat 'mvnw.cmd clean install'
        }
      }
    }
    stage('Build Department Service'){
      steps{
        dir('Department-Serivce'){
           bat 'dir'
          bat 'mvnw.cmd clean install'
        }
      }
    }
    stage('Build Project Service'){
      steps{
        dir('Project-Serivce'){
           bat 'dir'
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
