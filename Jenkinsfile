pipeline {
  agent any
  
  environment {
	  DISCORD_WEBHOOK_URL = credentials('discord-webhook')
  }
  
  stages {
    stage('git clone') {
      steps {
        git branch: 'develop', credentialsId: 'github-magikabdul', url: 'git@github.com:magikabdul/configaro.git'
      }
    }
	  
	  stage ('mvn clean'){
			steps {
				sh 'mvn clean'
			}
	  }  
  }
  
  post {
    always {
      discordSend description: "**Build** #${currentBuild.number}\n**Changes:** ${currentBuild.changeSets}",
        footer: "Result - ${currentBuild.currentResult}",
        result: currentBuild.currentResult,
        title: 'Configaro',
        webhookURL: DISCORD_WEBHOOK_URL
    }
  }
}
