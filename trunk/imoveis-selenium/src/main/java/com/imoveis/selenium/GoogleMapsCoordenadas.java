/**
 * 
 */
package com.imoveis.selenium;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * 
 * @author User
 *
 */
public class GoogleMapsCoordenadas {

	public static void main(String[] args) {
		final WebDriver driver = new FirefoxDriver();
		final Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        driver.get("http://www.procriativo.com.br/include/app/google-longitude-latitude.php");

        final WebElement addressElement = driver.findElement(By.id("address"));
        final WebElement searchButton = driver.findElement(By.xpath("//input[@value='Procurar']"));

        final List<String> addresses = Arrays.asList("Rua Pedro Lerbach, 840 - Esteio", "Rua Aurora, 1200 - Canoas", "Rua Germano Lang - 1551 - São Leopoldo");
        for (String address : addresses) {
        	//type search query
        	
        	addressElement.clear();
			addressElement.sendKeys(address);

        	// click search
			searchButton.click();

        	// Wait for search to complete
        	wait.until(new ExpectedCondition<Boolean>() {
        		public Boolean apply(WebDriver webDriver) {
        			System.out.println("Searching ...");
        			final WebElement findElement = webDriver.findElement(By.id("formatedAddress"));
					return findElement != null && !findElement.getText().isEmpty();
        		}
        	});
        	
        	// Look for lat/lng  and address result
        	System.out.println(driver.findElement(By.id("latlng")).getText());
        	System.out.println(driver.findElement(By.id("formatedAddress")).getText());
		}
        driver.close();
	}
}
