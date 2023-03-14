package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import pageObjects.BaseClassiOSAndAndroid;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class Utils extends BaseClassiOSAndAndroid {

    public static AndroidDriver androidDriver;
    public static IOSDriver iosDriver;

    public Utils(AndroidDriver androidDriver, IOSDriver iosDriver) {
        super(androidDriver, iosDriver);
    }

    public static AndroidDriver LaunchAndroidApp() throws InterruptedException, MalformedURLException {

        deviceType = "Android";

        if (androidDriver == null) {
            //Set the path for AndroidDriver
            System.setProperty("https.protocols", Constant.httpsProtocols);
            File classpathRoot = new File(System.getProperty("user.dir"));

            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Constant.deviceName);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Constant.version);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Constant.platformName);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            capabilities.setCapability(MobileCapabilityType.APP, Constant.appPath);
            capabilities.setCapability(MobileCapabilityType.HAS_NATIVE_EVENTS, true);

            androidDriver = new AndroidDriver(new URL(Constant.URL), capabilities);
            Log.info("App is Launched");
            androidDriver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

            /*try {
                driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc='Search']")).click();

                Thread.sleep(2000);

                driver.findElementByClassName("android.widget.EditText").sendKeys("7Now");

                Thread.sleep(3000);



                driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
                Thread.sleep(10000);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }*/
        }
        return androidDriver;
    }

    public static IOSDriver LaunchiOSApp() throws MalformedURLException {

        deviceType = "iOS";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        URL url;

        //2
        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
//        url = new URL(URL_STRING);

        //3
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("udid", "00008030-000174123601402E");
        capabilities.setCapability("bundleId", "com.cytracom.Cytracom-Mobile");
        capabilities.setCapability("xcodeSigningId", "iPhone Developer");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.6");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iphone");
        capabilities.setCapability(MobileCapabilityType.UDID, "00008030-000174123601402E");


        capabilities.setCapability("xcodeOrgId", "pdoshi@cytracom.com");

        capabilities.setCapability("xcodeSigningId", "iPhone Developer");


//        capabilities.setCapability("autoAcceptAlerts", false);
//        capabilities.setCapability("autoGrantPermissions", false);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
//        capabilities.setCapability("sendKeyStrategy","setValue");
//        capabilities.setCapability("maxTypingFrequency",60);
        //capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability(MobileCapabilityType.HAS_NATIVE_EVENTS, true);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability("useNewWDA", false);

        //4
        url = new URL(Constant.URL);
        //iosDriver = new IOSDriver(url, capabilities);
        iosDriver = new IOSDriver<IOSElement>(new URL(URL_STRING), capabilities);
        iosDriver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        iosDriver.resetApp();

        //xpath for loading
        //XCUIElementTypeActivityIndicator[@name="In progress"]

        return iosDriver;
    }

    public static String getTestCaseName(String sTestCase)throws Exception{

        String value = sTestCase;
        try{
            int posi = value.indexOf("@");
            value = value.substring(0, posi);
            posi = value.lastIndexOf(".");
            value = value.substring(posi + 1);
            return value;

        }catch (Exception e){
            Log.error("Class Utils | Method getTestCaseName | Exception desc : "+e.getMessage());
            throw (e);
        }

    }

    public static String takeScreenshotAndroid(String sTestCaseName) throws Exception{
            File scrFile = ((TakesScreenshot)androidDriver).getScreenshotAs(OutputType.FILE);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String str = timestamp.toString();
            FileUtils.copyFile(scrFile, new File(Constant.Path_ScreenShot + sTestCaseName + str + ".jpg"));
            return Constant.Path_ScreenShot + sTestCaseName + str + ".jpg";
    }

    public static String takeScreenshotiOS(String sTestCaseName) throws Exception{
            File scrFile = ((TakesScreenshot)iosDriver).getScreenshotAs(OutputType.FILE);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String str = timestamp.toString();
            FileUtils.copyFile(scrFile, new File(Constant.Path_ScreenShot + sTestCaseName + str + ".jpg"));
            return Constant.Path_ScreenShot + sTestCaseName + str + ".jpg";
    }


}
