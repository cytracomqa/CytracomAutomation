package utilities.listen;

import com.aventstack.extentreports.Status;
//import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pageObjects.BaseClassiOSAndAndroid;
import utilities.Log;
import utilities.Utils;
import utilities.reports.ExtentTestManager;

//import javax.rmi.CORBA.Util;
import java.net.MalformedURLException;

public class Listener implements ITestListener {

	public AndroidDriver androidDriver;
	public IOSDriver iosDriver;

	@Override
	public void onTestStart(ITestResult result) {

		String Platform = result.getMethod().getXmlTest().getLocalParameters().get("platform");
		if(Platform.contains("android"))
		{
			try {
				androidDriver = Utils.LaunchAndroidApp();
				System.out.println("App Launched");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else
		if(Platform.contains("ios")){
			try {
				iosDriver = Utils.LaunchiOSApp();
				Log.info("Launched");
				System.out.println("App Launched");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		new BaseClassiOSAndAndroid(androidDriver, iosDriver);
		ExtentTestManager.getTest().log(Status.PASS, "App is successfully Launched");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			tearDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			tearDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		try {
			tearDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

	public void tearDown() throws InterruptedException {

	}
}
