package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import pageObjects.BaseClassiOSAndAndroid;
import pageObjects.CallHistoryPage;
import pageObjects.SignInScreen;
import utilities.*;

import java.net.MalformedURLException;
import java.util.Base64;
import java.util.List;

//************************************************************************************************************************
//************************************************************************************************************************
//                  Sign In Screen related actions
//************************************************************************************************************************
//************************************************************************************************************************

public class SignInAction extends BaseClassiOSAndAndroid {

    public SignInAction() throws MalformedURLException, InterruptedException {
        super(androidDriver, iosDriver);
    }

    static SoftAssert sa= new SoftAssert();

    public static void SignInFlow() throws Exception {

        int i=0;

        //Below for Dont allow
        //(new TouchAction(iosDriver)).tap(PointOption.point(150, 535)).perform();
//        if(deviceType.equals("iOS")) {
//            try{
//                Functions.waitForFeedbackPopup();
//                Functions.FeedbackPopup();
//            }catch(Exception e){
//                System.out.println("No Feedback Popup");
//            }
//            try {
//                Functions.notificationsDontAllowForiOS().click();
//                    Log.info("Dont allow is clicked for Notifications");
//            }catch (Exception e){
//                System.out.println("No Notifications Popup");
//            }

//        }

        //This function looks for Sign In with Email button. If it is present and clicked, i will be set to 1 to continue next step
        //If not, i will remain 0 which means already Signed In
        i = SignInWithEmail(i);

        if(i==1) {
            //Signing In with Username and Password retrieved from DataFile
            SignInWithUnamePwd();
        }

//        DelOrPickup();

    }

//    public static void SignInActionCA(int iTestCaseRow) throws Exception {
//
//        int i=0;
//
//        i = SignInWithEmail(i);
//        System.out.println(i);
//
//        if(i==1) {
//            SignInWithUnamePwd();
//
//            /*try {
//                Functions.waitForbuttonClose();
//                Functions.buttonClose().click();
//            }catch (Exception ex3) {
//                System.out.println("No Unexpected Popup");
//            }*/
//
//        }
//
//        DelOrPickup();
//
//    }
        //Waits for Sign In Button and clicks the button. If not present will throw exception and proceed with the test
        public static int SignInWithEmail(int i) throws InterruptedException {
                try {
                    //SignInScreen.waitForbuttonSignIn();
                    if (SignInScreen.WelcomeToCytracom().isDisplayed()) {

                        i++;
                    }
                    return i;
                }catch(Exception e){
//                    sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.callHistoryMenu()));
                        Log.info("User is Signed In");
                    System.out.println("User  In");
                    return i;
                }
        }


