package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReporter {
	static ExtentReports extentReport;
	
	public static ExtentReports getExtentReport( ) {
		String reportpath = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportpath);
		reporter.config().setReportName("Omayo Test Report");
		reporter.config().setDocumentTitle("Omayo Test Report Title");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Operating System", "Windows 10");
		extent.setSystemInfo("Tested By", "Sunitha");

	
	return extentReport;
	
}
}