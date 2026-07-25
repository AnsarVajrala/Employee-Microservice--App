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
        bat 'dir'
        bat 'dir Employee-Service'
      }
    }
    stage('Build Department Service'){
      steps{
        bat 'dir'
        bat 'dir Department-Serivce'
      }
    }
    stage('Build Project Service'){
      steps{
        bat 'dir'
        bat 'dir Project-Serivce'
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
