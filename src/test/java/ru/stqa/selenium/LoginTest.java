package ru.stqa.selenium;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class LoginTest extends TestNgTestBase {

  @Test
  public void testLogin() throws Exception {
    driver.get(baseUrl + "/php4dvd/");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("submit")).click();
    Assert.assertTrue(isElementPresent(By.cssSelector("div.button > div")));
  }
  
  
private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }
}
