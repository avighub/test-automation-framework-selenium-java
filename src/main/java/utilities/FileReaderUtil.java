package utilities;

import exceptions.RuntimeExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public final class FileReaderUtil {

  private FileReaderUtil() {
  }

  /**
   * Making effective use of exception handling
   * - Handling exception with try resources
   * - catching exact exception
   * - throwing exception to propagate
   * - logging exception properly
   */

  public static Properties getConfigPropertyFileInfo(String filePath) {
    log.debug("Reading properties file at the location: {}", filePath);

    Properties properties = new Properties();

    // using try with resources to better manage closing of resources automatically
    try (
            FileReader fileReader = new FileReader(filePath); // throws FileNotFoundException
            BufferedReader reader = new BufferedReader(fileReader) // does not throw any exception but we added it to align inside resource block
    ) {
      properties.load(reader); // throws IOException
    } catch (FileNotFoundException e) { // catching lower level exception first
      log.error("Properties file not found:: {}", filePath, e); // proper way of logging error with custom message
      throw new RuntimeExceptionHandler("Properties file not found", e);
    } catch (IOException e) {
      log.error("Error reading properties file: {}", filePath, e); // proper way of logging error with custom message
      throw new RuntimeExceptionHandler("Error reading Properties file: " + filePath, e);
    }
    return properties;
  }
}
