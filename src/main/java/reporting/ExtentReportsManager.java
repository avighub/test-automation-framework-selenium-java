package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ExtentReportsManager {

  public ExtentReportsManager() {
  }

  private ExtentReports extentReports;
  private ExtentSparkReporter sparkReporter;

  public ExtentReports initializeReport() {
    log.debug("Initializing Report...");

    //Step1- Initialize Extent report
    extentReports = new ExtentReports();

    //Step 2- Define HTML location
    String reportPath = "target/ExtentReport.html";
    sparkReporter = new ExtentSparkReporter(reportPath);
    log.debug("Report path is set at : {}", reportPath);

    //Step 3- Link extent report with Html object
    extentReports.attachReporter(sparkReporter);
    return extentReports;
  }

}
