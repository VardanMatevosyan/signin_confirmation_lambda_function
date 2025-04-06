package demo.service;

import demo.entity.User;
import demo.mapper.UserMapperImpl;
import demo.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@ApplicationScoped
public class UserServiceImpl {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  @Inject
  UserMapperImpl userMapper;
  @Inject
  CognitoServiceImpl cognitoService;
  @Inject
  UserRepository userRepository;

  @Transactional
  public void save(String userName, Map<String, String> userAttributes) {
    User user = userMapper.mapToEntity(userName, userAttributes);
    if (doseNotExist(user.getIdpSub())) {
      LOGGER.info("Trying to save user {}", user);
      userRepository.persist(user);
      LOGGER.info("Saved the user {}", user);
    }
//    setPersistedAttribute(user.getIdpUserName());
  }

  /**
   * This method is commented because the VPC endpoint for cognito is not available at this moment.
   * @param username of the cognito user
   */
  private void setPersistedAttribute(String username) {
    cognitoService.setUserAttributes(username, Map.of("custom:persisted", "true"));
  }

  public boolean doseNotExist(String sub) {
    return userRepository.doseNotExist(sub);
  }
}
