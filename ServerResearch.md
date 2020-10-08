# Baylie & Karen Meeting 10/6/20 7pm

Issue #55: Research how to integrate the server - Baylie & Karen (Oct 12)

- Both attempted to install AWS Amplify to begin testing if this is an item that we can use and we ran into installation issues on our  education accounts. AWS was not allowing us to create an IAM user, so we contacted the professor about this issue to see if she can help.


Issue #50: Find out which AWS items will be used - Baylie, Karen, & Jacob (Oct 12)

- Testing with AWS Amplify to see if we can connect to an S3 Bucket

Prerequisites
  (https://docs.amplify.aws/lib/project-setup/prereq/q/platform/android)
Getting Started
  (https://docs.amplify.aws/lib/storage/getting-started/q/platform/android)
  
  
# Baylie & Karen Meeting 10/8/20 3:30pm
  
  
Issue #55 : Research how to integrate the server - Baylie & Karen (Oct 12)

Issue #60 : Install Amplify AWS and connect to the mobile app - Baylie & Karen (Oct 12)

Installing AWS CLI and Amplify (https://docs.amplify.aws/cli/start/install)
  
- 1) Install all Prerequisites listed on this site: (https://docs.amplify.aws/cli/start/install#pre-requisites-for-installation) and make sure that you have set up your AWS educate account (this can be done through the link that was sent to your school email from AWS).
- 2) Make sure that you are signed into your AWS educate account and navigate to the "AWS Console". Leave this page open in your browser.
- 3) Open your command line prompt.
- 4) Install Amplify CLI using the command: npm install -g @aws-amplify/cli
- 5) Configure Amplify CLI using the command: amplify configure
- 6) It will take you to 'Sign in to your AWS administrator account' and open up to the "AWS Console" page in your browser. You don't need to do anything, just return to your command line prompt and press ENTER.
- 7) To 'Specify the AWS Region' select: us-east-1
- 8) To 'Specify the username of the new IAM user' enter: amplify_user
- 9) It will take you to another page to try and create an IAM user. You will see that there is an error since you can not create one with your AWS educate account. Navigate back to your command line prompt and press ENTER.
- 10) Instead navigate back to your AWS Educate Vocareum page and select "Account Details". 
- 11) Next to "AWS CLI" select show. Copy over the accessKeyID and secretAccessKey into your command prompt. 
- 12) Name your Profile Name: (default)
- 13) You should the message "Successfully set up the new user"
- 14) Navigate to your user profile in your computer's directory, there should now be a folder named: .aws
- 15) Open .aws/credentials in a text editor.
- 16) From your "Account Details" on your AWS Educate Vocareum page copy over all of the following that looks like this into the .aws/credentials file under [(default)] and save:

aws_access_key_id=ASIAWAGKPYWX2FMMJERL
aws_secret_access_key=GN3xkC47Nz/lAqDjeGI6WeYDNQTKf5GgOsq84xnN aws_session_token=FwoGZXIvYXdzEA4aDJu6loW1au1IpXUiYiLBAUIgxwghkH8YtPycNF342Sp1A7z730JW3YJJITp4cgbGtWWm666dVRL/dkI+zYqm7tKNb7v6IMs5U1cb9ChtEDulKE+iEhIbq3VEtAntoEDtlxx7NozCc6RFLn6GTE5rYf90Qy5pmelxa4hKuxtBqi/58UfvJiNoEL7LfzHeRrO6tpKZmk18SEX3WHqFnKd4nDMPE3i2+PcCLnwkSGBr5i9fncmAl0uRTdWMVtZCKokylwyn/slwMvLubQ0x1NC4p6Io4en9+wUyLWMQPN4RfR+ndtCz9Q8CoogohaskEclp7NyhoI6uBM9acBy6pNSqw7qZc+ZiRw==

 
- 17) In your command line run the following commands one at a time using your own AWS credentials:

set aws_access_key_id=ASIAWAGKPYWX2FMMJERL
set aws_secret_access_key=GN3xkC47Nz/lAqDjeGI6WeYDNQTKf5GgOsq84xnN 
set aws_session_token= ...............

- 18) Change directory to the project folder that conatins the CNNapp. For example, C:\Users\bayli\AndroidStudioProjects\cosc481w-581-2020-fall-convoluted-classifiers\CNNapp>

- 19) Run the command: amplify init
- 20) Enter a name for the project: CNNapp
- 21) Enter a name for the environment: dev
- 22) Choose your default editor: This is a personal choice, use whatever you like.
- 23) Choose the type of app that you're building: android
- 24) Where is your Res directory: app/src/main/res
- 25) Do you want to use an AWS profile?: Yes
- 26) Please choose the profile you wnat to use: (default)

  
Amplify was successfully installed and connected to the mobile app.  
  

- Possible Architecture 1:
![1](arch1.png)

- Possible Architecture 2: 
![2](arch2.png)

