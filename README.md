Documentation of the Application
* [README_DEVELOPMENT_DOCUMENTATION_SOURCE.md](readme%2FREADME_DEVELOPMENT_DOCUMENTATION_SOURCE.md)
* [README_QUARKUS.md](readme%2FREADME_QUARKUS.md)
* [README_SAM.md](readme%2FREADME_SAM.md)

To invoke lambda function locally passing the event from the base64 encoded payload 
```shell
aws --endpoint-url=http://localhost:9999 lambda invoke --function-name "SignInConfirmationHandler" --payload file://events/eventDataBase64.json output.json
```


Deploy with all needed args if no toml config applied 
* Process file applying dynamic variables during CI/CD to replace STAGE or ENV variable
* Build template file that was processed 
* Deploy project using built template file
```shell
sed 's/STAGE_REPLACEMENT/dev/g' template.yaml > template-dev.yaml
sam build --template-file template-dev.yaml
sam deploy --stack-name signin-confirmation-lambda-function-dev --s3-bucket signin-confirmation-lambda-dev-serverless-deployment --capabilities CAPABILITY_NAMED_IAM --region eu-central-1
```


### Debugging Quarkus lambda function

#### **1 approach**
This is for all kind of events
* Run the [DebugLambdaClass.java](src%2Fmain%2Fjava%2Fdemo%2FDebugLambdaClass.java) in debug mode using IDE
By default it developed to use local profile, but if profile argument present in the configuration it will use the one from configuration.
The event.json should base64 encoded string. Use this command to do so and copy the output string to the event.json to use in the invocation command.
```shell
base64 textBasedEvent.json
```
* Invoke the lambda function handler
```shell
 aws --endpoint-url=http://localhost:9999 lambda invoke --function-name "SignInConfirmationHandler" --payload file://events/eventDataBase64.json output.json
```
#### **2 approach**
This is for REST API lambdas 
* Run the command to start with debug port
```shell
   sam local start-api --template target/sam.jvm.yaml -d 5005
```

* Add the remote JVM debug configuration to the IDE for host=localhost and port 5005
```text
-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
```  

* Add break points and invoke the lambda




