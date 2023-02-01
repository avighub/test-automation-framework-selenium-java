package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public final class FileReaderUtil {

  private FileReaderUtil(){}

  public static Properties getConfigPropertyFileInfo(String filePath)
    {

      System.out.println("Reading properties file at the location: "+filePath);

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
