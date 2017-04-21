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
                sh "./gradlew clean check build"
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
//            junit 'build/reports/**/*.xml'
        }
    }
}
