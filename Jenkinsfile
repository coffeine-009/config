pipeline {
    agent any
    stages {
        stage('Build') {
            agent {
                docker {
                    image 'gradle:jdk8'
                    reuseNode true
                    args '-v ~/.ssh:/root/.ssh'
                }
            }
            steps {
                sh "./gradlew clean assemble check build clean release"
            }
        }
        stage('Build image') {
            steps {
                script {
                    docker.build('thecoffeine/config')
                }
            }
        }
        stage("Publish image") {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'thecoffeine') {
                        docker.image('thecoffeine/config').push('latest')
                    }
                }
            }
        }
    }

    post {
        always {
            archive 'build/libs/**/*.jar'
            junit 'build/test-results/**/*.xml'
        }
        success {
            slackSend channel: '#release',
                color: 'good',
                message: "@channel Configuration server has been released. \nVersion:${env.BUILD_NUMBER}."
        }
        failure {
            slackSend channel: '#release',
                color: 'danger',
                message: "@channel Configuration server hasn't been released. \nVersion:${env.BUILD_NUMBER} is failed."
        }
        unstable {
            slackSend channel: '#release',
                color: 'warning',
                message: "@channel Configuration server's build #${env.BUILD_NUMBER} is unstable."
        }
        changed {
            slackSend channel: '#release',
                color: 'warning',
                message: "@channel Configuration server's build #${env.BUILD_NUMBER} is changed."
        }
    }
}
