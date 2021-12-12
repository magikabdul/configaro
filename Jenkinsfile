pipeline {
  agent any
  
  stages {
    stage('git clone') {
      steps {
        git branch: 'develop', credentialsId: 'github-magikabdul', url: 'git@github.com:magikabdul/configaro.git'
      }
    }
  }
  
  post {
    always {
      
    }
  }
}
