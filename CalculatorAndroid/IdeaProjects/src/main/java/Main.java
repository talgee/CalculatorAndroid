import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Main {

    private static AndroidDriver<AndroidElement> driver;

    public static Double result ;
    public static ArrayList<Double> results = new ArrayList<>();
    WebDriverWait shortWait = new WebDriverWait(driver, 30);
    public List<AndroidElement> calculatorDigitList = driver.findElements(calculatorElements.calculatorDigitList);

    static CalculatorElements calculatorElements;

    public static void main(String[] args) throws Exception {

    }

    @BeforeClass
    public static void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
        capabilities.setCapability("appPackage", "com.google.android.calculator");
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
//        capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
        capabilities.setCapability("fastReset", true);
        driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub/"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    public void test01_calculatorAdd() throws InterruptedException {

        Thread.sleep(1500);
        //2+3
        shortWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(calculatorElements.digintNo_two)));
        driver.findElement(calculatorElements.digintNo_two).click();
        driver.findElement(calculatorElements.addSign).click();
        driver.findElement(calculatorElements.digintNo_three).click();
        driver.findElement(calculatorElements.equalSign).click();

        saveResults();
    }

    @Test
    public void test02_calculatorReduction(){

        //10-2
        shortWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(calculatorElements.digintNo_one)));
        driver.findElement(calculatorElements.digintNo_one).click();
        driver.findElement(calculatorElements.digintNo_zero).click();
        driver.findElement(calculatorElements.minusSign).click();
        driver.findElement(calculatorElements.digintNo_two).click();
        driver.findElement(calculatorElements.equalSign).click();

        saveResults();
    }

    @Test
    public void test03_calculatorMultification(){

        //(10-2)*2!=20
        driver.findElement(calculatorElements.openScienceCalculatorArrow).click();
        driver.findElement(calculatorElements.lParen).click();
        driver.pressKeyCode(AndroidKeyCode.BACK);
        shortWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(calculatorElements.digintNo_one)));
        driver.findElement(calculatorElements.digintNo_one).click();
        driver.findElement(calculatorElements.digintNo_zero).click();
        driver.findElement(calculatorElements.minusSign).click();
        driver.findElement(calculatorElements.digintNo_two).click();
        shortWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(calculatorElements.openScienceCalculatorArrow)));
        driver.findElement(calculatorElements.openScienceCalculatorArrow).click();
        driver.findElement(calculatorElements.rParen).click();
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driver.findElement(calculatorElements.multificationSign).click();
        driver.findElement(calculatorElements.digintNo_two).click();
        driver.findElement(calculatorElements.equalSign).click();

        saveResults();
    }

    @Test
    public void test04_calculatorTrig(){

        //sin(30)=0.5
        driver.findElement(calculatorElements.openScienceCalculatorArrow).click();
        driver.findElement(calculatorElements.sinSign).click();
        driver.pressKeyCode(AndroidKeyCode.BACK);
        shortWait.until(ExpectedConditions.visibilityOfElementLocated(calculatorElements.digintNo_three));
        driver.findElement(calculatorElements.digintNo_three).click();
        driver.findElement(calculatorElements.digintNo_zero).click();
        driver.findElement(calculatorElements.openScienceCalculatorArrow).click();
        driver.findElement(calculatorElements.rParen).click();
        driver.pressKeyCode(AndroidKeyCode.BACK);
        driver.findElement(calculatorElements.equalSign).click();

        saveResults();
    }

    @Test
    public void test05_assersResults(){

        try {
            Assert.assertEquals(5,results.get(0).intValue());
            System.out.println("the result is correct");
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            Assert.assertEquals(8,results.get(1).intValue());
            System.out.println("the result is correct");
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            Assert.assertNotSame(20,results.get(2).intValue());
            System.out.println("the result is correct");
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            Assert.assertEquals(0.5,results.get(3),0);
            System.out.println("the result is correct");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveResults(){

        result = Double.parseDouble(driver.findElement(By.id("result")).getText());
        results.add(result);
    }
}
