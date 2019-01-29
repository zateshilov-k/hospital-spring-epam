package com.epam.lab.hospitalspring.controller;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PersonalControllerTest {

    public WebDriver driver = InitRoles.initAdmin();

    @Test
    public void testGetPersonals() {
        InitRoles.initAdmin();
    }

    @Test
    public void testGetAddPersonalPage() {
        driver.findElement(By.id("menuAddPersonal")).click();
        driver.findElement(By.id("login")).sendKeys("Test12311@epam.com");
        driver.findElement(By.id("password")).sendKeys("123456qwe");
        driver.findElement(By.id("firstname")).sendKeys("Marina");
        driver.findElement(By.id("lastname")).sendKeys("Avdeeva");
        driver.findElement(By.id("addpersonal")).click();
    }

    @Test
    public void testUpdatePersonal() {
        driver.findElement(By.id("example")).findElements(By.tagName("tr")).get(5).findElement(By.id("openprofile")).click();
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
        personalTable.findElements(By.tagName("tr")).get(5).findElement(By.id("openprofile")).click();
    }

    @Test
    public void testDeletePersonalFromDB() {
        WebElement personalTable = driver.findElement(By.id("example"));
        personalTable.findElements(By.tagName("tr")).get(5).findElement(By.id("deletepersonalDB")).click();
    }

}