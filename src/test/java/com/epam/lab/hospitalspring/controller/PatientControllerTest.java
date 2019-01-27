package com.epam.lab.hospitalspring.controller;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PatientControllerTest {

    public WebDriver driver = InitRoles.initDoctor();

    @Test
    public void testGetPatients() {
        InitRoles.initDoctor();
    }

    @Test
    public void testGetDeletedPatients() {
        driver.findElement(By.id("deletedpatients")).click();
    }

    @Test
    public void testGetPatientDiagnosisCard() {
        WebElement personalTable = driver.findElement(By.id("example"));
        personalTable.findElements(By.tagName("tr")).get(5).findElement(By.id("patientcard")).click();
    }

    @Test
    public void testGetAddPatientPage() {
        driver.findElement(By.id("addpatient")).click();
    }

    @Test
    public void testAddPatient() {
        driver.findElement(By.id("addpatient")).click();
        driver.findElement(By.id("firstname")).sendKeys("Hello");
        driver.findElement(By.id("lastname")).sendKeys("World");
        driver.findElement(By.id("addpatient")).click();
    }

    @Test
    public void testShowPatientProfile() {
        WebElement personalTable = driver.findElement(By.id("example"));
        personalTable.findElements(By.tagName("tr")).get(5).findElement(By.id("patientprofile")).click();
    }

    @Test
    public void testUpdatePatientProfile() {
        WebElement personalTable = driver.findElement(By.id("example"));
        personalTable.findElements(By.tagName("tr")).get(5).findElement(By.id("patientprofile")).click();
        driver.findElement(By.id("firstname")).sendKeys("HelloTest");
        driver.findElement(By.id("lastname")).sendKeys("WorldTest");
        driver.findElement(By.id("updatepatient")).click();
    }

}