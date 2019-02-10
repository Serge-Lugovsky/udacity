properties([disableConcurrentBuilds()])

pipeline{

    agent {
        label 'master'
    }

    options {
        timestamps()
    }

    triggers{ cron('H/5 * * * *') }

    environment{
        ALLURE_RESULTS_DIR='target/allure-results'
        ALLURE_REPORTS_DIR='target/allure-reports'
        GIT_REPOSITORY_URL='https://github.com/Serge-Lugovsky/udacity'
        GIT_BRANCH='dev_exp'
        BROWSER="chrome"

        CHROMEDRIVER_URL="wget --no-check-certificate 'https://drive.google.com/uc?authuser=0&id=1Q8a-d6AivRNCkpdr2UPEtwqtgxEYeYp6&export=download' -O chromedriver"
        GECKODRIVER_URL="wget --no-check-certificate 'https://drive.google.com/uc?authuser=0&id=1MIcIAJ5xCI-9-I5VuzEUSvx9iMrhFpdo&export=download' -O geckodriver"
        APP_PROPERTIES="wget --no-check-certificate 'https://drive.google.com/uc?authuser=0&id=1cvntvXg5aJ7Gdxlx_soMoMYvnHWaNRhW&export=download' -O app.properties"
        ALLURE_PROPERTIES="wget --no-check-certificate 'https://drive.google.com/uc?authuser=0&id=1BfaN7O9DRbxUfyBRUiwmHkwEMcXhDrwu&export=download' -O allure.properties"
    }

    stages{

        stage('Get source code'){
            steps{
                git branch: "${GIT_BRANCH}", url: "${GIT_REPOSITORY_URL}"
            }
        }

        stage('Get resources'){
            steps{
//                 sh "cp -rf ${JENKINS_HOME}/projects_resources/${JOB_NAME}/resources/* ${WORKSPACE}/src/main/resources/"
                sh "cd src/main/resources/ && ${CHROMEDRIVER_URL} && ${GECKODRIVER_URL} && ${APP_PROPERTIES} " +
                        "&& ${ALLURE_PROPERTIES} && chmod 777 *"
            }
        }

        stage('Testing'){
            steps{
                sh "mvn clean test -P${BROWSER} -Pheadless"
            }
        }

        stage('Reports'){
            steps{
                allure([
                    includeProperties: false,
                    jdk: "java-1.8",
                    report: "${ALLURE_REPORTS_DIR}",
                    results: [[path: "${ALLURE_RESULTS_DIR}"]]
                ])
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
