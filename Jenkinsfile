pipeline {
    agent any

    environment {
        // Define any environment variables here if needed
        DOCKER_IMAGE_NAME = 'imagetest'
        DOCKER_REGISTRY = 'alachebil'
        DOCKER_CREDENTIALS_ID = 'dockerHub'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the repository
                git 'https://github.com/choucheniheb/Pi_Dev_Cloud.git'
            }
        }

        stage('Build') {
            steps {
                // Build the Java project using Maven
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Run tests for the Java project
                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Build the Docker image
                script {
                    docker.build("${env.DOCKER_IMAGE_NAME}", ".")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                // Push the Docker image to the registry
                script {
                    docker.withRegistry("${env.DOCKER_REGISTRY}", "${env.DOCKER_CREDENTIALS_ID}") {
                        docker.image("${env.DOCKER_IMAGE_NAME}").push('latest')
                    }
                }
            }
        }
    }

    post {
        always {
            // Cleanup actions or notifications
            cleanWs()
        }
    }
}
