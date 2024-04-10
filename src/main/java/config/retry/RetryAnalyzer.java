package config.retry;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * How to use this Retry logic?
 * - Add the annotation @Test(retryAnalyzer = RetryTests.class) with Test annotation
 */
public class RetryAnalyzer implements IRetryAnalyzer, IAnnotationTransformer {

  private int retryCount = 0;

  @Override
  public boolean retry(ITestResult iTestResult) {
    int maxRetryCount = 3;
    if (retryCount < maxRetryCount) {
      retryCount++;
      return true;
    }
    return false;
  }

}
