pipeline {
    agent any

    stages {
        stage('Test Stage') {
            steps {
                echo 'TEST STEP'
                sh 'chmod +x gradlew'
                sh './gradlew build'
            }
        }
    }
}