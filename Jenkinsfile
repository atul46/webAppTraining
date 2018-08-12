pipeline {
    agent any

    stages {
        stage ('Compile Stage') {

            steps {
                withMaven(maven : 'maven_3_5_0') {
                    sh 'mvn clean'
                }
            }
        }

        stage ('Testing Stage') {

            steps {
                withMaven(maven : 'maven_3_5_0') {
                    sh 'mvn install'
                }
            }
        }


        stage ('Deployment Stage') {
            steps {
                withMaven(maven : 'maven_3_5_0') {
                    sh 'cd /var/lib/jenkins/workspace/jenikinsTraning/'
                    sh 'docker build -f Dockerfile -t empdetails .'
                    sh 'docker run empdetails'
                }
            }
        }
    }
}