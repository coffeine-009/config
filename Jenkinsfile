pipeline {
    agent any
    stages {
        stage('Download sources') {
            steps {
                git credentialsId: 'b2c5042a-d992-49e5-994a-7ae4bfc4a0bf', url: 'git@github.com:coffeine-009/config.git'
            }
        }
        stage('Build') {
            agent { docker 'gradle:jdk8' }
            steps {
                sh "./gradlew clean check build"
            }
        }
    }
}
