pipeline {
    agent any
    stages {
        stage('Download sources') {
            steps {
                git credentialsId: 'b2c5042a-d992-49e5-994a-7ae4bfc4a0bf', url: 'git@github.com:coffeine-009/config.git'
            }
        }
        stage('Build') {
            steps {
                sh "./gradlew clean check build"
                sh "docker build --rm -t thecoffeine/config ."
            }
        }
        stage("Publish image") {
            steps {
                withDockerRegistry([credentialsId: 'thecoffeine', url: 'https://registry.hub.docker.com']) {
                    sh "docker push thecoffeine/config"
                }
            }
        }
    }

    post {
        always {
            archive 'build/libs/**/*.jar'
//            junit 'build/reports/**/*.xml'
        }
    }
}
