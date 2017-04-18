/**
 * Created by aviz on 31/12/2016.
 */

import java.io.*;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Bus {

    private static WebDriver driver;
    private static Properties prop = new Properties();
    private static final String propFileName = "src/main/resources/properties.properties";
    private static InputStream input = null;

    public static void main (String [] args){

        String firstName = null;
        String lastName = null;
        String idNumber = null;
        String city = null;
        String streetName = null;
        String streetNumber = null;
        String email = null;
        String phoneNumber = null;
        String busCompany = null;
        String busNumber = null;
        String busStation = null;
        String topic = null;
        String details = null;
        String time = null;

        try {
            input = new FileInputStream(propFileName);
            // load a properties file
            //prop.load(input);
            prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));

            firstName = prop.getProperty("firstName");
            lastName = prop.getProperty("lastName");
            idNumber = prop.getProperty("idNumber");
            city = prop.getProperty("city");
            streetName = prop.getProperty("streetName");
            streetNumber = prop.getProperty("streetNumber");
            email = prop.getProperty("email");
            phoneNumber = prop.getProperty("phoneNumber");
            busCompany = prop.getProperty("busCompany");
            busNumber = prop.getProperty("busNumber");
            busStation = prop.getProperty("busStation");
            topic = prop.getProperty("topic");
            details = prop.getProperty("details");
            time = prop.getProperty("time");

        } catch (IOException e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                input.close();
            } catch (IOException ef) {
                System.out.println("Exception: " + ef);
            }
        }

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

        driver = new ChromeDriver();
        driver.get("https://motssl5.mot.gov.il/FORMS/he/compl-pbl/plaint-public-transport-form");
        driver.findElement(By.id("ff_elem1123")).sendKeys(firstName);

        driver.findElement(By.id("ff_elem1119")).click();
        driver.findElement(By.id("ff_elem1119")).sendKeys(lastName);

        driver.findElement(By.id("ff_elem1120")).click();
        driver.findElement(By.id("ff_elem1120")).sendKeys(idNumber);

        driver.findElement(By.className("bfNextButton")).click();

        driver.findElement(By.id("ff_elem1124")).click();
        driver.findElement(By.id("ff_elem1124")).sendKeys(city);


        driver.findElement(By.id("ff_elem1118")).click();
        driver.findElement(By.id("ff_elem1118")).sendKeys(streetName);

        driver.findElement(By.id("ff_elem1661")).click();
        driver.findElement(By.id("ff_elem1661")).sendKeys(streetNumber);

        driver.findElement(By.xpath("//*[@id='bfPage2']/div[4]/button[2]")).click();

        driver.findElement(By.id("ff_elem1122")).click();
        driver.findElement(By.id("ff_elem1122")).sendKeys(phoneNumber);

        driver.findElement(By.id("ff_elem1121")).click();
        driver.findElement(By.id("ff_elem1121")).sendKeys(email);

        driver.findElement(By.xpath("//*[@id='bfPage3']/div[4]/button[2]")).click();

        driver.findElement(By.id("ff_elem1139")).click();
        driver.findElement(By.id("ff_elem1139")).sendKeys(busCompany);

        driver.findElement(By.id("ff_elem1138")).click();
        driver.findElement(By.id("ff_elem1138")).sendKeys(busNumber);

        driver.findElement(By.id("ff_elem1141")).click();
        driver.findElement(By.id("ff_elem1141")).sendKeys(busStation);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String requiredDate = df.format(new Date());

        System.out.println(requiredDate);
        driver.findElement(By.id("ff_elem1125")).sendKeys(requiredDate);

        driver.findElement(By.id("ff_elem1126")).click();
        driver.findElement(By.id("ff_elem1126")).sendKeys(busNumber + topic + time);

        driver.findElement(By.id("ff_elem1127")).click();
        driver.findElement(By.id("ff_elem1127")).sendKeys(busNumber + details + time);
        driver.findElement(By.xpath("//*[@id='bfPage4']/div[4]/button[2]")).click();

        String captcha = driver.findElement(By.id("recaptcha_image")).getText();
        System.out.println(captcha);
    }
}
