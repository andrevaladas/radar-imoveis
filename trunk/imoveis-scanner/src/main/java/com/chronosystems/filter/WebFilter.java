/**
 * 
 */
package com.chronosystems.filter;

import com.chronosystems.entity.enumeration.TipoImovel;
import com.chronosystems.entity.enumeration.TipoOperacao;

/**
 * @author André Valadas
 */
public class WebFilter {

	private TipoOperacao tipoOperacao;

	private TipoImovel tipoImovel;

	private String url;

	/**
	 * @param tipoOperacao
	 * @param tipoImovel
	 * @param url
	 */
	public WebFilter(TipoOperacao tipoOperacao, TipoImovel tipoImovel, String url) {
		super();
		this.tipoOperacao = tipoOperacao;
		this.tipoImovel = tipoImovel;
		this.url = url;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public TipoImovel getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(TipoImovel tipoImovel) {
		this.tipoImovel = tipoImovel;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}