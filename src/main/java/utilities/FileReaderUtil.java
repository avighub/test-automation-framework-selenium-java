package utilities;

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

  public static Properties getConfigPropertyFileInfo(String filePath) {

    log.debug("Reading properties file at the location: {}", filePath);

    //Code for reading prop file
    Properties properties = new Properties();
    FileReader fileReader;

    try {
      fileReader = new FileReader(filePath);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    BufferedReader reader = new BufferedReader(fileReader);

    try {
      properties.load(reader);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return properties;
  }
}
