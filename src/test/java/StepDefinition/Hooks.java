package StepDefinition;

import Utilities.Driver;
import Utilities.WriteInExcelReusableMethod;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {

    @Before
    public void before(){
//        Add the navigate to website enter username and password

    }

    /*
        Before


     */

//    This after annotation will run the code after each scenario
//      We should import it from cucumber.api.java
//    Cucumber is providing the scenario object
    @After
    public void after(Scenario scenario){

        System.out.println(scenario.getStatus() + " status of the scenario"); // scenario is passed or failed.
        System.out.println(scenario.getId() + " id of the scenario");
        System.out.println(scenario.getName() + " name of the scenario");

        if(scenario.getStatus().equalsIgnoreCase("failed")) {
//        Take Screen shot is coming from the selenium.
            TakesScreenshot Screenshot = ((TakesScreenshot) Driver.getDriver()); // which driver to get a screen shot

//        File is coming from JAVA
            File srcFile = Screenshot.getScreenshotAs(OutputType.FILE);

            String featureFileName = scenario.getName();

//        Adding the today date and time to our screen shot name
            Date now = new Date();

            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH mm ss");

            String dateInString = formater.format(now);

            dateInString = dateInString.replace(":", " ");

//        Location of the screenshot.
            File destinationFile = new File("target/FailedScreenShots/" + featureFileName + dateInString + ".png");

            try {
                FileUtils.copyFile(srcFile, destinationFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        WriteInExcelReusableMethod.writeInExcelReusable("src\\main\\resources\\Results.xlsx", "Sheet1",scenario);

        Driver.QuitDriver();

        System.out.println("This is the after class");
    }

//    currently it is taking with failed and success scenarious


}



//    //        Take Screen shot is coming from the selenium.
//    TakesScreenshot Screenshot = ((TakesScreenshot)Driver.getDriver()); // which driver to get a screen shot
//
//    //        File is coming from JAVA
//    File srcFile= Screenshot.getScreenshotAs(OutputType.FILE);
//
//    String featureFileID = scenario.getId();
//
//    //        Location of the screenshot.
//    File destinationFile = new File("target/FailedScreenShots/" + featureFileID+".png");
//
//        try {
//                FileUtils.copyFile(srcFile , destinationFile);
//                } catch (IOException e) {
//                e.printStackTrace();
//                }
//
//                Driver.QuitDriver();
//
//                System.out.println("This is the after class");