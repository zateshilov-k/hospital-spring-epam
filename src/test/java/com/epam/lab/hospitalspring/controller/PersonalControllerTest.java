package com.epam.lab.hospitalspring.controller;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class PersonalControllerTest {

    public WebDriver driver;
    @Before
    public  void setUpAdmin() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("test start.");
        driver.get("http://localhost:8080/");

        System.out.println("Page title is: " + driver.getTitle());

        WebElement emailInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        emailInput.sendKeys("admin@epam.com");
        passwordInput.sendKeys("admin");
        driver.findElement(By.id("enter")).submit();
    }

    @Test
    public void testUpdatePersonal() {
        System.out.println("Page title is: " + driver.getTitle());

        WebElement personalTable = driver.findElement(By.id("example"));
        List<WebElement> rows = personalTable.findElements(By.tagName("tr"));
        rows.get(1).click();

        driver.findElement(By.id("openProfile")).click();

//        <--updatePersonal-->
        driver.findElement(By.id("field5")).clear();
        driver.findElement(By.id("field5")).sendKeys("Marina@epam.com");

        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("12345");

        driver.findElement(By.id("field1")).clear();
        driver.findElement(By.id("field1")).sendKeys("Marina");

        driver.findElement(By.id("field2")).clear();
        driver.findElement(By.id("field2")).sendKeys("Avdeeva");

        driver.findElement(By.id("saveProfile")).click();

////       <--deleted-->
//        WebElement personalTable2 = driver.findElement(By.id("example"));
//        List<WebElement> rows2 = personalTable2.findElements(By.tagName("tr"));
//        rows2.get(4).click();
//
//        driver.findElement(By.id("buttondel")).submit();
////        <--exit-->
//        driver.findElement(By.id("exit")).submit();
    }

//@Test
//public void testDeletePersonalFromDB(){
//    driver.findElement(By.id("personalDeleted")).submit();
//}
//    @Test
//    public void testExitFromAdmin() {
//        driver.findElement(By.id("exit")).submit();
//    }
}