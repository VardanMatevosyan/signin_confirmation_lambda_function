# Documentation sources of development

This README provides documentation source for developing this kind of application and more.

## SAM - AWS Serverless Application Model

[About SAM](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/what-is-sam.html)

[Install SAM](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/install-sam-cli.html)

All about SAM commands - ([SAM commands](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-command-reference.html))
Main commands used - init, build, deploy, validate
* init - used to generate skeleton of the SAM app by providing the main config.
* build - for building the SAM app you will see the .aws-sam folder in result that is built app.
* deploy - uploads the sam app (function.zip) using the CodeUri property value - which is path to the function.zip of your app
after and sam template file to s3 bucket and if the template changed then it will update the stack.
* validate - validate the template file.

## For the future use to enhance the app

> **_NOTE:_** The functionality of updating the cognito is commented out, so interaction is not possible for now.
That is why use exists checks to database added. If in the future AWS provides the ability of PrivateLink (endpoint) 
for Cognito this can be used. Also NAT Gateway can be an option to try.

* [Private REST APIs](https://docs.aws.amazon.com/apigateway/latest/developerguide/apigateway-private-apis.html)
* [VPC Endpoint](https://docs.aws.amazon.com/vpc/latest/privatelink/create-interface-endpoint.html)
* [NAT Gateway](https://medium.com/@sanjuthamke9699/nat-gateway-with-s-698e8bb3f477) and [VPC YouTube video](https://www.youtube.com/watch?v=43tIX7901Gs)

## Lambda Debugger

* For the future to try this AWS Lambda debugger plugin Thundra for IDE.
[Thundra](https://gokalp.medium.com/how-to-remote-debug-lambda-functions-using-thundra-e8d5e780b268)

* Also for Quarkus it possible to use Main class with public static main method to debug teh function locally.

## CircleCI CI/CD integration

* For building and deploying the SAM app using CircleCI the [aws-sam-serverless](https://circleci.com/developer/orbs/orb/circleci/aws-sam-serverless#commands-install)  Orb was used to simplify the integration and deployment.
* The documentation contains different use cases with examples. Documentation describes the jobs, commands with the options that can be used.
* To build native image using docker container with Remote Docker [CircleCi Remote Docker configuration](https://circleci.com/docs/building-docker-images/)

## Quarkus docs

* [Mapping Configuration from properties file](https://quarkus.io/guides/config-mappings#config-mappings)
* [Hibernate in Quarkus](https://quarkus.io/guides/hibernate-orm-panache)
* [Quarkus and Gradle](https://quarkus.io/guides/gradle-tooling)
* [Native Executable](https://quarkus.io/guides/building-native-image)

## Testing the authentication flow 

* To authenticate and get the id_token back the web version was used [Web Postmen](https://web.postman.co/)
Thi triggers the Congnito lambda triggers and start the process.
* The Cognito SPA client was used with Managed UI.

## Cognito

* [Endpoints](https://docs.aws.amazon.com/cognito/latest/developerguide/federation-endpoints.html)
* [Redirect endpoints](https://docs.aws.amazon.com/cognito/latest/developerguide/authorization-endpoint.html)
* [Cognito Provider configuration](https://docs.aws.amazon.com/cognito/latest/developerguide/cognito-user-pools-identity-provider.html#cognito-user-pools-facebook-provider)
* [Post Confirmation Lambda Trigger Doc](https://docs.aws.amazon.com/cognito/latest/developerguide/user-pool-lambda-post-confirmation.html)
* 

## Google app client

* Config for SSO using [Google Cloud Console](https://console.cloud.google.com/auth/clients).
Need to configure the client application and set teh cognito:
* Authorized JavaScript origins which looks like this - <https://USERPOOL_NAME.auth.REGION.amazoncognito.com>
* Authorized redirect URIs like - <https://USERPOOL_NAME.auth.REGION.amazoncognito.com/oauth2/idpresponse> which is predefine endpoint.
* Authorized domains: 
  * amazoncognito.com
  * USERPOOL_NAME.auth.REGION.amazoncognito.com
