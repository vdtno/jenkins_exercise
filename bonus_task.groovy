def var_set = false

pipeline {
   agent any
   stages {
      stage('run stages in parallel') {
         parallel {
            stage('stage1 on windows') {
               agent {
                  label "windows"
               }
               steps {
                  script {
                     echo "Build number is ${currentBuild.number}"
                  }
               }
            }
            stage('stage2 on windows') {
               agent {
                  label "windows"
               }
               steps {
                  script {
                     max = 100
                     random_num = "${Math.abs(new Random().nextInt(max))}"
                     echo "The random number: ${random_num}"
                     stash 'random_num'
                     var_set = true
                  }
               }
            }
            stage('stage3 on linux') {
               agent {
                  label "linux"
               }
               steps {
                  script{
                     def path_workspace = WORKSPACE
                     util = load("C:/Temp/check_free_space.groovy")
                     util.freespace(path_workspace)
                  }
               }
            }
            stage('stage4 on windows') {
               agent {
                  label "windows"
               }
               steps {
                  script {
                  waitUntil {
                     var_set
                  }
                     unstash 'random_num'
                     echo "The random number from stage 2: ${random_num}"
                  }
               }
            }
         }
      }
   }
}