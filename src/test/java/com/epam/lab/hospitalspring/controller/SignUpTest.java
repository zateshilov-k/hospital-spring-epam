package com.epam.lab.hospitalspring.controller;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SignUpTest {
    public WebDriver driver;

    @Test
    public void testSignUp (){
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("test start.");
        driver.get("http://localhost:8080/");
        System.out.println("Page title is: " + driver.getTitle());

        driver.findElement(By.id("signup")).click();
        driver.findElement(By.id("login")).sendKeys("Test@epam.com");
        driver.findElement(By.id("password")).sendKeys("test5");
        driver.findElement(By.id("firstname")).sendKeys("Test");
        driver.findElement(By.id("lastname")).sendKeys("Testovich");
        driver.findElement(By.id("save")).submit();
    }
}
