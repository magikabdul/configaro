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
      discordSend description: "**Build** #${currentBuild.number}\n**Changes:** ${currentBuild.changeSets}",
        footer: "Result - ${currentBuild.currentResult}",
        result: currentBuild.currentResult,
        title: 'Configaro',
        webhookURL: ''
    }
  }
}
