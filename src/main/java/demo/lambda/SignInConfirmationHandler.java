package demo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.CognitoUserPoolPostConfirmationEvent;
import demo.service.UserServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Named("SignInConfirmationHandler")
@ApplicationScoped
public class SignInConfirmationHandler
    implements RequestHandler<CognitoUserPoolPostConfirmationEvent,
                              CognitoUserPoolPostConfirmationEvent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(SignInConfirmationHandler.class);

  @Inject
  UserServiceImpl userService;

  @Override
  public CognitoUserPoolPostConfirmationEvent handleRequest(CognitoUserPoolPostConfirmationEvent e,
                                                            Context context) {
    Map<String, String> userAttributes = e.getRequest().getUserAttributes();
    LOGGER.info("Start request to the sign confirmation lambda: {}", userAttributes);
    userService.save(e.getUserName(), userAttributes);
    LOGGER.info("End request with attributes: {}", userAttributes);
    return e;
  }

  /**
   * This should be used later to avoid DB calls, when the VPC PrivateLink (endpoint) would be available in AWS.
   * Checks the user attribute "custom:persisted" value.
   * The lambda function set the "true" when the user confirm the registration.
   * Confirmation is implemented using Cognito User Poll feature.
   * Cognito sends the 6-digit code to the user email. User can confirm the account using the code.
   * @param userAttributes from Cognito User Poll Sign in confirmation trigger.
   * @return true if user persisted in the RDS database, otherwise return false.
   */
  private boolean isPersisted(Map<String, String> userAttributes) {
    return Boolean.parseBoolean(userAttributes.get("custom:persisted"));
  }
}
