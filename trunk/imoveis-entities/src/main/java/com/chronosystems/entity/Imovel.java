package com.chronosystems.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.chronosystems.entity.enumeration.CategoriaImovel;
import com.chronosystems.entity.enumeration.Estado;
import com.chronosystems.entity.enumeration.SimNao;
import com.chronosystems.entity.enumeration.SiteBusca;
import com.chronosystems.entity.enumeration.TipoImovel;
import com.chronosystems.entity.enumeration.TipoLocalizacao;

/**
 * Classe Imovel
 * 
 * @author Andr� Valadas
 */
@Entity
public class Imovel implements Serializable {
	private static final long serialVersionUID = -2610532917952796318L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_IMOVEL", unique = true, nullable = false)
	private Long id;

	/** BASE DO ANUNCIO */
	@Enumerated(EnumType.STRING)
	@Column(name = "CODIGO_ESTADO", nullable = false, length = 2)
	private Estado estado;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SITE_BUSCA", nullable = false, length = 20)
	private SiteBusca siteBusca;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO", nullable = false, length = 2)
	private TipoImovel tipoImovel;

	@Enumerated(EnumType.STRING)
	@Column(name = "CATEGORIA", nullable = false, length = 2)
	private CategoriaImovel categoriaImovel;


	/** RESUMO */
	@Column(name = "URL_ANUNCIO", nullable = false, length = 255)
	private String urlAnuncio;
	
	@Column(name = "CODIGO_ANUNCIO", nullable = false, length = 45)
	private String codigoAnuncio;
	
	@Column(name = "TITULO_RESUMO", nullable = false, length = 255)
	private String tituloResumo;

	@Column(name = "CARACTERISTICA_RESUMO", nullable = false, length = 255)
	private String caracteristicasResumo;

	@Column(name = "DESCRICAO_RESUMO", nullable = false, length = 255)
	private String descricaoResumo;

	@Column(name = "IMG_DESTAQUE", nullable = false, length = 255)
	private String imgDestaque;


	/** DETALHAMENTO */
	@Column(name = "ANUNCIANTE", length = 255)
	private String anunciante;

	@Column(name = "TELEFONE", length = 20)
	private String telefone;
	
	@Column(name = "DORMITORIOS")
	private Integer dormitorios;

	@Column(name = "BOX_GARAGEM")
	private Integer boxGaragem;

	@Column(name = "AREA_TOTAL")
	private BigDecimal areaTotal;

	@Column(name = "AREA_PRIVATIVA")
	private BigDecimal areaPrivativa;
	private BigDecimal valor;

	@Column(nullable = false, length = 1000)
	private String endereco;
	private BigDecimal latitude;
	private BigDecimal longitude;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_LOCALIZACAO", nullable = false, length = 1)
	private TipoLocalizacao tipoLocalizacao;

	@Column(name = "TOTAL_IMAGENS")
	private Integer totalImagens;


	/** STATUS */
	@Temporal(TemporalType.DATE)
	@Column(name = "DT_INCLUSAO", nullable = false, length = 10)
	private Date dataInclusao;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_ALTERACAO", length = 10)
	private Date dataAlteracao;

	@Enumerated(EnumType.STRING)
	@Column(name = "ATIVO", nullable = false, length = 1)
	private SimNao ativo = SimNao.S;

	@OneToMany(mappedBy = "imovel", fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL, orphanRemoval = true)
	private Set<ImagemImovel> imagens = new HashSet<ImagemImovel>(0);

	public Imovel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public SiteBusca getSiteBusca() {
		return siteBusca;
	}

	public void setSiteBusca(SiteBusca siteBusca) {
		this.siteBusca = siteBusca;
	}

	public TipoImovel getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(TipoImovel tipoImovel) {
		this.tipoImovel = tipoImovel;
	}

	public CategoriaImovel getCategoriaImovel() {
		return categoriaImovel;
	}

	public void setCategoriaImovel(CategoriaImovel categoriaImovel) {
		this.categoriaImovel = categoriaImovel;
	}

	public String getUrlAnuncio() {
		return urlAnuncio;
	}

	public void setUrlAnuncio(String urlAnuncio) {
		this.urlAnuncio = urlAnuncio;
	}

	public String getCodigoAnuncio() {
		return codigoAnuncio;
	}

	public void setCodigoAnuncio(String codigoAnuncio) {
		this.codigoAnuncio = codigoAnuncio;
	}

	public String getTituloResumo() {
		return tituloResumo;
	}

	public void setTituloResumo(String tituloResumo) {
		this.tituloResumo = tituloResumo;
	}

	public String getCaracteristicasResumo() {
		return caracteristicasResumo;
	}

	public void setCaracteristicasResumo(String caracteristicasResumo) {
		this.caracteristicasResumo = caracteristicasResumo;
	}

	public String getDescricaoResumo() {
		return descricaoResumo;
	}

	public void setDescricaoResumo(String descricaoResumo) {
		this.descricaoResumo = descricaoResumo;
	}

	public String getImgDestaque() {
		return imgDestaque;
	}

	public void setImgDestaque(String imgDestaque) {
		this.imgDestaque = imgDestaque;
	}

	public String getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(String anunciante) {
		this.anunciante = anunciante;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getDormitorios() {
		return dormitorios;
	}

	public void setDormitorios(Integer dormitorios) {
		this.dormitorios = dormitorios;
	}

	public Integer getBoxGaragem() {
		return boxGaragem;
	}

	public void setBoxGaragem(Integer boxGaragem) {
		this.boxGaragem = boxGaragem;
	}

	public BigDecimal getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(BigDecimal areaTotal) {
		this.areaTotal = areaTotal;
	}

	public BigDecimal getAreaPrivativa() {
		return areaPrivativa;
	}

	public void setAreaPrivativa(BigDecimal areaPrivativa) {
		this.areaPrivativa = areaPrivativa;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public TipoLocalizacao getTipoLocalizacao() {
		return tipoLocalizacao;
	}

	public void setTipoLocalizacao(TipoLocalizacao tipoLocalizacao) {
		this.tipoLocalizacao = tipoLocalizacao;
	}

	public Integer getTotalImagens() {
		return totalImagens;
	}

	public void setTotalImagens(Integer totalImagens) {
		this.totalImagens = totalImagens;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public SimNao getAtivo() {
		return ativo;
	}

	public void setAtivo(SimNao ativo) {
		this.ativo = ativo;
	}

	public Set<ImagemImovel> getImagens() {
		return imagens;
	}

	public void setImagens(Set<ImagemImovel> imagens) {
		this.imagens = imagens;
	}
}