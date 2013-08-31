package com.imoveis.selenium.maps.search;

import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.chronosystems.entity.Imovel;
import com.imoveis.selenium.maps.driver.LocationSearchDriver;

public final class AddressFinder extends LocationSearchDriver {

	static WebElement addressField;
	static WebElement searchButton;

	static AddressFinder instance;
	private AddressFinder() {
		super();
	}

	@Override
	protected String getUrlLocation() {
		return "http://www.procriativo.com.br/include/app/google-longitude-latitude.php";
	}

	public static AddressFinder getInstance() {
		if (instance == null) {
			instance = new AddressFinder();
			addressField = driver.findElement(By.id("address"));
	    	searchButton = driver.findElement(By.xpath("//input[@value='Procurar']"));
		}
		return instance;
	}

	@Override
	public synchronized void configureLocation(final Imovel imovel) {

		//type search query
    	addressField.clear();
		addressField.sendKeys(imovel.getEndereco());

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
    	final String latLng = driver.findElement(By.id("latlng")).getText().replaceAll("\\(", "").replaceAll("\\)", "");
    	final String[] split = latLng.split(",");
		//System.out.println("| lat: "+split[0].trim());
		//System.out.println("| lng: "+split[1].trim());
    	//System.out.println(driver.findElement(By.id("formatedAddress")).getText());

    	imovel.setLatitude(new BigDecimal(split[0].trim()));
    	imovel.setLongitude(new BigDecimal(split[1].trim()));
    	imovel.setEndereco(driver.findElement(By.id("formatedAddress")).getText().trim());
	}

	public static void main(String[] args) {
		final Imovel imovel = new Imovel();
		imovel.setEndereco("Av Borges de Medeiros, 1745, POA");
		AddressFinder.getInstance().configureLocation(imovel);

		System.out.println(imovel.getLatitude());
		System.out.println(imovel.getLongitude());
		System.out.println(imovel.getEndereco());

		imovel.setEndereco("Av Borges de Medeiros, 1745, POA");
		AddressFinder.getInstance().configureLocation(imovel);

		System.out.println(imovel.getLatitude());
		System.out.println(imovel.getLongitude());
		System.out.println(imovel.getEndereco());

		AddressFinder.getInstance().release();
	}
}