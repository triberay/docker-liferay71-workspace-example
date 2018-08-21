package com.triberay.docker.info.test;


import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class SimpleTest extends TestCase {

    private WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");

        driver = new RemoteWebDriver(URI.create("http://127.0.0.1:4444/wd/hub").toURL(), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testSimple() {
        this.driver.get("http://httpd");

        WebDriverWait wait = new WebDriverWait(driver, 20 * 1000, 200);
        wait.until(ExpectedConditions.titleIs("Triberay Docker Example Setup - Liferay"));

        String body = this.driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("The portlet is not well deployed", body.contains("Hello from Triberay Docker Info!"));
    }

    @After
    public void tearDown() throws Exception {
        this.driver.quit();
    }
}