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
          bat 'mvn clean install'
        }
      }
    }
    stage('Build Department Service'){
      steps{
        dir('Department-Serivce'){
          bat 'mvn clean install'
        }
      }
    }
    stage('Build Project Service'){
      steps{
        dir('Project-Serivce'){
          bat 'mvn clean install'
        }
      }
    }
