package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentManager {

  public ExtentManager(){}

  ExtentReports extentReports;
  ExtentSparkReporter spark;

  public ExtentReports initReport(){
    //Step1- Initialize Extent report
    extentReports = new ExtentReports();

    //Step 2- Define HTML location
    spark = new ExtentSparkReporter("target/Spark.html");

    //Step 3- Link extent report with Html object
    extentReports.attachReporter(spark);
    return extentReports;
  }




}
