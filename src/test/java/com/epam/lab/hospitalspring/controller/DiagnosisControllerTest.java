package com.epam.lab.hospitalspring.controller;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DiagnosisControllerTest {

    public WebDriver driver = InitRoles.initDoctor();

    @Test
    public void testAddDiagnosis() {
        driver.findElement(By.id("example")).findElements(By.tagName("tr")).get(1).findElement(By.id("patientcard")).click();
        driver.findElement(By.id("diagnosisDescription")).sendKeys("Test diagnosis description");
        driver.findElement(By.id("diagnosisSubmit")).click();

        driver.findElements(By.tagName("tr")).get(31).findElement(By.id("diagnosis")).click();
    }
}