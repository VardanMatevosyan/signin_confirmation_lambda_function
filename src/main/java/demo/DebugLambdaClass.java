package demo;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

@QuarkusMain
@Slf4j
public class DebugLambdaClass {

    public static void main(String[] args) {
        log.info("Start debugging lambda function");
        setProperties();
        Quarkus.run(args);
    }

    private static void setProperties() {
        if (isNull(System.getProperty("quarkus.profile"))) {
            System.setProperty("quarkus.profile", "local");
        }
    }

}
