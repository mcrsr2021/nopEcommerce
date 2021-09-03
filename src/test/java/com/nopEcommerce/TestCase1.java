package com.nopEcommerce;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase1 {

	@Test
	public void loginTest() {
		
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		Assert.assertTrue(driver.findElement(By.id("welcome")).getText().contains("Welcome"));
		
		driver.quit();
	}
	
	@Test(enabled = false)
	public void logoutTest() {
		
			WebDriverManager.firefoxdriver().setup();
			WebDriver driver = new FirefoxDriver();
		
		try {
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			driver.get("https://opensource-demo.orangehrmlive.com/");
			
			driver.findElement(By.id("txtUsername")).sendKeys("Admin");
			driver.findElement(By.id("txtPassword")).sendKeys("admin123");
			driver.findElement(By.id("btnLogin")).click();
			
			Assert.assertTrue(driver.findElement(By.id("welcome")).getText().contains("Welcome"));
			
			WebDriverWait wait = new WebDriverWait(driver,30);
			
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("welcome"))));
			
			driver.findElement(By.xpath("//a[@id='welcome' and @class='panelTrigger']")).click();
			
			Actions action = new Actions(driver);
			
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Logout"))));
			
			action.moveToElement(driver.findElement(By.linkText("Logout"))).click().build().perform();
			
			
			Assert.assertEquals(driver.findElement(By.id("btnLogin")).getAttribute("value"), "LOGIN");
		}
		finally {
			driver.quit();
		}
		
	}
}
