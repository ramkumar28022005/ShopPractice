package com.shoppractice.tests;

import com.shoppractice.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class ScratchTest {

    public static void main(String[] args) throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        
        driver.get("https://rahulshettyacademy.com/client");
        
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login("anshika@gmail.com", "Iamking@000");
        OrderHistoryPage ordersPage = dashboardPage.goToOrdersPage();
        OrderDetailsPage detailsPage = ordersPage.viewOrderDetails("ZARA COAT 3");
        Thread.sleep(2000);
        System.out.println("HTML_START");
        System.out.println(driver.getPageSource());
        System.out.println("HTML_END");
        
        driver.quit();
    }
}
