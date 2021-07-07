def var_set = false  //to check if stage2 variable is already set

pipeline {
	agent any
	stages {
		stage('run stages in parallel') {
			parallel {
				stage('stage1') {
					steps {
						script {
							echo "Build number is ${currentBuild.number}"
						}
					}
				}
				stage('stage2') {
					steps {
						script {
							max = 100  // upper limit for random number
							random_num = "${Math.abs(new Random().nextInt(max))}"
							echo "The random number: ${random_num}"
							stash 'random_num'  // save variable for stage4
							var_set = true  // random number is generated
						}
					}
				}
				stage('stage3') {
					steps {
						script{
							def path_workspace = WORKSPACE  // get workspace path
							groovyscript = load("C:/Temp/check_free_space.groovy")  // load another groovy script
							groovyscript.freespace(path_workspace)  // run function and pass workspace path
						}
					}
				}
				stage('stage4') {
					steps {
						script {
						waitUntil {  // wait for stage2 to generate random number
							var_set
						}
							unstash 'random_num'  // load variable from stage2
							echo "The random number from stage2: ${random_num}"
						}
					}
				}
			}
		}
	}
}