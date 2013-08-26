/**
 * 
 */
package com.chronosystems.geocoder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chronosystems.entity.Bairro;
import com.chronosystems.entity.Cidade;
import com.chronosystems.entity.Imovel;
import com.chronosystems.entity.enumeration.Estado;
import com.chronosystems.entity.enumeration.util.EnumUtils;
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
 * TODO Rever o processo de OVER_QUERY_LIMIT
 * 
 * Use of the Google Geocoding API is subject to a query limit of 2,500 requests per day. 
 * (User of Google Maps API for Business may perform up to 100,000 requests per day.) 
 * This limit is enforced to prevent abuse and/or repurposing of the Geocoding API, 
 * and this limit may be changed in the future without notice. 
 * Additionally, we enforce a request rate limit to prevent abuse of the service. 
 * If you exceed the 24-hour limit or otherwise abuse the service, 
 * the Geocoding API may stop working for you temporarily. 
 * If you continue to exceed this limit, your access to the Geocoding API may be blocked.
 * 
 * @link https://developers.google.com/maps/documentation/geocoding/ 
 * 
 * @author André Valadas
 */
public final class GeocoderHelper {

	private static final Map<String, GeocoderResult> addressMap = new HashMap<>();
	private static final Map<LatLng, GeocoderResult> locationMap = new HashMap<>();
	private static boolean skip = true;

	public static void configureDefaultLocation(final Imovel imovel) {
		final Bairro bairro = new Bairro();
		bairro.setDescricao("[Bairro não Encontrado]");
		imovel.setBairro(bairro);

		final Cidade cidade = new Cidade();
		cidade.setEstado(imovel.getEstado());
		cidade.setDescricao("[Cidade não encontrada]");
		imovel.setCidade(cidade);

		//TJ-RS
		imovel.setLatitude(BigDecimal.valueOf(-30.045098697));
		imovel.setLongitude(BigDecimal.valueOf(-51.229248046));
	}

	/**
	 * Cache de endereços para reduzir os acesso ao google 
	 * 
	 * @param address
	 * @return
	 */
	public static synchronized GeocoderResult getGeocoderResult(final String address) {
		if (!skip && !addressMap.containsKey(address)) {
			final Geocoder geocoder = new Geocoder();
			final GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(address).setLanguage("pt-BR").getGeocoderRequest();
			final GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);

			if (GeocoderStatus.OK.equals(geocoderResponse.getStatus())) {
				final GeocoderResult geocoderResult = geocoderResponse.getResults().get(0);
				addressMap.put(address, geocoderResult);
			}
		}
		return addressMap.get(address);
	}

	/**
	 * Cache de coordenadas para reduzir os acessos ao google
	 * 
	 * @param location
	 * @return
	 */
	private static synchronized GeocoderResult getGeocoderResult(final LatLng location) {
		if (!skip && !locationMap.containsKey(location)) {
			final Geocoder geocoder = new Geocoder();
			final GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setLocation(location).setLanguage("pt-BR").getGeocoderRequest();
			final GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);

			if (GeocoderStatus.OK.equals(geocoderResponse.getStatus())) {
				final GeocoderResult geocoderResult = geocoderResponse.getResults().get(0);
				locationMap.put(location, geocoderResult);
			}
		}
		return locationMap.get(location);
	}

	/**
	 * Busca os dados de localização pelas coordenadas latitude/longitude
	 * 
	 * @param imovel
	 * @param location
	 */
	public static synchronized void configureLocation(final Imovel imovel, final LatLng location) {
		//coordenadas
		imovel.setLatitude(location.getLat());
		imovel.setLongitude(location.getLng());

		final GeocoderResult geocoderResult = getGeocoderResult(location);
		if (geocoderResult != null) {
			//Endereço
			imovel.setEndereco(geocoderResult.getFormattedAddress());

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

						/** corrige estado do endereço */
						final String codigoEstado = geocoderAddressComponent.getShortName();
						final Estado estado = EnumUtils.findByValue(Estado.class, codigoEstado);
						if (estado != null) {
							imovel.setEstado(estado);
						}
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
		//System.out.println("| info: "+info);
	}
}