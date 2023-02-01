package reporting;

import com.aventstack.extentreports.ExtentTest;

import java.util.Objects;

public final class ExtentFactory {

  private ExtentFactory(){}

  private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

  public static void setExtentTest(ExtentTest test){
    if(Objects.nonNull(test)){
      extentTest.set(test);
    }else {
      throw new RuntimeException("Null Extent Test");
    }
  }

  public static ExtentTest getExtentTest(){
    return extentTest.get();
  }

  public static void removeTest(){
    extentTest.remove();
  }



}
