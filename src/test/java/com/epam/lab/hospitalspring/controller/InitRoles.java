package com.epam.lab.hospitalspring.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class InitRoles {

    public static WebDriver driver;

    public static WebDriver initAdmin() {
        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("test start.");
        driver.get("http://localhost:8080/");

        System.out.println("Page title is: " + driver.getTitle());

        WebElement emailInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        emailInput.sendKeys("admin@epam.com");
        passwordInput.sendKeys("admin");
        driver.findElement(By.id("enter")).submit();
        return driver;
    }

    public static WebDriver initDoctor() {
        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("test start.");
        driver.get("http://localhost:8080/");

        System.out.println("Page title is: " + driver.getTitle());

        WebElement emailInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        emailInput.sendKeys("doctor@epam.com");
        passwordInput.sendKeys("doctor");
        driver.findElement(By.id("enter")).submit();
        return driver;
    }

    public static WebDriver initNurse() {
        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("test start.");
        driver.get("http://localhost:8080/");

        System.out.println("Page title is: " + driver.getTitle());

        WebElement emailInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        emailInput.sendKeys("nurse@epam.com");
        passwordInput.sendKeys("nurse123");
        driver.findElement(By.id("enter")).submit();
        return driver;
    }

}