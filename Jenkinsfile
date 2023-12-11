pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master', credentialsId: 'testId-dev-github', url: 'https://github.com/doncoffee/help-desk.git'
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x ./gradlew'
                sh './gradlew clean build'
            }
        }
    }

    post {
        success {
            echo 'Build successful! Deploying...'
        }
        failure {
            echo 'Build failed! Notify someone...'
        }
    }
}
