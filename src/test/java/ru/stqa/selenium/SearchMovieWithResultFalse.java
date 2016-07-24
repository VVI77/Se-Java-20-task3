package ru.stqa.selenium;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;


public class SearchMovieWithResultFalse extends TestNgTestBase{
	private boolean acceptNextAlert = true;


  @Test
  public void testSearchMovieResFalse() throws Exception {
	testLogin();
	Thread.sleep(500);
	int movieCount = driver.findElements(By.xpath("//div[@id='results']/a")).size();
	if (movieCount == 0) {
		  addOneMovie("Forrest Gump", "1994");
		  addOneMovie("Gladiator", "2000");
		  addOneMovie("The Prestige", "2006");
		  addOneMovie("Back to the Future", "1985");
		  addOneMovie("Pulp Fiction", "1994");
	}
	String searchWord = "Andsfgsdaaf";
    driver.findElement(By.id("q")).clear();
    driver.findElement(By.id("q")).sendKeys(searchWord);
    String xPathQuery = "//div[@id='results']//div[@class='title']"
    		+ "[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
    		+ searchWord.toLowerCase() + "')]";
    int countSearchElements = driver.findElements(By.xpath(xPathQuery)).size();
    driver.findElement(By.id("q")).sendKeys(Keys.ENTER);
    System.out.println("************* String for search: '" + searchWord + "'.");
    if (countSearchElements == 0 ){
    	while (!isElementPresent(By.xpath("//div[@id='results']/div[@class='content']"))) {};
    	System.out.println("************* No movies where found.");
    }
    else 
    	throw new RuntimeException("Error! One or more movies found...");
    	
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
  
  private void addOneMovie(String movieName, String movieYear) throws Exception {
	  int movieCount = driver.findElements(By.xpath("//div[@id='results']/a")).size();   
	     driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
	     driver.findElement(By.name("name")).clear();
	     driver.findElement(By.name("name")).sendKeys(movieName);
	     driver.findElement(By.name("year")).clear();
	     driver.findElement(By.name("year")).sendKeys(movieYear);
	     driver.findElement(By.cssSelector("img[alt=\"Save\"]")).click();
	     driver.findElement(By.cssSelector("h1")).click();
	     while (driver.findElements(By.cssSelector("div#results a")).size() < movieCount) {}
	     movieCount = driver.findElements(By.cssSelector("div#results a")).size() - movieCount;
	     if (movieCount == 1) 
	      System.out.println("************* The movie " + movieName + " (" + movieYear + 
	        ") is successfully added to the list");
	     else
	      throw new RuntimeException("************* The new movie is NOT added to the list");
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


