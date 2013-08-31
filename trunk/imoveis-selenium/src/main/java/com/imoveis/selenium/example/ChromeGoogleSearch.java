package com.imoveis.selenium.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeGoogleSearch {
	public static void main(String[] args) {
		// Optional, if not specified, WebDriver will search your path for
		// chromedriver.
		System.setProperty("webdriver.chrome.driver", "/Program Files (x86)/Google/Chrome/Application/chrome.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com/xhtml");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("ChromeDriver");
		searchBox.submit();
		driver.quit();
	}
}
