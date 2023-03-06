package utilities;

//import org.apache.poi.hemf.record.emf.HemfMisc;
import actions.SignInAction;
import com.aventstack.extentreports.Status;
//import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.BaseClassiOSAndAndroid;
import pageObjects.SignInScreen;
import utilities.reports.ExtentTestManager;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static pageObjects.BaseClass.driver;


public class Functions extends BaseClassiOSAndAndroid {

    static WebDriverWait wait;
    static String encryptedCard = new DataFileReader().getData("cardLast4");
    static String card = new String(Base64.getDecoder().decode(encryptedCard.getBytes()));

    Functions(){
        super(androidDriver, iosDriver);
    }

    public static boolean verifyElementPresence(WebElement element) {
        boolean elementPresence = false;

        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(element));
            elementPresence=true;
        }catch(Exception e) {
            elementPresence=false;
        }

        return elementPresence;
    }


    public static void waitForElement(String Locator, String str, long time){
        if(deviceType.equalsIgnoreCase("Android")){
            wait = new WebDriverWait(androidDriver, time);
//            try {
                switch(Locator) {
                    case "ID":
                        wait.until(ExpectedConditions.presenceOfElementLocated(new MobileBy.ByAccessibilityId(str))) ;
                        break;
                    case "classname":
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.className(str))) ;
                        break;
                    case "xpath":
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(str)));
                        break;
                }
//            }catch(Exception e){
//                System.out.println(e.getMessage());
//            }
        }
        else {
            wait = new WebDriverWait(iosDriver, time);
//            try {
                switch (Locator) {
                    case "ID":
                        wait.until(ExpectedConditions.presenceOfElementLocated(new MobileBy.ByAccessibilityId(str)));
                        break;
                    case "classname":
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.className(str))) ;
                        break;
                    case "xpath":
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(str)));
                        break;
                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
        }
    }



    public static void waitForElementVisibilityOf(String Locator, String str, long time){
        if(deviceType.equalsIgnoreCase("Android")){
            wait = new WebDriverWait(androidDriver, time);
//            try {
            switch(Locator) {
                case "ID":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(new MobileBy.ByAccessibilityId(str))) ;
                    break;
                case "classname":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(str))) ;
                    break;
                case "xpath":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(str)));
                    break;
            }