        //This function is to retrieve and enter username and password from data File and Signing In
        public static void SignInWithUnamePwd() throws Exception {

            String Username = new DataFileReader().getData("username");
            String Password = new DataFileReader().getData("password");
            //String encryptedPassword = new DataFileReader().getData("password");
            //String Password = new String(Base64.getDecoder().decode(encryptedPassword.getBytes()));
//            byte[] decoded = Base64.getDecoder().decode(encodedPwd.getBytes());
//
//            System.out.println(new String(decoded));


            SignInScreen.textUsername().clear();
            SignInScreen.textUsername().click();
            SignInScreen.textUsername().sendKeys(Username);
            Log.info("Username Entered");
            System.out.println("Username Entered");


            SignInScreen.textPassword().click();
            SignInScreen.textPassword().sendKeys(Password);
            Log.info("Password Entered");
            System.out.println("Password Entered");


            if (deviceType.equalsIgnoreCase("Android")) {
                androidDriver.hideKeyboard();
            }

            SignInScreen.waitForbtnSignIn();
            SignInScreen.signIn().click();
            Log.info("Clicked SignIn Button");
            System.out.println("Clicked on SignIn Button");

            try{
                SignInScreen.signIn().click();
            }catch(Exception e) {System.out.println("Already Clicked on SignIn button");}


            Thread.sleep(15000);

            List<WebElement> m = androidDriver.findElements(By.xpath("//*"));

            for (int i = 0; i < m.size(); i++) {
                //obtain text
                String s = m.get(i).getText();
                System.out.println("Text is: " + s);
            }
        }

//            try{
//                Functions.waitForFeedbackPopup();
//                Functions.FeedbackPopup();
//            }catch(Exception e){
//                System.out.println("No Feedback Popup");
//            }
            //Wait for call history menu button after Logging in
//              try {
//                  if (SignInScreen.audioPermissionPopup().getText().equals("WHILE USING THE APP")) {
//                      SignInScreen.audioPermissionPopup().click();}
//                  }catch(Exception e) {System.out.println("No Audio Permission Popup");}
//
//            try {
//                if (SignInScreen.callPermissionPopup().getText().equals("Allow Cytracom Mobile to make and manage phone calls?")) {
//                    SignInScreen.allowButton().click();
//                }
//            }catch(Exception e) {System.out.println("No Call Permission Popup");}



//            try {
//                SignInScreen.allowButton().click();
//            }catch(Exception e) {System.out.println("No Permission Popup");}
//            Thread.sleep(4000);
//
//            try{
//                SignInScreen.audioPermissionPopup().click();
//            }catch(Exception e) {System.out.println("No Permission Popup");}
//
//            try{
//                SignInScreen.audioPermissionPopup().click();
//            }catch(Exception e) {System.out.println("No Permission Popup");}
//
//            sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.callHistoryMenu()));
//        }





//                String text = "";
//                //For iOS, Email field has a default text Email. so this is included for iOS. For Android, it is blank
//                if(deviceType.equalsIgnoreCase("iOS")){
//                    text="Email";
//                }
//                if (SignInScreen.textfieldUsername().getText().equals(text)) {
//                    SignInScreen.textUsername().click();
////                    try{
////                        if(AccountScreen.btnClearText().isDisplayed()){
////                            AccountScreen.btnClearText().click();
////                        }
////                    }catch (Exception e){
////
////                    }
//                    SignInScreen.textUsername().sendKeys(Username);
//                        Log.info("Username Entered");
//
//                    SignInScreen.textPassword().click();
//                    SignInScreen.textPassword().sendKeys(Password);
//                        Log.info("Password Entered");
//
//                    SignInScreen.waitForbtnSignIn();
//                    SignInScreen.signIn().click();
//                        Log.info("Clicked SignIn Button");
//
//                    SignInScreen.waitForSigningIn();
//                }
//                else{
//                    SignInScreen.textUsername().click();
//                    AccountScreen.btnClearText().click();
//                    SignInScreen.textUsername().click();
//                    SignInScreen.textUsername().sendKeys(Username);
//                    Log.info("Username Entered");
//
//                    SignInScreen.textPassword().click();
//                    SignInScreen.textPassword().sendKeys(Password);
//                    Log.info("Password Entered");
//
//                    SignInScreen.waitForbtnSignIn();
//                    SignInScreen.signIn().click();
//                    Log.info("Clicked SignIn Button");
//
//                    SignInScreen.waitForSigningIn();
//                }
            /*try {
                Functions.waitForbuttonClose();
                Functions.buttonClose().click();
            }catch (Exception ex3) {
                System.out.println("No Unexpected Popup");
            }*/
            //wait.until(ExpectedConditions.presenceOfElementLocated(SignInPage.waitForPageload()));

//            try{
//                RegisterScreen.waitForCompleteProfile();
//                Functions.Close().click();
//            }catch(Exception e){
//                System.out.println("Verify Profile Popup is not displayed");
//            }
//            if(deviceType.equals("iOS")) {
//                try {
//                    Functions.iOSAskAppNotToTrack().click();
//                }catch (Exception e){
//                    System.out.println("No Popup to Track");
//                }
//            }
//
//                SignInScreen.waitForAddItems();
//                Log.info("User is Signed In");
//
//        }

    //This function is to retrieve and enter username and password from data File for Subscription and Signing In
    public static void SignInWithUnamePwdForSubscriptionCancel(String email) throws Exception {

        SignInScreen.waitForbuttonSignIn();
        if (SignInScreen.buttonSignIn().getText().equals("SIGN IN WITH EMAIL / ") || SignInScreen.buttonSignIn().getText().equals("Sign In With Email/7 Rewards Button")) {
            SignInScreen.buttonSignIn().click();
            Log.info("Sign in with Email button clicked");
        }

        String Username = new DataFileReader().getData(email);
        String encryptedPassword = new DataFileReader().getData("password");
        String Password = new String(Base64.getDecoder().decode(encryptedPassword.getBytes()));

//            byte[] decoded = Base64.getDecoder().decode(encodedPwd.getBytes());
//
//            System.out.println(new String(decoded));

        String text = "";
        //For iOS, Email field has a default text Email. so this is included for iOS. For Android, it is blank
        if (deviceType.equalsIgnoreCase("iOS")) {
            text = "Email";
        }
        if (SignInScreen.textfieldUsername().getText().equals(text)) {
            SignInScreen.textUsername().click();
            SignInScreen.textUsername().sendKeys(Username);
            Log.info("Username Entered");

            SignInScreen.textPassword().click();
            SignInScreen.textPassword().sendKeys(Password);
            Log.info("Password Entered");

            SignInScreen.waitForbtnSignIn();
            SignInScreen.signIn().click();
            Log.info("Clicked SignIn Button");

            //SignInScreen.waitForSigningIn();
        }
    }



}
