package ru.stqa.selenium;


import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;


public class AddMovie extends TestNgTestBase  {

  @Test
  public void testAddMovie() throws Exception {
	int movieCount = driver.findElements(By.cssSelector("div#results a")).size(); 
	System.out.println("************* movieCount before added: " + movieCount);
    driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("3+2");
    driver.findElement(By.name("year")).clear();
    driver.findElement(By.name("year")).sendKeys("1986");
    driver.findElement(By.cssSelector("img[alt=\"Save\"]")).click();
    driver.findElement(By.cssSelector("h1")).click();
    while (driver.findElements(By.cssSelector("div#results a")).size() < movieCount) {}
	System.out.println("************* movieCount after added: " + driver.findElements(By.cssSelector("div#results a")).size());
    movieCount = driver.findElements(By.cssSelector("div#results a")).size() - movieCount;
    if (movieCount == 1) 
    	System.out.println("************* The new movie is successfully added to the list");
    else
    	System.out.println("************* The new movie is NOT added to the list");
    
  }

}