//            }catch(Exception e){
//                System.out.println(e.getMessage());
//            }
        }
        else {
            wait = new WebDriverWait(iosDriver, time);
//            try {
            switch (Locator) {
                case "ID":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(new MobileBy.ByAccessibilityId(str)));
                    break;
                case "classname":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(str))) ;
                    break;
                case "xpath":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(str)));
                    break;
            }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
        }
    }





    public static String[] split(int iTestCaseRow, int Column) throws Exception {

        String[] Items = ExcelUtils.getCellData(iTestCaseRow, Column).split(";");
        return Items;

    }
    public static void swipeScreen(){
        switch (deviceType) {
            case "Android":
                Dimension dim = androidDriver.manage().window().getSize();
                int height = dim.getHeight();
                int width = dim.getWidth();
                int startx = width / 2;
                int endx = width / 2;
                int starty = (int) (height * .80);
                int endy = (int) (height*.20);

                new TouchAction(androidDriver).press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                        .moveTo(PointOption.point(endx, endy))
                        .release().perform();
                break;

            case "iOS":
                Dimension dim1 = iosDriver.manage().window().getSize();
                int height1 = dim1.getHeight();
                int width1 = dim1.getWidth();
                int startx1 = width1 / 2;
                int endx1 = width1 / 2;
                int starty1 = (int) (height1 * .80);
                int endy1 = (int) (height1*.10);

                new TouchAction(iosDriver).press(PointOption.point(startx1, starty1)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                        .moveTo(PointOption.point(endx1, endy1))
                        .release().perform();
                break;

            default:
                break;

        }
    }

    public static void scrollDownUntilElementVisible(WebElement w) {
        int k = 0;
        while(w.isDisplayed() == false && k<15)  {
            Functions.swipeScreen();
            k++;
        }
    }

    public static void scrollDownUntilTwoElementsVisible(WebElement w, WebElement x) {

        do {
            Functions.swipeScreen();
        }
        while((w.isDisplayed() == false) && (x.isDisplayed() ==false) );
    }

    public static void swipeUpScreen(){
        switch (deviceType) {
            case "Android":
                Dimension dimUp = androidDriver.manage().window().getSize();
                int heightUp = dimUp.getHeight();
                int widthUp = dimUp.getWidth();
                int startxUp = widthUp / 2;
                int endxUp = widthUp / 2;
                int startyUp = (int) (heightUp * .80);
                int endyUp = (int) (heightUp*.20);

                new TouchAction(androidDriver).press(PointOption.point(startxUp, endyUp)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                        .moveTo(PointOption.point(startxUp, startyUp))
                        .release().perform();
                break;

            case "iOS":
                Dimension dim1 = iosDriver.manage().window().getSize();
                int heightUp1 = dim1.getHeight();
                int widthUp1 = dim1.getWidth();
                int startxUp1 = widthUp1 / 2;
                int endx1 = widthUp1 / 2;
                int startyUp1 = (int) (heightUp1 * .80);
                int endyUp1 = (int) (heightUp1*.20);

                new TouchAction(iosDriver).press(PointOption.point(startxUp1, endyUp1)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                        .moveTo(PointOption.point(startxUp1, startyUp1))
                        .release().perform();
                break;

            default:
                break;

        }
    }
    public static void swipeUp(String str){
        HashMap<String, String> scrollObjects = new HashMap<String, String>();
        switch (deviceType){
            case "Android":
                String scrollElement = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + str + "\").instance(0))";
                WebElement element = androidDriver.findElement(MobileBy.AndroidUIAutomator(scrollElement));
                break;
            case "iOS":
                scrollObjects = new HashMap<String, String>();
                scrollObjects.put("direction", "up");
//                scrollObjects.put("Flavor",
//                        ((RemoteWebElement) parentElement).getId());
                //scrollObjects.put("predicateString", "label == \"Flavor Gulp Coke Gulp Dr Pepper Diet Pepsi Gulp Diet Coke Gulp Pepsi Gulp Sprite Gulp Jarritos Gulp Mt Dew Size 30oz 24oz");
                scrollObjects.put("name", str);
                iosDriver.executeScript("mobile: swipe", scrollObjects);
                break;
            default:
                break;
        }
    }



    public static void swipeDown(String str){
        switch (deviceType){
            case "Android":

            case "iOS":
                HashMap<String, String> scrollObjects = new HashMap<String, String>();
                scrollObjects.put("direction", "down");
//                scrollObjects.put("Flavor",
//                        ((RemoteWebElement) parentElement).getId());
                //scrollObjects.put("predicateString", "label == \"Flavor Gulp Coke Gulp Dr Pepper Diet Pepsi Gulp Diet Coke Gulp Pepsi Gulp Sprite Gulp Jarritos Gulp Mt Dew Size 30oz 24oz");
                scrollObjects.put("name", str);
                iosDriver.executeScript("mobile: swipe", scrollObjects);
                break;
            default:
                break;
        }
    }

    public static void swipeLeft(String str){
        HashMap<String, String> scrollObjects = new HashMap<String, String>();
        switch (deviceType){
            case "Android":
                scrollObjects.put("direction", "left");
//                scrollObjects.put("Flavor",
//                        ((RemoteWebElement) parentElement).getId());
                //scrollObjects.put("predicateString", "label == \"Flavor Gulp Coke Gulp Dr Pepper Diet Pepsi Gulp Diet Coke Gulp Pepsi Gulp Sprite Gulp Jarritos Gulp Mt Dew Size 30oz 24oz");
                scrollObjects.put("name", str);
                iosDriver.executeScript("mobile: swipe", scrollObjects);
            case "iOS":
                scrollObjects.put("direction", "down");
//                scrollObjects.put("Flavor",
//                        ((RemoteWebElement) parentElement).getId());
                //scrollObjects.put("predicateString", "label == \"Flavor Gulp Coke Gulp Dr Pepper Diet Pepsi Gulp Diet Coke Gulp Pepsi Gulp Sprite Gulp Jarritos Gulp Mt Dew Size 30oz 24oz");
                scrollObjects.put("name", str);
                iosDriver.executeScript("mobile: swipe", scrollObjects);
                break;
            default:
                break;
        }
    }

    public static void swipeLeftForCard() throws InterruptedException {
        int x=0;
        int y=0;
        WebElement parentElement = null;
        switch (deviceType) {
            case "Android":
                parentElement = androidDriver.findElement(By.xpath("//android.widget.TextView[contains(@text,\"**** " + card + "\")]"));
                x = parentElement.getLocation().getX();
                y = parentElement.getLocation().getY();
                System.out.println(x);
                System.out.println(y);
                int x_stop = x - 200;
                int y_stop = y;
                new TouchAction(androidDriver).press(PointOption.point(x + 200, y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                        .moveTo(PointOption.point(x_stop, y_stop)).release().perform();
                System.out.println("Swiped");
                //AccountScreen.waitForDeleteButton();
                break;
            case "iOS":
                try{
                    if(iosDriver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"**** " + card + "\"])[6]")).isDisplayed()){
                        parentElement = iosDriver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"**** " + card + "\"])[6]"));
                    }
                }catch (Exception e){
                    parentElement = iosDriver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"**** " + card + " \uE5CA\"])[6]"));
                }
                x = parentElement.getLocation().getX();
                y = parentElement.getLocation().getY();
                System.out.println(x);
                System.out.println(y);
                int x_stoppt = x + 157;
                int y_stoppt = y;
                new TouchAction(iosDriver).press(PointOption.point(x + 285, y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                        .moveTo(PointOption.point(x_stoppt, y_stoppt)).release().perform();
                System.out.println("Swiped");
                //AccountScreen.waitForDeleteButton();
                break;
            default:
                System.out.println("Neither Android nor iOS");
                break;
        }
    }

    public static JavascriptExecutor js;
    public static HashMap<String, String> scrollObject = new HashMap<>();

    public static void iosScrollToAnElement(IOSElement el) {
        scrollObject.put("direction", "down");
        scrollObject.put("element", el.getId());
        js.executeScript("mobile: swipe", scrollObject);
    }

    void androidScrollToAnElementByText(String text) {
        try {
            ((AndroidDriver)driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + text + "\").instance(0))");
        } catch (Exception e) {
            throw new NoSuchElementException("No element" + e);
        }
    }



    public static List<Integer> itemQuantities(int numofItems) throws Exception {

        List<Integer> list = new ArrayList<Integer>();
        for(int i=0; i<numofItems; i++){
            list.add(ThreadLocalRandom.current().nextInt(1, 3));
        }

        return list;

    }

    public static int NumOfItems(int iTestCaseRow) throws Exception {

        String[] Quantities = Functions.split(iTestCaseRow, Constant.Col_Quantities);
        int k=0;
        int i=0;
        while(k<Quantities.length){
            i = i + Integer.parseInt(Quantities[k]);
            k++;
        }
        return i;
    }



    public static void failClose() throws Exception {
        DataFileReader d = new DataFileReader();
        String bundleid = d.getData("bundleid");
        switch (deviceType) {
            case "Android":
                String packageName = ((AndroidDriver) androidDriver).getCurrentPackage();
                System.out.println(packageName);
                androidDriver.terminateApp("com.cytracom.cytracommobile");
                System.out.println("App Terminated");
                androidDriver.activateApp("com.cytracom.cytracommobile");
                //Utils.LaunchAndroidApp();
                System.out.println("Relaunched");
                try{
                    Thread.sleep(10000);
                    //Functions.waitForFeedbackPopup();
                    //Functions.FeedbackPopup();
                }catch(Exception e){
                    System.out.println("No Feedback Popup");
                }
                SignInAction.SignInFlow();
                break;
            case "iOS":
                iosDriver.terminateApp(bundleid);
                iosDriver.activateApp(bundleid);
                try{
                    Thread.sleep(10000);
                    //Functions.waitForFeedbackPopup();
                    Functions.FeedbackPopup();
                }catch(Exception e){
                    System.out.println("No Feedback Popup");
                }
                SignInAction.SignInFlow();
                break;
            default:
                System.out.println("Neither Android nor iOS");
                break;
        }
//        try {
////                wait.until(ExpectedConditions.presenceOfElementLocated(waitForClose()));
//                Close().click();
//        }catch(Exception e){
//            System.out.println("Failed to close");
//            System.out.println(e.getMessage());
//        }
//        try{
////            wait.until(ExpectedConditions.presenceOfElementLocated(waitForbuttonHome()));
//            if(buttonHome().isDisplayed()) {
//                buttonHome().click();
//            }
//        }catch(Exception e){
//            Thread.sleep(5000);
//            if(Close().isDisplayed()) {
//                Close().click();
////                wait.until(ExpectedConditions.presenceOfElementLocated(waitForbuttonHome()));
//                buttonHome().click();
//                System.out.println("Closed and Clicked Home");
//            }
//            System.out.println(e.getMessage());
//        }
    }

    public static void failCloseForRegisterTCs() throws Exception {
        DataFileReader d = new DataFileReader();
        String bundleid = d.getData("bundleid");
        switch (deviceType) {
            case "Android":
                String packageName = ((AndroidDriver) androidDriver).getCurrentPackage();
                System.out.println(packageName);
                androidDriver.terminateApp(packageName);
                androidDriver.activateApp(packageName);
                SignInScreen.waitForbuttonRegister();
                break;
            case "iOS":
                iosDriver.terminateApp(bundleid);
//                iosDriver.activateApp(bundleid);
//                SignInScreen.waitForbuttonRegister();
                break;
            default:
                System.out.println("Neither Android nor iOS");
                break;
        }
    }





    public static WebElement Back() {
        WebElement element = null;
            switch (deviceType) {
                case "Android":
                    element = androidDriver.findElement(By.xpath("//android.widget.Button[@content-desc=\"arrow-back\"]/android.widget.TextView"));
                    return element;
                case "iOS":
                    element = iosDriver.findElement(new MobileBy.ByAccessibilityId("arrow-back"));
                    return element;
                default:
                    System.out.println("Neither Android nor iOS");
                    return element;
            }
    }



    public static WebElement itemSearchCancel() {
        WebElement element = null;
        try {
            switch (deviceType) {
                case "Android":
                    element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Cancel']"));
                    return element;
                case "iOS":
                    element = iosDriver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"Cancel\"])[2]"));
                    return element;
                case "default":
                    element = null;
                    System.out.println("Neither Android nor iOS");
                    return element;
            }
        }catch (Exception e){

        }
        return null;
    }

    public static void waitForitemSearchCancel() {
        try {
            switch (deviceType) {
                case "Android":
                    waitForElement("xpath","//android.widget.TextView[@text='Cancel']",5);
                    break;
                case "iOS":
                    waitForElement("xpath","(//XCUIElementTypeOther[@name=\"Cancel\"])[2]",5);
                    break;
                case "default":
                    System.out.println("Neither Android nor iOS");
                    break;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void btnAccountInvisible() {
        wait = new WebDriverWait(iosDriver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(new MobileBy.ByAccessibilityId("Account")));
    }

    public static void waitForitemSearchCancelInvisible() {

        By element = null;

        switch (deviceType){
            case "Android":
                wait = new WebDriverWait(androidDriver, 10);
                element = By.xpath("//android.widget.Button[@content-desc='Cancel']");
                wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
                break;

            case "iOS":
                wait = new WebDriverWait(iosDriver, 10);
                element = By.xpath("(//XCUIElementTypeOther[@name=\"Cancel\"])[2]");
                wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
                break;

            case "default":
                System.out.println("Neither Android nor iOS");
                break;
        }
    }



    public static By waitforarrowCancel() {
        By element = By.xpath("//android.widget.TextView[@text='\uE5CD']");
        return element;
    }

    public static By waitForInProgressOrdView(){
        By element = By.xpath("//android.widget.TextView[@text='VIEW']");
        return element;
    }

    public static void confirmHome() {
        try{
            switch (deviceType){
                case "Android":
                    waitForElement("xpath", "//android.widget.TextView[@text='Home']", 20);
                    break;
                case "iOS":
                    Functions.waitForElement("xpath","//XCUIElementTypeButton[@name=\"Home\"]", 20);
                    break;
                default:
                    System.out.println("Neither Android nor iOS");
                    break;
            }
        }catch (Exception e){
            ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());
        }
    }

    public static WebElement Close() {
        WebElement element = null;
        switch (deviceType){
            case "Android":
                element = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Home']"));
                return element;
            case "iOS":
                element = iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Home\"]"));
                return element;
            default:
                System.out.println("Neither Android nor iOS");
                return element;
        }
    }

    public static void restartApp(){
        switch (deviceType){
            case "Android":

            case "iOS":
                iosDriver.terminateApp("com.");
                System.out.println("Done");
                iosDriver.findElement(new MobileBy.ByAccessibilityId("7")).click();
                break;
            default:
                System.out.println("Neither Android nor iOS");
                break;
        }
    }

    public static By waitForbuttonHome() {
        By element = By.xpath("//android.widget.TextView[@text='Home']");
        return element;
    }



    public static void waitForCommentsScreen() {
        try {
            switch (deviceType) {
                case "Android":
                    waitForElement("xpath","//android.widget.TextView[@text='here']",10);
                    break;
                case "iOS":
                    waitForElement("id", "here", 10);
                    Thread.sleep(5000);
                    System.out.println("waited for Comments screen");
                    break;
                default:
                    System.out.println("Neither Android nor iOS");
                    break;
            }
        }catch(Exception e){
            ExtentTestManager.getTest().log(Status.FAIL, e);
        }
    }



    public static void enterComments(){
        try {
            switch (deviceType) {
                case "Android":
                    //              wait.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText")));
                    androidDriver.findElement(By.className("android.widget.EditText")).click();
                    androidDriver.findElement(By.className("android.widget.EditText")).sendKeys("Issue");
                    //androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
                    break;
                case "iOS":
                    iosDriver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"Give us your like feedback\"])[2]")).click();
                    iosDriver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"Give us your like feedback\"])[2]")).sendKeys("Issue");
                    break;
                default:
                    System.out.println("Neither Android nor iOS");
                    break;
            }
        }catch(Exception e){
            ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());
        }
    }









    public static void returnToHome() {
        switch (deviceType){
            case "Android":
                androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Home']")).click();
                break;
            case "iOS":
                iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Home\"]")).click();
                break;
            default:
                System.out.println("Neither Android nor iOS");
                break;
        }
        Log.info("Returned to Home");
    }



    public static void waitForLoadingToAppear(){
        try{
            switch (deviceType){
                case "Android":
                    wait = new WebDriverWait(androidDriver, 5);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ProgressBar")));
                    break;
                case "iOS":
                    wait = new WebDriverWait(iosDriver, 5);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeActivityIndicator[@name=\"In progress\"]")));
                    break;
            }
        }catch (Exception e){
        }

    }

    public static void waitForLoadingToDisappear(){
        try{
            switch (deviceType){
                case "Android":
//                    wait = new WebDriverWait(androidDriver, Duration.ofSeconds(2));
//                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.ProgressBar")));
                    wait = new WebDriverWait(androidDriver, 30);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
                    break;
                case "iOS":
//                    wait = new WebDriverWait(iosDriver, Duration.ofSeconds(2));
//                    //wait.until(ExpectedConditions.invisibilityOfElementLocated(new MobileBy.ByAccessibilityId("Location Close")));
//                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//XCUIElementTypeActivityIndicator[@name=\"In progress\"]")));
                    wait = new WebDriverWait(iosDriver, 30);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//XCUIElementTypeActivityIndicator[@name=\"In progress\"]")));
                    break;
                default:
                    System.out.println("LoadingtoDisappear - Neither Android nor iOS");
                    break;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public static void iosRegisterWait() {
        wait = new WebDriverWait(iosDriver, 20);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(new MobileBy.ByAccessibilityId("Location Close")));
        System.out.println("Waited Till Location Close");
    }

    public static String getTimeStamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long time = timestamp.getTime();
        String str = String.valueOf(time);
        return str.substring(4, 10);
    }

    public static void keyEnter(String str) throws InterruptedException {
        switch (deviceType) {
            case "Android":
                Thread.sleep(2000);
                androidDriver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
                androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
               break;
            case "iOS":
                waitForElement("xpath", "//XCUIElementTypeButton[@name=\"Clear Text\"]/following::XCUIElementTypeOther", 15);
                Thread.sleep(2000);
                iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Clear Text\"]/following::XCUIElementTypeOther")).click();
                break;
            default:
                System.out.println("keyEnter - Neither Android nor iOS");
                break;
        }
    }

    public static void keyEnterGoldPass(String str) throws InterruptedException {
        switch (deviceType) {
            case "Android":
                Thread.sleep(2000);
//                androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
                waitForElement("xpath", "//android.view.ViewGroup[@content-desc=\"" + str + "\"]/android.widget.TextView[2]", 10);
                androidDriver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"" + str + "\"]/android.widget.TextView[2]")).click();
                //android.view.ViewGroup[@content-desc="2711 McKinney Avenue, Dallas, TX 75204, USA"]/android.widget.TextView[2]
                break;
            case "iOS":
                waitForElement("xpath", "(//XCUIElementTypeOther[@name=\"" + str + "\"])[3]", 15);
                iosDriver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"" + str + "\"])[3]")).click();
                break;
            default:
                System.out.println("keyEnter - Neither Android nor iOS");
                break;
        }
    }



    public static void FeedbackPopup() {
        try {
            switch (deviceType) {
                case "Android":
                    if (androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Rate your order']")).getText().equals("Rate your order")) {
                        androidDriver.findElement(By.xpath("//android.widget.TextView[@text='CLOSE']")).click();
                    }
                    break;
                case "iOS":
                    if(iosDriver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Rate your order\"]")).getAttribute("name").equals("Rate your order")){
                        iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Close Button\"]")).click();
                    }
                    break;
                default:
                    System.out.println("Neither Android nor iOS");
                    break;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void RoundUp(boolean Flag){
        try{
            Thread.sleep(3000);
            switch (deviceType) {
                case "Android":
//            wait.until(ExpectedConditions.presenceOfElementLocated(PlaceOrderPage.waitForbuttonRoundUp()));
//                    if (PlaceOrderScreen.buttonRoundUp().isDisplayed() && Flag != true) {
//                        //System.out.println("Not Today");
//                        PlaceOrderScreen.buttonNotToday().click();
//                    } else {
//                        //System.out.println("Round Up");
//                        PlaceOrderScreen.buttonRoundUp().click();

                    break;
                case "iOS":
                    if(Flag!=true){
                        //System.out.println("Not Today");
                        (new TouchAction(iosDriver)).tap(PointOption.point(190, 646)).perform();
                        //iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Not Today\"]")).click();
                    }
                    else {
                        //System.out.println("Round Up");
                        (new TouchAction(iosDriver)).tap(PointOption.point(195, 639)).perform();
                        //iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"ROUND UP\"]")).click();
                    }
                    break;
                default:
                    System.out.println("Neither Android nor iOS");
                    break;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }



//    public static void signOut() throws InterruptedException {
//                AccountScreen.buttonAccount().click();
//                AccountScreen.waitForAccountscreen();
//                AccountScreen.buttonSignOut().click();
//                AccountScreen.waitForSignOutPopup();
//                AccountScreen.buttonProceed().click();
//                SignInScreen.waitForbuttonSignIn();
//    }

    public static void waitForClose() throws InterruptedException { }


    public static void chkBasket(){

        switch (deviceType){

            case "Android":
                wait = new WebDriverWait(androidDriver, 10);
                try {
                    if (androidDriver.findElement(By.xpath("//android.widget.Button[@content-desc=\"basket icon\"]")).isDisplayed()) {
                        System.out.println("Basket Check is good");
                        break;
                    }
                }catch(Exception e){
                    System.out.println("Basket is NOT Empty");
                    int i=0;
                    do {
                        i++;
                        try {
                            //androidDriver.findElement(By.xpath("//android.widget.Button[@content-desc=\"" + i + " items in your basket\"]")).click();
                            try {
                                if (androidDriver.findElement(By.id("Cancel")).isDisplayed()) {
                                    itemSearchCancel().click();
                                }
                            }catch(Exception ex1){
                                System.out.println(e.getMessage());
                            }
                            List<WebElement> elements = androidDriver.findElements(By.className("android.widget.Button"));
                            elements.get(4).click();
                            //CheckOutScreen.waitForPayment();
                            String scrollElement = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Items Subtotal\").instance(0))";
                            WebElement element = androidDriver.findElement(MobileBy.AndroidUIAutomator(scrollElement));
                            //break;
                            try {
                                do {
                                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"Delete\"]")));
                                    androidDriver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Delete\"]")).click();
                                    Thread.sleep(3000);
                                }while (androidDriver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Delete\"]")).isDisplayed());
                            }catch(Exception e2){
                                System.out.println("Delete no longer shown");
                                System.out.println(e2.getMessage());
                            }
                        } catch (Exception e1) {
                            try{
                                if(Close().isDisplayed()){
                                    Close().click();
                                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"basket icon\"]")));
                                    if (androidDriver.findElement(By.xpath("//android.widget.Button[@content-desc=\"basket icon\"]")).isDisplayed()) {
                                        System.out.println("Basket Check is good");
                                        break;
                                    }
                                }
                            }catch(Exception e2){
                                System.out.println(e2.getMessage());
                            }
                            System.out.println(e1.getMessage());
                        }
                        try {
                            waitForClose();
                            Close().click();
                            Thread.sleep(3000);
                        }catch(Exception e3){
                            System.out.println(e3.getMessage());
                        }
                    }while(!androidDriver.findElement(By.xpath("//android.widget.Button[@content-desc=\"basket icon\"]")).isDisplayed() || i>3);
                    System.out.println(e.getMessage());
                    break;
                }

            case "iOS":
                wait = new WebDriverWait(iosDriver, 10);
                try {
                    if (iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"basket icon\"]")).isDisplayed()) {
                        System.out.println("Basket Check is good");
                        break;
                    }
                }catch(Exception e) {
                    System.out.println("Basket is NOT Empty");
                    int i=0;
                    do {
                        i++;
                        try {
                            //androidDriver.findElement(By.xpath("//android.widget.Button[@content-desc=\"" + i + " items in your basket\"]")).click();
                            try {
                                if (iosDriver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"Cancel\"])[2]")).isDisplayed()) {
                                    itemSearchCancel().click();
                                }
                            }catch(Exception ex1){
                                System.out.println(ex1.getMessage());
                            }
                            List<WebElement> elements = iosDriver.findElements(By.className("XCUIElementTypeButton"));
                            elements.get(7).click();
                            //CheckOutScreen.waitForPayment();

                            RemoteWebElement parent = (RemoteWebElement)iosDriver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Payment\"]"));
                            String parentID = parent.getId();
                            HashMap<String, String> scrollObject = new HashMap<String, String>();
                            scrollObject.put("Items Subtotal", parentID);
                            scrollObject.put("direction", "down");
                            iosDriver.executeScript("mobile:scroll", scrollObject);
                            //break;
                            try {
                                do {
                                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//XCUIElementTypeOther[@name=\"Remove Button\"])[2]")));
                                    iosDriver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"Remove Button\"])[2]")).click();
                                }while (iosDriver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"Remove Button\"])[2]")).isDisplayed());
                            }catch(Exception e2){
                                System.out.println("Remove no longer shown");
                                System.out.println(e2.getMessage());
                            }
                        } catch (Exception e1) {
                            try{
                                if(Close().isDisplayed()){
                                    Close().click();
                                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeButton[@name=\"basket icon\"]")));
                                    if (iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"basket icon\"]")).isDisplayed()) {
                                        System.out.println("Basket Check is good");
                                        break;
                                    }
                                }
                            }catch(Exception e2){
                                System.out.println(e2.getMessage());
                            }
                            System.out.println(e1.getMessage());
                        }
                        try {
                            waitForClose();
                            Close().click();
                            Thread.sleep(3000);
                        }catch(Exception e3){
                            System.out.println(e3.getMessage());
                        }
                    }while(!iosDriver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"basket icon\"]")).isDisplayed() || i>3);
                    System.out.println(e.getMessage());
                    break;
                }

        }
    }

    public static String returnMonth(String month){
        switch (month){
            case "01":
                return "Jan";
            case "02":
                return "Feb";
            case "03":
                return "Mar";
            case "04":
                return "Apr";
            case "05":
                return "May";
            case "06":
                return "Jun";
            case "07":
                return "Jul";
            case "08":
                return "Aug";
            case "09":
                return "Sep";
            case "10":
                return "Oct";
            case "11":
                return "Nov";
            case "12":
                return "Dec";
            default:
                return "";
        }
    }





    public static void androidDownEnter(){

    }

}
