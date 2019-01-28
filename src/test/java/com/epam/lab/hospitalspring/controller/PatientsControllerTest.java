package com.epam.lab.hospitalspring.controller;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class PatientsControllerTest {

    public WebDriver driver;

    @Before
    public  void setUpDoctor() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("test start.");
        driver.get("http://localhost:8080/");

        System.out.println("Page title is: " + driver.getTitle());

        WebElement emailInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        emailInput.sendKeys("doctor@epam.com");
        passwordInput.sendKeys("doctor");
        driver.findElement(By.id("enter")).submit();
    }

    @Test
    public void testUpdatePatient() {
        System.out.println("Page title is: " + driver.getTitle());

        WebElement personalTable = driver.findElement(By.id("example2"));
        List<WebElement> rows = personalTable.findElements(By.tagName("tr"));
        rows.get(2).click();

        driver.findElement(By.id("cardofpatients")).click();

        System.out.println("2");

    }
}

