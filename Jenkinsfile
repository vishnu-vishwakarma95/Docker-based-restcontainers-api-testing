pipeline {
    agent any

    environment {
        JAVA_HOME = tool 'JDK11'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            node {
                    // Steps that require FilePath context, e.g., junit
                    junit '**/target/surefire-reports/*.xml'
                }
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
