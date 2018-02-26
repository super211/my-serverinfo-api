import java.text.SimpleDateFormat

testEnvUrl = 'http://localhost:8089/'

stage 'Build'
node {
currentBuild.result = "SUCCESS"
  try{
   def mvnHome;
   def project_id;
   def artifact_id;
   def aws_s3_bucket_name;
   def aws_s3_bucket_region;
   def timeStamp;
   def baseDir;
   
   stage('Initalize'){
   //Get these from parameters later
       mvnHome = tool 'Maven3.5.0'
	   project_id = 'serverinfo-api';
	   aws_s3_bucket_name = 'devopshub-repo';
	   aws_s3_bucket_region = 'ap-southeast-1';
	   timeStamp = getTimeStamp();
       baseDir = pwd();
	   currentBranch = env.BRANCH_NAME;
	   deploy_env=getTargetEnv(currentBranch);
//	   artifact_id = version();
   }
   stage('Checkout') { // for display purposes
      // Get some code from a GitHub repository
      checkout scm;
   }
   stage('Code Analysis'){
       //sh "'${mvnHome}/bin/mvn' clean jacoco:prepare-agent package jacoco:report"
       echo 'perform code analysis here!'
   }
  stage('Build'){
      if (isUnix()) {
         //sh "'${mvnHome}/bin/mvn' clean deploy -Ps3 -s ${baseDir}/scripts/settings.xml"
         sh "'${mvnHome}/bin/mvn' clean install"
      } else {
         //bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean install -Ps3 -s scripts\settings.xml/)
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean deploy/)
      }
  }
   stage('Stash')
   {
      stash includes: 'target/*.war', name: 'target'
   }

    stage('Send Build to S3')
    {
    unstash 'target';
    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', 
    accessKeyVariable: 'AWS_ACCESS_KEY_ID', 
    credentialsId: 's3mavenadmin', 
    secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']])  
	 {
        awsIdentity() //show us what aws identity is being used
        def targetLocation = project_id + '/builds/' + timeStamp;
        withAWS(region: aws_s3_bucket_region) {
        s3Upload(file: 'target', bucket: aws_s3_bucket_name, path: targetLocation)
        }
     }
    }
	
	if(deploy_env=="all"){
	def envlist = ["dev", "sit", "uat", "staging","prod"];
		for(itm in envlist){
			stage("Checkpoint ${itm}"){
				checkAndDeploy(baseDir, itm, timeStamp);
			}
		}
	}
	else{
		if(deploy_env!='none')
		{
			stage("Deploy to ${deploy_env}")
			{
				checkAndDeploy(baseDir, deploy_env, timeStamp);
			}
		}
	}
  } catch (err) {

        currentBuild.result = "FAILURE"

        throw err
    }
}

def getTimeStamp(){
	def dateFormat = new SimpleDateFormat("yyyyMMddHHmm")
	def date = new Date()
	return dateFormat.format(date);
}

def version() {
    def ver = readFile('pom.xml') =~ '<version>(.+)</version>'
    ver ? ver[0][1] : null
    def art = readFile('pom.xml') =~ '<artifactId>(.+)</artifactId>'
    art ? art[0][1] : null
    def pck = readFile('pom.xml') =~ '<packaging>(.+)</packaging>'
    pck ? pck[0][1] : null
	version = art+ver ? art + '-' + ver + '.' + pck : artifactId + '.war'
	return version;
}

def mvn(args) {
    _mvnHome = tool 'Maven3.5.0'
    sh "${_mvnHome}/bin/mvn ${args}"
}

def runTests(duration) {
    node {
        checkout scm
        runWithServer {url ->
            mvn "-o -f sometests test -Durl=${url} -Dduration=${duration}"
        }
    }
}


def run_playbook(playbook_name, deploy_env) {
	sh "ansible-playbook ${playbook_name} --extra-vars deploy_host=${deploy_env}";
}

def getTargetEnv(String branchName){
	def deploy_env="dev";
	switch(branchName){
		case('develop'):
		deploy_env="dev";
		break;
		case('sit'):
		deploy_env="sit";
		break;
		case('uat'):
		deploy_env="uat";
		break;
		case('staging'):
		deploy_env="staging";
		break;
		case('master'):
		deploy_env="prod";
		break;
		case('cdp'):
		deploy_env="all";
		break;
		default:
			if(branchName.startsWith("feature")){
				deploy_env="none"
			}
		break;
	}
	return deploy_env;
}

def checkAndDeploy(String baseDir, String envname, String timeStamp){
	  def  c_userInput = false;
	  def c_didTimeout = false;

	if(envname=="dev"){
	//Deploy directly to dev environment
		run_playbook("main.yaml",envname);
	}
	else{

	try {
	    timeout(time: 5, unit: 'DAYS') { // change to a convenient timeout for you
	        c_userInput = input(message: "Do you approve deployment to ${envname}?", ok: "Proceed", 
                        parameters: [booleanParam(defaultValue: true, 
                        description: "If you would want to proceed for deployment to ${envname}, just tick the checkbox and click Proceed!",name: "Yes?")])
	    }
	} catch(err) { // timeout reached or input false
	    def user = err.getCauses()[0].getUser()
	    if('SYSTEM' == user.toString()) { // SYSTEM means timeout.
	        c_didTimeout = true
	    } else {
	        c_userInput = false
	        echo "Aborted by: [${user}]"
	    }
	}
        if ((c_didTimeout)||(!c_userInput)) {
            // do something on timeout
            echo "no input was received before timeout"
            currentBuild.result = 'ABORTED'
        } else {
			run_playbook("main.yaml",envname);
        } 
   }
}


def deploy(id) {
    //unstash 'war'
    //sh "cp x.war /tmp/webapps/${id}/${version()}"
	echo 'call the playbook to deploy this war now.  sh ansible-playbook main.yaml --extra-vars "deploy_environment=test ansible_task=deploy-user-api deploy_host=localhost"';
	unstash 'target';
	sh 'make clean install';
}

def undeploy(id) {
    sh "rm /tmp/webapps/${id}.war"
}

def runWithServer(body) {
    def id = UUID.randomUUID().toString()
    deploy id
    try {
        body.call "${jettyUrl}${id}/"
    } finally {
        undeploy id
    }
}