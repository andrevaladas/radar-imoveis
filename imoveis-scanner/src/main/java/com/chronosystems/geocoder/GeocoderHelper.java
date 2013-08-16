/**
 * 
 */
package com.chronosystems.geocoder;

import java.util.List;

import com.chronosystems.entity.Bairro;
import com.chronosystems.entity.Cidade;
import com.chronosystems.entity.Imovel;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderAddressComponent;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderResultType;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;

/**
 * Classe utilitária configurações de localização
 * 
 * @author André Valadas
 */
public final class GeocoderHelper {

	public static synchronized void configureLocation(final Imovel imovel, final LatLng location) {
		final Geocoder geocoder = new Geocoder();
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setLocation(location).setLanguage("pt-BR").getGeocoderRequest();
		GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
		if (GeocoderStatus.OK.equals(geocoderResponse.getStatus())) {
			final GeocoderResult geocoderResult = geocoderResponse.getResults().get(0);

			//Endereço
			imovel.setEndereco(geocoderResult.getFormattedAddress());

			//coordenadas
			imovel.setLatitude(location.getLat());
			imovel.setLongitude(location.getLng());

			final List<GeocoderAddressComponent> addressComponents = geocoderResult.getAddressComponents();
			for (GeocoderAddressComponent geocoderAddressComponent : addressComponents) {
				final List<String> types = geocoderAddressComponent.getTypes();
				for (String type : types) {
					info("-------------------");

					// logradouro
					if (GeocoderResultType.ROUTE.value().equals(type)) {
						info("Logradouro:");
						info(geocoderAddressComponent.getShortName());
						break;
					}
					// bairro
					if (GeocoderResultType.SUBLOCALITY.value().equals(type)) {
						info("Bairro:");
						info(geocoderAddressComponent.getShortName());
						final Bairro bairro = new Bairro();
						bairro.setDescricao(geocoderAddressComponent.getShortName());
						imovel.setBairro(bairro);
						break;
					}
					// cidade
					if (GeocoderResultType.LOCALITY.value().equals(type)) {
						info("Cidade:");
						info(geocoderAddressComponent.getShortName());
						final Cidade cidade = new Cidade();
						cidade.setEstado(imovel.getEstado());
						cidade.setDescricao(geocoderAddressComponent.getShortName());
						imovel.setCidade(cidade);
						break;
					}
					// estado
					if (GeocoderResultType.ADMINISTRATIVE_AREA_LEVEL_1.value().equals(type)) {
						info("Estado:");
						info(geocoderAddressComponent.getLongName());
						info(geocoderAddressComponent.getShortName());
						break;
					}
					// pais
					if (GeocoderResultType.COUNTRY.value().equals(type)) {
						info("País:");
						info(geocoderAddressComponent.getLongName());
						info(geocoderAddressComponent.getShortName());
						break;
					}

					info("Outro:");
					info(type);
					info(geocoderAddressComponent.getLongName());
					info(geocoderAddressComponent.getShortName());
				}
			}
		}
	}
	
	private static void info(String info) {
		//System.out.println("| info: ");
	}
}