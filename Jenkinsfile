pipeline {
    agent any
    stages {
        stage('Build') {
            agent {
                docker {
                    image 'gradle:jdk8'
                    reuseNode true
                }
            }
            steps {
                sh "./gradlew clean assemble check build"
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
    }
}
