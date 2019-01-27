package com.epam.lab.hospitalspring.controller;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class DiagnosisControllerTest {

    public WebDriver driver;

    @Before
    public  void setUpDoctor() {
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
    }

    @Test
    public void testAddDiagnosis() {
        System.out.println("Page title is: " + driver.getTitle());

        WebElement personalTable = driver.findElement(By.id("example"));
        List<WebElement> rows = personalTable.findElements(By.tagName("tr"));
        rows.get(5).click();

        driver.findElement(By.id("cardofpatients")).click();

        WebElement diagnosisDescription = driver.findElement(By.id("diagnosisDescription"));
        diagnosisDescription.sendKeys("Test diagnosis description");
        driver.findElement(By.id("diagnosisSubmit")).click();

        WebElement addPrescription = driver.findElement(By.id("prescriptionDescription"));
        addPrescription.sendKeys("Test prescription3");
        driver.findElement(By.id("prescriptionSubmit")).click();

        driver.findElement(By.id("doPrescription")).click();
        driver.findElement(By.id("closeDiagnosisButton")).click();
    }
}
