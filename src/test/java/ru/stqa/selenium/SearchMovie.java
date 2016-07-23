package ru.stqa.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SearchMovie extends TestNgTestBase{
	private boolean acceptNextAlert = true;


  @Test
  public void testSearchMovie() throws Exception {
	testLogin();  
    driver.findElement(By.id("q")).clear();
    driver.findElement(By.id("q")).sendKeys("and");
    String xPathQuery = "//div[@id='results']//div[@class='title']"
    		+ "[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'and')]";
    int countSearchElements = driver.findElements(By.xpath(xPathQuery)).size();
	System.out.println("************* countSearchElements = " + countSearchElements);
    driver.findElement(By.id("q")).sendKeys(Keys.ENTER);
    
    testLogout();
  }


  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private void testLogin() throws Exception {
	    driver.get(baseUrl + "/php4dvd/");
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys("admin");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("admin");
	    driver.findElement(By.name("submit")).click();
	    Assert.assertTrue(isElementPresent(By.cssSelector("div.button > div")));
	  }
  
  private void testLogout() throws Exception {
	  Assert.assertTrue(isElementPresent(By.cssSelector("div.button > div")));  
	  driver.findElement(By.linkText("Log out")).click();
	  Assert.assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to log out[\\s\\S]$"));
	  /*  for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.cssSelector("div#login"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000); */
	    }
  
  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
  
}


