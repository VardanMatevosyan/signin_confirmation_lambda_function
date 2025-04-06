package demo.properties;

import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@StaticInitSafe
@ConfigMapping(prefix = "cognito")
public interface CognitoProperties {
  @WithName("user-pool-id")
  String userPoolId();
}
