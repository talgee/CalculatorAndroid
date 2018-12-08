import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class CalculatorElements {

    static AndroidDriver driver;

    static By calculatorDigitList = By.xpath("//android.widget.Button[contains(@resource-id,'digit')]");
    static By addSign = By.id("op_add");
    static By equalSign = By.id("eq");
    static By minusSign = By.id("op_sub");
    static By openScienceCalculatorArrow = By.id("arrow");
    static By lParen = By.id("lparen");
    static By rParen = By.id("rparen");
    static By multificationSign = By.id("op_mul");
    static By sinSign = By.id("fun_sin");

    static By digintNo_zero = By.id("digit_0");
    static By digintNo_one = By.id("digit_1");
    static By digintNo_two = By.id("digit_2");
    static By digintNo_three = By.id("digit_3");


    public CalculatorElements (AndroidDriver driver){

        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

}
