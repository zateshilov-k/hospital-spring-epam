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

    @Test
    public void testUpdatePersonal() {
        driver = InitRoles.initAdmin();
        System.out.println("Page title is: " + driver.getTitle());

        WebElement personalTable = driver.findElement(By.id("example"));
        List<WebElement> rows = personalTable.findElements(By.tagName("tr"));
        rows.get(1).click();

        driver.findElement(By.id("openprofile")).click();

//        <--updatePersonal-->
        driver.findElement(By.id("field5")).clear();
        driver.findElement(By.id("field5")).sendKeys("Marina@epam.com");

        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("123456qwe");

        driver.findElement(By.id("field1")).clear();
        driver.findElement(By.id("field1")).sendKeys("Marina");

        driver.findElement(By.id("field2")).clear();
        driver.findElement(By.id("field2")).sendKeys("Avdeeva");

        driver.findElement(By.id("saveprofile")).click();

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