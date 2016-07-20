package ru.stqa.selenium;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;


public class RemoveMovie extends TestNgTestBase {
	private boolean acceptNextAlert = true;

  @Test
  public void testRemoveMovie() throws Exception {
	int movieCount = driver.findElements(By.cssSelector("div#results a")).size();
	System.out.println("************* movieCount before deleted: " + movieCount);
    driver.findElement(By.cssSelector("div.movie_cover div.nocover")).click();
    //Assert.assertTrue(isElementPresent(By.cssSelector("img[alt=\"Remove\"]")));
    driver.findElement(By.cssSelector("img[alt=\"Remove\"]")).click();
    Assert.assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to remove this[\\s\\S]$"));
    while (driver.findElements(By.cssSelector("div#results a")).size() < movieCount-1) {}
	System.out.println("************* movieCount after deleted: " + driver.findElements(By.cssSelector("div#results a")).size());
    movieCount = movieCount - driver.findElements(By.cssSelector("div#results a")).size();
    if (movieCount == 1) 
    	System.out.println("************* The movie is successfully deleted from the list");
    else
    	System.out.println("************* The movie is NOT deleted from the list");
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
