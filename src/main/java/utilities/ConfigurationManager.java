package utilities;

import java.util.Objects;
import java.util.Properties;

public final class ConfigurationManager {
  private ConfigurationManager()
    {
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

  private static void getSystemVariables()
    {
      if (Objects.nonNull(System.getProperty("ENVIRONMENT"))) {
        ENVIRONMENT = System.getProperty("ENVIRONMENT");
        System.out.println("ENVIRONMENT value: " + ENVIRONMENT + " is set from Environment Variables.");
      } else {
        ENVIRONMENT = FRAMEWORK_PROPERTIES.getProperty("ENVIRONMENT");
        System.out.println("ENVIRONMENT value : " + ENVIRONMENT + "is set from framework-config.properties.");
      }

      if (Objects.nonNull(System.getProperty("BROWSER_NAME"))) {
        BROWSER_NAME = System.getProperty("BROWSER_NAME");
        System.out.println("BROWSER_NAME value: " + BROWSER_NAME + " is set from Environment Variables.");
      } else {
        BROWSER_NAME = FRAMEWORK_PROPERTIES.getProperty("BROWSER_NAME");
        System.out.println("BROWSER_NAME value : " + BROWSER_NAME + "is set from framework-config.properties.");
      }

      if (Objects.nonNull(System.getProperty("IS_HEADLESS"))) {
        IS_HEADLESS = Boolean.parseBoolean(System.getProperty("IS_HEADLESS"));
        System.out.println("IS_HEADLESS value: " + IS_HEADLESS + " is set from Environment Variables.");
      } else {
        IS_HEADLESS = Boolean.parseBoolean(FRAMEWORK_PROPERTIES.getProperty("IS_HEADLESS"));
        System.out.println("IS_HEADLESS value : " + IS_HEADLESS + " is set from framework-config.properties.");
      }
    }

  private static void setTestEnvProperties()
    {
      if (ENVIRONMENT.equalsIgnoreCase("QA")) {
        ENVIRONMENT_PROPERTIES = FileReaderUtil.getConfigPropertyFileInfo("src/main/resources/environments/qa.properties");
        System.out.println("QA Environment properties loaded");
      } else if (ENVIRONMENT.equalsIgnoreCase("INT")) {
        ENVIRONMENT_PROPERTIES = FileReaderUtil.getConfigPropertyFileInfo("src/main/resources/environments/int.properties");
        System.out.println("DEV Environment properties loaded");
      } else if (ENVIRONMENT.equalsIgnoreCase("PROD")) {
        ENVIRONMENT_PROPERTIES = FileReaderUtil.getConfigPropertyFileInfo("src/main/resources/environments/prod.properties");
        System.out.println("PROD Environment properties loaded");
      } else {
        throw new RuntimeException("Invalid Environment selected. Enter one of these values QA , INT, PROD");
      }
    }
}
