package ru.stqa.selenium;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;


public class SearchMovieWithResultFalse extends TestNgTestBase{
	private boolean acceptNextAlert = true;


  @Test
  public void testSearchMovieResFalse() throws Exception {
	testLogin();  
	String searchWord = "Andsdfasdkqdjasduuuwksd";
    driver.findElement(By.id("q")).clear();
    driver.findElement(By.id("q")).sendKeys(searchWord);
    String xPathQuery = "//div[@id='results']//div[@class='title']"
    		+ "[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
    		+ searchWord.toLowerCase() + "')]";
    int countSearchElements = driver.findElements(By.xpath(xPathQuery)).size();
    driver.findElement(By.id("q")).sendKeys(Keys.ENTER);
    System.out.println("************* String for search: '" + searchWord + "'.");
    if (countSearchElements > 0 ){
    	while (driver.findElements(By.xpath("//div[@id='results']/a")).size() < countSearchElements) {}
    	System.out.println("************* We found and displayed " + countSearchElements + " movies.");
    }
    else {
    	while (!isElementPresent(By.xpath("//div[@id='results']/div[@class='content']"))) {};
    	System.out.println("************* No movies where found.");
    }
    	
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


