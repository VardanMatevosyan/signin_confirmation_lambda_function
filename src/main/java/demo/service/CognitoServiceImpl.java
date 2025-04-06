package demo.service;

import demo.properties.CognitoProperties;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminUpdateUserAttributesRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;

@ApplicationScoped
public class CognitoServiceImpl {

  @Inject
  CognitoIdentityProviderClient cognitoClient;
  @Inject
  CognitoProperties cognitoProperties;

  public void setUserAttributes(String username, Map<String, String> userAttributes) {
    var request = buildAdminUpdateUserAttributesRequest(username, userAttributes);
    cognitoClient.adminUpdateUserAttributes(request);
  }

  private AdminUpdateUserAttributesRequest buildAdminUpdateUserAttributesRequest(
      String username,
      Map<String, String> userAttributes) {
    return AdminUpdateUserAttributesRequest.builder()
        .userPoolId(cognitoProperties.userPoolId())
        .username(username)
        .userAttributes(getUserAttributes(userAttributes))
        .build();
  }

  private static List<AttributeType> getUserAttributes(Map<String, String> userAttributes) {
    return userAttributes.entrySet()
        .stream()
        .map(CognitoServiceImpl::buildAttributeType)
        .toList();
  }

  private static AttributeType buildAttributeType(Entry<String, String> entry) {
    return AttributeType.builder().name(entry.getKey()).value(entry.getValue()).build();
  }

}
