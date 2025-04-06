package demo.properties;

import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@StaticInitSafe
@ConfigMapping(prefix = "aws_credentials")
public interface AwsCredentialsProperties {

  @WithName("access-key-id")
  String accessKeyId();

  @WithName("secret-access-key")
  String secretAccessKey();
}
