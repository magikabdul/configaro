pipeline {
    agent { label 'Configaro - remote host' }

    environment {
        LOGIN = credentials('digital-ocean-postrges-login')
        PASS = credentials('digital-ocean-postrges-password')
        DISCORD_WEBHOOK_URL = credentials('discord-webhook')
    }

    stages {
        stage('repository clone') {
            steps {
                git branch: 'develop', credentialsId: 'github-magikabdul', url: 'git@github.com:magikabdul/configaro.git'
            }
        }

        stage('maven tests') {
            steps {
                sh 'docker container run --rm -v ${PWD}:/configaro -v /root/.m2:/root/.m2 -w /configaro maven:3.8.4-openjdk-17 mvn clean test'
            }
        }

        stage('maven package') {
            steps {
                sh 'docker container run --rm -v ${PWD}:/configaro -v /root/.m2:/root/.m2 -w /configaro maven:3.8.4-openjdk-17 mvn -DskipTests clean package spring-boot:repackage'
            }
        }

        stage('configaro app - down') {
            steps {
                sh 'docker-compose down'
            }
        }

        stage('npm install') {
            steps {
                sh'''
                    rm -rf ${PWD}/frontend/build
                    docker container run --rm -v ${PWD}/frontend/:/configaro -w /configaro node:lts-slim npm i
                '''
            }
        }

        stage('npm build') {
            steps {
                sh'''
                    rm -rf ${PWD}/frontend/build
                    docker container run --rm -v ${PWD}/frontend/:/configaro -w /configaro node:lts-slim npm run build
                '''
            }
        }

        stage('resource copy - frontend') {
            steps {
                sh'''
                    rm -rf /root/nginx/html/*
                    cp -r ${PWD}/frontend/build/* /root/nginx/html/
                '''
            }
        }

        stage('resource copy - backend') {
            steps {
                sh'''
                    rm -rf ${PWD}/backend/*.jar
                    cp -r ${PWD}/backend/target/*.jar ${PWD}/backend
                '''
            }
        }

        stage('building - docker-compose backend image') {
            steps {
                sh'''
                    export SPRING_DATASOURCE_USERNAME=${LOGIN}
                    export SPRING_DATASOURCE_PASSWORD=${PASS}
                    docker-compose up --build -d
                '''
            }
        }

        stage('cleaning'){
            steps {
                sh 'docker image prune --filter="dangling=true" -f'
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
