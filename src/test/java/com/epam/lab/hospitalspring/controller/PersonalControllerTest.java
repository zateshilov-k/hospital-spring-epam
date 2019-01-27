package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.service.PersonalService;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class PersonalControllerTest {

    public WebDriver driver = InitRoles.initAdmin();

    @Autowired
    PersonalService personalService;

    @Test
    public void testGetPersonals() {
        InitRoles.initAdmin();
    }

    @Test
    public void testGetAddPersonalPage() {
        driver.findElement(By.id("menuAddPersonal")).click();
        driver.findElement(By.id("login")).sendKeys("Test@epam.com");
        driver.findElement(By.id("password")).sendKeys("123456qwe");
        driver.findElement(By.id("firstname")).sendKeys("Marina");
        driver.findElement(By.id("lastname")).sendKeys("Avdeeva");
        driver.findElement(By.id("addpersonal")).click();
    }

    @Test
    public void testUpdatePersonal() {
        WebElement personalTable = driver.findElement(By.id("example"));
        personalTable.findElements(By.tagName("tr")).get(10).click();
        driver.findElement(By.id("openprofile")).click();
        driver.findElement(By.id("field5")).clear();
        driver.findElement(By.id("field5")).sendKeys("Marina@epam.com");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("123456qwe");
        driver.findElement(By.id("field1")).clear();
        driver.findElement(By.id("field1")).sendKeys("Marina123");
        driver.findElement(By.id("field2")).clear();
        driver.findElement(By.id("field2")).sendKeys("Avdeeva123");
        driver.findElement(By.id("saveprofile")).click();
    }

    @Test
    public void testGetPersonalPage() {
        WebElement personalTable = driver.findElement(By.id("example"));
        personalTable.findElements(By.tagName("tr")).get(10).click();
        driver.findElement(By.id("openprofile")).click();
    }

    @Test
    public void testDeletePersonalFromDB() {
        WebElement personalTable = driver.findElement(By.id("example"));
        List<WebElement> rows = personalTable.findElements(By.tagName("tr"));
        rows.get(1).click();
        driver.findElement(By.id("deletepersonalDB")).click();
    }

}