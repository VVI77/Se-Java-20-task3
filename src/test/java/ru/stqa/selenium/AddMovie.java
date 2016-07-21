package ru.stqa.selenium;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class AddMovie extends TestNgTestBase  {
	private boolean acceptNextAlert = true;
	private String movieName = "Terminator 2"; 
	private String movieYear = "1991";

  @Test  
  public void testAddMovie() throws Exception {
	testLogin();	  
	int movieCount = driver.findElements(By.cssSelector("div#results a")).size(); 
	System.out.println("************* movieCount before added: " + movieCount);
    driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys(movieName);
	System.out.println("************* movieName for add: " + movieName);
    driver.findElement(By.name("year")).clear();
    driver.findElement(By.name("year")).sendKeys(movieYear);
    driver.findElement(By.cssSelector("img[alt=\"Save\"]")).click();
    driver.findElement(By.cssSelector("h1")).click();
    while (driver.findElements(By.cssSelector("div#results a")).size() < movieCount) {}
	System.out.println("************* movieCount after added: " + driver.findElements(By.cssSelector("div#results a")).size());
    movieCount = driver.findElements(By.cssSelector("div#results a")).size() - movieCount;
    if (movieCount == 1) 
    	System.out.println("************* The new movie is successfully added to the list");
    else
    	System.out.println("************* The new movie is NOT added to the list");
    testLogOut();    
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
  
  private void testLogOut() throws Exception {
	  Assert.assertTrue(isElementPresent(By.cssSelector("div.button > div")));  
	  driver.findElement(By.linkText("Log out")).click();
	  Assert.assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to log out[\\s\\S]$"));
	  /*  for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.cssSelector("div#login"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000); */
	    }
  
  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
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
