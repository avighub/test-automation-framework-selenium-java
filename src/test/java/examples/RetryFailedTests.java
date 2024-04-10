package examples;

import config.retry.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class RetryFailedTests {

  @Test(retryAnalyzer = RetryAnalyzer.class)
  void retryThisTestIfFails() {
    Random random = new Random();
    int randomInt = random.nextInt(3);

    Assert.assertTrue(randomInt == 2);
  }
}
