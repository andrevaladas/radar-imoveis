package com.chronosystems.scanner;

import java.util.List;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderAddressComponent;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderResultType;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;

public class GoogleGeocoderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String endereco = "São Leopoldo - Rio Grande do Sul, Brasil";
		endereco = "Bairro Teresópolis, Porto Alegre / RS";
		final LatLng location = testEndereco(endereco);
		
		testLocation(location);
	}

	private static void testLocation(LatLng location) {
		final Geocoder geocoder = new Geocoder();
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setLocation(location).setLanguage("pt-BR").getGeocoderRequest();
		GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
		if (GeocoderStatus.OK.equals(geocoderResponse.getStatus())) {
			final GeocoderResult geocoderResult = geocoderResponse.getResults().get(0);

			//Endereço
			System.out.println("Endereço:");
			System.out.println(geocoderResult.getFormattedAddress());

			final List<GeocoderAddressComponent> addressComponents = geocoderResult.getAddressComponents();
			for (GeocoderAddressComponent geocoderAddressComponent : addressComponents) {
				final List<String> types = geocoderAddressComponent.getTypes();
				for (String type : types) {
					System.out.println("-------------------");

					// logradouro
					if (GeocoderResultType.ROUTE.value().equals(type)) {
						System.out.println("Logradouro:");
						System.out.println(geocoderAddressComponent.getShortName());
						break;
					}
					// bairro
					if (GeocoderResultType.SUBLOCALITY.value().equals(type)) {
						System.out.println("Bairro:");
						System.out.println(geocoderAddressComponent.getShortName());
						break;
					}
					// bairro
					if (GeocoderResultType.SUBLOCALITY.value().equals(type)) {
						System.out.println("Bairro:");
						System.out.println(geocoderAddressComponent.getShortName());
						break;
					}
					// cidade
					if (GeocoderResultType.LOCALITY.value().equals(type)) {
						System.out.println("Cidade:");
						System.out.println(geocoderAddressComponent.getShortName());
						break;
					}
					// estado
					if (GeocoderResultType.ADMINISTRATIVE_AREA_LEVEL_1.value().equals(type)) {
						System.out.println("Estado:");
						System.out.println(geocoderAddressComponent.getLongName());
						System.out.println(geocoderAddressComponent.getShortName());
						break;
					}
					// pais
					if (GeocoderResultType.COUNTRY.value().equals(type)) {
						System.out.println("País:");
						System.out.println(geocoderAddressComponent.getLongName());
						System.out.println(geocoderAddressComponent.getShortName());
						break;
					}
					
					System.out.println("Outro:");
					System.out.println(type);
					System.out.println(geocoderAddressComponent.getLongName());
					System.out.println(geocoderAddressComponent.getShortName());
				}
			}
		}
	}

	private static LatLng testEndereco(String endereco) {
		LatLng location = null;
		final Geocoder geocoder = new Geocoder();
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(endereco).setLanguage("pt-BR").getGeocoderRequest();
		GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
		if (GeocoderStatus.OK.equals(geocoderResponse.getStatus())) {
			final GeocoderResult geocoderResult = geocoderResponse.getResults().get(0);
			location = geocoderResult.getGeometry().getLocation();
			final List<GeocoderAddressComponent> addressComponents = geocoderResult.getAddressComponents();
			for (GeocoderAddressComponent geocoderAddressComponent : addressComponents) {
				final List<String> types = geocoderAddressComponent.getTypes();
				for (String type : types) {
					System.out.println("-------------------");
					// cidade
					if (GeocoderResultType.LOCALITY.value().equals(type)) {
						System.out.println("Cidade:");
						System.out.println(geocoderAddressComponent.getShortName());
						break;
					}
					// estado
					if (GeocoderResultType.ADMINISTRATIVE_AREA_LEVEL_1.value().equals(type)) {
						System.out.println("Estado:");
						System.out.println(geocoderAddressComponent.getLongName());
						System.out.println(geocoderAddressComponent.getShortName());
						break;
					}
					// pais
					if (GeocoderResultType.COUNTRY.value().equals(type)) {
						System.out.println("País:");
						System.out.println(geocoderAddressComponent.getLongName());
						System.out.println(geocoderAddressComponent.getShortName());
						break;
					}
				}
			}
		}
		return location;
	}

}
