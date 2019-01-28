package com.epam.lab.hospitalspring.controller;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    public  WebDriver driver;

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
    public void testLoginPage() {
    }
}
