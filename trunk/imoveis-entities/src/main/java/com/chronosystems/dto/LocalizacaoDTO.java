/**
 * 
 */
package com.chronosystems.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author André Valadas
 */
public class LocalizacaoDTO implements Serializable {

	private static final long serialVersionUID = -3367411322779509343L;

	private BigDecimal latitude;
	private BigDecimal longitude;

	/**
	 * Default constructor 
	 */
	public LocalizacaoDTO(final BigDecimal latitude, final BigDecimal longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
}