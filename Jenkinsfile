pipeline {
    agent any
    tools {
        maven 'maven-3.9.6'
    }

    stages {
        stage('Git Checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/ashinjimmy/department.git']])
                echo 'Git Checkout Completed'
            }
        }
        stage(' Maven Build') {
            steps {
                sh 'mvn clean package -DskipTests'
                echo 'Maven build Completed'
            }
        }
        stage('JUnit Test') {
            steps {
                // Run unit tests
                script {
                    try {
                        sh 'mvn clean test surefire-report:report' 
                    } catch (err) {
                        currentBuild.result = 'FAILURE'
                        echo 'Unit tests failed!'
                        error 'Unit tests failed!'
                    }
                }
                echo 'JUnit test Completed'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '''mvn clean verify sonar:sonar -Dsonar.projectKey=department -Dsonar.projectName='department' -Dsonar.host.url=http://localhost:9000''' //port 9000 is default for sonar
                    echo 'SonarQube Analysis Completed'
                }
            }
        }
        stage('Copy artifact to EC2') {
            steps {
                sshPublisher(
                    publishers: [
                        sshPublisherDesc(
                            configName: 'ansible-server',
                            transfers: [
                                sshTransfer(
                                    cleanRemote: false,
                                    excludes: '',
                                    execCommand: '',
                                    execTimeout: 120000,
                                    flatten: false,
                                    makeEmptyDirs: false,
                                    noDefaultExcludes: false,
                                    patternSeparator: '[, ]+',
                                    remoteDirectory: '//opt//cicd-deployment',
                                    remoteDirectorySDF: false,
                                    removePrefix: 'target',
                                    sourceFiles: 'target/*.jar'
                                )
                            ],
                            usePromotionTimestamp: false,
                            useWorkspaceInPromotion: false,
                            verbose: false
                        )
                    ]
                )
                echo 'Copying Jar to EC2 Completed'
            }
        }
        stage('Run Docker Container') {
            steps {
                sshPublisher(
                    publishers: [
                        sshPublisherDesc(
                            configName: 'ansible-server',
                            transfers: [
                                sshTransfer(
                                    cleanRemote: false,
                                    excludes: '',
                                    execCommand: '''
                                        cd /opt/cicd-deployment/
                                        ansible-playbook start_container.yml
                                    ''',
                                    execTimeout: 120000,
                                    flatten: false,
                                    makeEmptyDirs: false,
                                    noDefaultExcludes: false,
                                    patternSeparator: '[, ]+',
                                    remoteDirectory: '',
                                    remoteDirectorySDF: false,
                                    removePrefix: '',
                                    sourceFiles: ''
                                )
                            ],
                            usePromotionTimestamp: false,
                            useWorkspaceInPromotion: false,
                            verbose: false
                        )
                    ]
                )
                echo 'Deployment Completed'
            }
        }

    }
    post {
        failure {
            // This block will execute if any of the previous stages fail, including unit tests
            echo 'One or more stages have failed!'
            echo 'Pipeline Aborted'
        }
        always {
            echo 'always section'
            // Publish Surefire test results
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
        }
    }
}
