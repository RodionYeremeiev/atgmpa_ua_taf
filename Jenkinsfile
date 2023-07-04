pipeline {
    agent any

    stages {
        stage('Run API tests') {
            steps {
                echo 'Running api tests'
                sh 'chmod +x gradlew'
                sh 'clean test -Dusername=TESTUSERATGMPA -Dpassword=Test1111 -Dtoken=$REPORT_PORTAL_TOKEN'
            }
        }
        stage('Run UI tests') {
            steps {
                echo 'Running api tests'
                sh 'chmod +x gradlew'
                sh 'clean test -Dusername=TESTUSERATGMPA -Dpassword=$TEST_USER_PASSWORD -DzephyrApiToken=$ZEPHYR_API_TOKEN'
            }
        }
    }
}