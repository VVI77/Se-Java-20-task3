package ru.stqa.selenium;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;


public class RemoveMovie extends TestNgTestBase {
	private boolean acceptNextAlert = true;

  @Test
  public void testRemoveMovie() throws Exception {
	testLogin();  
	int movieCount = driver.findElements(By.cssSelector("div#results a")).size();
	System.out.println("************* movieCount before deleted: " + movieCount);
    driver.findElement(By.cssSelector("div.movie_cover div.nocover")).click();
    driver.findElement(By.cssSelector("img[alt=\"Remove\"]")).click();
    Assert.assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to remove this[\\s\\S]$"));
    while (driver.findElements(By.cssSelector("div#results a")).size() < movieCount-1) {}
	System.out.println("************* movieCount after deleted: " + driver.findElements(By.cssSelector("div#results a")).size());
    movieCount = movieCount - driver.findElements(By.cssSelector("div#results a")).size();
    if (movieCount == 1) 
    	System.out.println("************* The movie is successfully deleted from the list");
    else
    	System.out.println("************* The movie is NOT deleted from the list");
    testLogout();
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


private void testLogin() throws Exception {
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


private void testLogout() throws Exception {
  Assert.assertTrue(isElementPresent(By.cssSelector("div.button > div")));  
  driver.findElement(By.linkText("Log out")).click();
  Assert.assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to log out[\\s\\S]$"));
  /*  for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("div#login"))) break; } catch (Exception e) {}
    	Thread.sleep(1000); */
    }


}
