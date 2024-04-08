package reporting;

import com.aventstack.extentreports.ExtentTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ExtentFactory {

  private ExtentFactory() {
  }

  private static final ThreadLocal<ExtentTest> threadLocalExtentTest = new ThreadLocal<>();

  public static void setExtentTest(ExtentTest extentTest) {
    if (threadLocalExtentTest.get() == null) {
      threadLocalExtentTest.set(extentTest);
      log.debug("New ExtentTest object is set...");
    }
  }

  public static ExtentTest getExtentTest() {
    return threadLocalExtentTest.get();
  }

  public static void remove() {
    threadLocalExtentTest.remove();
    log.debug("ExtentTest ThreadLocal object is removed...");
  }

}
