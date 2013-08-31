package com.imoveis.selenium.maps.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.imoveis.selenium.maps.LocationSearch;

public abstract class LocationSearchDriver implements LocationSearch {

	protected static WebDriver driver;
	protected static Wait<WebDriver> wait;

	public LocationSearchDriver() {
		initialize();
	}

	@Override
	public void initialize() {
		if (driver == null) {
			driver = new FirefoxDriver();
			wait = new WebDriverWait(driver, 30);
	        driver.get(getUrlLocation());
		}
	}

	protected abstract String getUrlLocation();
	
	@Override
	public void release() {
		if (driver != null) {
			driver.close();
			driver = null;
		}
	}
}
