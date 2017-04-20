pipeline {
    agent any
    stages {
        stage('Download sources') {
            steps {
                git credentialsId: 'b2c5042a-d992-49e5-994a-7ae4bfc4a0bf', url: 'git@github.com:coffeine-009/config.git'
            }
        }
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
                sh "docker build --rm -t thecoffeine/config ."
            }
        }
        stage("Publish image") {
            steps {
                script {
                    docker.withDockerRegistry([credentialsId: 'thecoffeine', url: 'https://registry.hub.docker.com']) {
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
