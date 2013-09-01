/**
 * 
 */
package com.chronosystems.dto;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Set;

import com.chronosystems.entity.ImagemImovel;
import com.chronosystems.entity.Imovel;

/**
 * @author André Valadas
 */
public class ImovelDTO extends Imovel {

	private static final long serialVersionUID = 641587227582774812L;

	private static final NumberFormat decimal = NumberFormat.getInstance(new Locale("pt", "BR"));
	private static final NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

	/**
	 * Default constructor 
	 */
	public ImovelDTO(final Imovel owner) {
		super();
		
		setId(owner.getId());
		setEstado(owner.getEstado());
		setCidade(owner.getCidade());
		setBairro(owner.getBairro());
		setSiteBusca(owner.getSiteBusca());
		setTipoOperacao(owner.getTipoOperacao());
		setTipoImovel(owner.getTipoImovel());
		setCategoriaImovel(owner.getCategoriaImovel());
		setUrlAnuncio(owner.getUrlAnuncio());
		setCodigoAnuncio(owner.getCodigoAnuncio());
		setTituloResumo(owner.getTituloResumo());
		setCaracteristicasResumo(owner.getCaracteristicasResumo());
		setDescricaoResumo(owner.getDescricaoResumo());
		setImgDestaque(owner.getImgDestaque());
		setAnunciante(owner.getAnunciante());
		setTelefone(owner.getTelefone());
		setDormitorios(owner.getDormitorios());
		setBoxGaragem(owner.getBoxGaragem());
		setAreaTotal(owner.getAreaTotal());
		setAreaPrivativa(owner.getAreaPrivativa());
		setValor(owner.getValor());
		setEndereco(owner.getEndereco());
		setLatitude(owner.getLatitude());
		setLongitude(owner.getLongitude());
		setTipoLocalizacao(owner.getTipoLocalizacao());
		setTotalImagens(owner.getTotalImagens());
		setDataInclusao(owner.getDataInclusao());
		setDataAlteracao(owner.getDataAlteracao());
		setAtivo(owner.getAtivo());

		final Set<ImagemImovel> imagens = owner.getImagens();
		for (ImagemImovel imagemImovel : imagens) {
			final ImagemImovel imagem = new ImagemImovel();
			imagem.setUrl(imagemImovel.getUrl());
			imagem.setDescricao(imagemImovel.getDescricao());
			getImagens().add(imagem);
		}
	}

	public String getDescricaoResumo() {
		if (super.getDescricaoResumo().indexOf("\"") > 0) {
			setDescricaoResumo(super.getDescricaoResumo().replaceAll("\"", "'"));
		}
		return super.getDescricaoResumo();
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