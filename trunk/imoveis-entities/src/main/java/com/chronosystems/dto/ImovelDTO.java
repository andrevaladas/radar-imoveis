/**
 * 
 */
package com.chronosystems.dto;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.beans.BeanUtils;

import com.chronosystems.entity.Imovel;

/**
 * @author André Valadas
 */
public class ImovelDTO extends Imovel {

	private static final long serialVersionUID = -3751383696570001447L;

	private static final NumberFormat decimal = NumberFormat.getInstance(new Locale("pt", "BR"));
	private static final NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

	/**
	 * Default constructor 
	 */
	public ImovelDTO(final Imovel owner) {
		super();
		BeanUtils.copyProperties(owner, this);
	}

	public String getAreaTotalFormatted() {
		return decimal.format(getAreaTotal());
	};

	public String getAreaPrivativaFormatted() {
		return decimal.format(getAreaPrivativa());
	}

	public String getValorFormatted() {
        return currency.format(getValor());
	}

	public LocalizacaoDTO getLocalizacao() {
		return new LocalizacaoDTO(getLatitude(), getLongitude());
	}
}