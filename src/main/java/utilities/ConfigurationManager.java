package utilities;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Properties;

@Slf4j
public final class ConfigurationManager {
  private ConfigurationManager() {
  }

  public static Properties FRAMEWORK_PROPERTIES;
  public static Properties ENVIRONMENT_PROPERTIES;
  public static String ENVIRONMENT;
  public static String BROWSER_NAME;
  public static boolean IS_HEADLESS;

  static {
    FRAMEWORK_PROPERTIES = FileReaderUtil.getConfigPropertyFileInfo("src/main/resources/framework-config.properties");
    getSystemVariables();
    setTestEnvProperties();
  }

  private static void getSystemVariables() {
    if (Objects.nonNull(System.getProperty("ENVIRONMENT"))) {
      ENVIRONMENT = System.getProperty("ENVIRONMENT");
      log.debug("ENVIRONMENT value: {} is set from Environment Variables.", ENVIRONMENT);
    } else {
      ENVIRONMENT = FRAMEWORK_PROPERTIES.getProperty("ENVIRONMENT");
      log.debug("ENVIRONMENT value: {} is set from framework-config.properties.", ENVIRONMENT);
    }

    if (Objects.nonNull(System.getProperty("BROWSER_NAME"))) {
      BROWSER_NAME = System.getProperty("BROWSER_NAME");
      log.debug("BROWSER_NAME value: {} is set from Environment Variables.", BROWSER_NAME);
    } else {
      BROWSER_NAME = FRAMEWORK_PROPERTIES.getProperty("BROWSER_NAME");
      log.debug("BROWSER_NAME value: {} is set from framework-config.properties.", BROWSER_NAME);
    }

    if (Objects.nonNull(System.getProperty("IS_HEADLESS"))) {
      IS_HEADLESS = Boolean.parseBoolean(System.getProperty("IS_HEADLESS"));
      log.debug("IS_HEADLESS value: {} is set from Environment Variables.", IS_HEADLESS);
    } else {
      IS_HEADLESS = Boolean.parseBoolean(FRAMEWORK_PROPERTIES.getProperty("IS_HEADLESS"));
      log.debug("IS_HEADLESS value: {} is set from framework-config.properties.", IS_HEADLESS);
    }
  }

  private static void setTestEnvProperties() {
    if (ENVIRONMENT.equalsIgnoreCase("QA")) {
      ENVIRONMENT_PROPERTIES = FileReaderUtil.getConfigPropertyFileInfo("src/main/resources/environments/qa.properties");
      log.info("QA Environment properties loaded");
    } else if (ENVIRONMENT.equalsIgnoreCase("INT")) {
      ENVIRONMENT_PROPERTIES = FileReaderUtil.getConfigPropertyFileInfo("src/main/resources/environments/int.properties");
      log.info("DEV Environment properties loaded");
    } else if (ENVIRONMENT.equalsIgnoreCase("PROD")) {
      ENVIRONMENT_PROPERTIES = FileReaderUtil.getConfigPropertyFileInfo("src/main/resources/environments/prod.properties");
      log.info("PROD Environment properties loaded");
    } else {
      throw new RuntimeException("Invalid Environment selected. Enter one of these values QA , INT, PROD");
    }
  }
}
