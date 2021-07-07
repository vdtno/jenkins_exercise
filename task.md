Write a Jenkins declarative pipeline script which executes 4 stages in parallel. Each stage does the following:
Stage 1 – execute windows batch code to print current build number
Stage 2 – generate a random number and save it as a parameter. Any scripting language can be used to generate the number.
Stage 3 – calls a groovy function. Function should print how much free disk space is left on a windows based Jenkins slave where workspace is located. Any scripting language can be used to calculate free space.
Stage 4 – print the random generated number from stage 2.

Bonus task:
Main script should trigger 4 separate jobs in parallel instead of running the code directly. Write a separate script for all 4 stages. 3 jobs should start on slaves having ‘windows’ label and 1 with ‘linux’. You can choose which jobs to run on which nodes. 
