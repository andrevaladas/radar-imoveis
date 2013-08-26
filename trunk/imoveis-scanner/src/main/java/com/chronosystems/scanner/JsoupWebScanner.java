package com.chronosystems.scanner;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.chronosystems.entity.ImagemImovel;
import com.chronosystems.entity.Imovel;
import com.chronosystems.entity.enumeration.CategoriaImovel;
import com.chronosystems.entity.enumeration.Estado;
import com.chronosystems.entity.enumeration.SiteBusca;
import com.chronosystems.entity.enumeration.TipoImovel;
import com.chronosystems.entity.enumeration.TipoLocalizacao;
import com.chronosystems.entity.enumeration.TipoOperacao;
import com.chronosystems.filter.WebFilter;
import com.chronosystems.geocoder.GeocoderHelper;
import com.chronosystems.utils.JsoupUtils;
import com.google.code.geocoder.model.GeocoderGeometry;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;

/**
 * @author André Valadas
 */
public class JsoupWebScanner {

	final List<Imovel> result = new ArrayList<>();

	/**
	 * Retorna os imoveis processados
	 * 
	 * @return
	 */
	public List<Imovel> getResult() {
		return result;
	}

	/**
	 * URL princiapl a ser processada
	 * 
	 * @return the URL to connect
	 */
	protected Map<Estado, List<WebFilter>> getURLConnect() {

		/** RS */
		final List<WebFilter> buscasRS = new ArrayList<>();

		/** COMPRA */
		buscasRS.add(new WebFilter(TipoOperacao.CO, TipoImovel.CA, "http://www.penseimoveis.com.br/rs/lista/compra/rs/?pais=Brasil&Tipo+de+im%F3vel=Casa&avancada=true"));
		//buscasRS.add(new WebFilter(TipoOperacao.CO, TipoImovel.CA, "http://www.penseimoveis.com.br/rs/lista/compra/rs/casa"));
		//buscasRS.add(new WebFilter(TipoOperacao.CO, TipoImovel.AP, "http://www.penseimoveis.com.br/rs/busca/compra/sc?pais=Brasil&avancada=true&Tipo%20de%20im%F3vel=Apartamento"));
		//buscasRS.add(new WebFilter(TipoOperacao.CO, TipoImovel.AP, "http://www.penseimoveis.com.br/rs/lista/compra/rs/apartamento"));
		//buscasRS.add(new WebFilter(TipoOperacao.CO, TipoImovel.CO, "http://www.penseimoveis.com.br/rs/lista/compra/rs/comercial"));
		//buscasRS.add(new WebFilter(TipoOperacao.CO, TipoImovel.TE, "http://www.penseimoveis.com.br/rs/lista/compra/rs/terreno"));

		/** ALUGUEL */
		/** http://www.penseimoveis.com.br/rs/lista/aluguel/rs/?pais=Brasil&Tipo+de+im%F3vel=Apartamento&avancada=true */
		//buscasRS.add(new WebFilter(TipoOperacao.AL, TipoImovel.CA, "http://www.penseimoveis.com.br/rs/lista/aluguel/rs/casa"));
		//buscasRS.add(new WebFilter(TipoOperacao.AL, TipoImovel.AP, "http://www.penseimoveis.com.br/rs/lista/aluguel/rs/apartamento"));
		//buscasRS.add(new WebFilter(TipoOperacao.AL, TipoImovel.CO, "http://www.penseimoveis.com.br/rs/lista/aluguel/rs/comercial"));
		//buscasRS.add(new WebFilter(TipoOperacao.AL, TipoImovel.TE, "http://www.penseimoveis.com.br/rs/lista/aluguel/rs/terreno"));

		/** SC */
		final List<WebFilter> buscasSC = new ArrayList<>();
		buscasSC.add(new WebFilter(TipoOperacao.CO, TipoImovel.CA, "http://www.penseimoveis.com.br/sc/lista/compra/sc/casa"));
		buscasSC.add(new WebFilter(TipoOperacao.CO, TipoImovel.AP, "http://www.penseimoveis.com.br/sc/lista/compra/sc/apartamento"));
		buscasSC.add(new WebFilter(TipoOperacao.CO, TipoImovel.CO, "http://www.penseimoveis.com.br/sc/lista/compra/sc/comercial"));
		buscasSC.add(new WebFilter(TipoOperacao.CO, TipoImovel.TE, "http://www.penseimoveis.com.br/sc/lista/compra/sc/terreno"));

		final Map<Estado, List<WebFilter>> searchProperties = new HashMap<>();
		searchProperties.put(Estado.RS, buscasRS); // Adiciona RS
		//searchProperties.put(Estado.SC, buscasSC); // Adiciona SC

		return searchProperties;
	}

	/**
	 * @return the site to search
	 */
	protected SiteBusca getSiteBusca() {
		return SiteBusca.PENSE_IMOVEIS;
	}
	
	/**
	 * Total de registros para processar
	 * 
	 * @param doc
	 * @return
	 */
	protected Integer getTotalPages(final Document doc) {
		return Integer.valueOf(doc.getElementById("totalDePaginas").text());
	}

	/**
	 * Elementos da pagina
	 * 
	 * @param doc
	 * @return
	 */
	protected Elements getPageElements(final Document doc) {
		return doc.select(".boxAnuncioLista");
	}

	/**
	 * Valida se elemento e valido para processar
	 * 
	 * @param element
	 * @return
	 */
	protected boolean validateElementToProcess(final Element element) {
		final Elements valorElement = element.select(".anuncioListaValoresValor");
		return valorElement != null && !valorElement.isEmpty();
	}

	/**
	 * @return the url
	 */
	protected String getUrlText(final Element doc) {
		final Element content = doc.select(".anuncioListaBoxImagem").first();
		final Elements links = content.getElementsByTag("a");
		if (!links.isEmpty()) {
			return links.first().absUrl("href");
		}
		return null;
	}

	/**
	 * @return the codigo
	 */
	protected String getCodigoText(final Element doc) {
		final Element content = doc.select(".anuncioListaInfoTitulo").first();
		final Elements codigo = content.getElementsByTag("a");
		if (!codigo.isEmpty()) {
			final String absUrl = codigo.first().absUrl("href");
			return absUrl.substring(absUrl.lastIndexOf("/") + 1);
		}
		return null;
	}

	/**
	 * @return the titulo
	 */
	protected String getTituloText(final Element doc) {
		final Element content = doc.select(".anuncioListaInfoTitulo").first();
		final Elements title = content.getElementsByTag("a");
		if (!title.isEmpty()) {
			return title.attr("title").trim();
		}
		return null;
	}

	/**
	 * @return the caracteristica
	 */
	protected String getCaracteristicaText(final Element doc) {
		final Element content = doc.select(".anuncioListaInfoCaracteristicas").first();
		final Elements caracteristica = content.getElementsByTag("strong");
		if (!caracteristica.isEmpty()) {
			return caracteristica.text().trim();
		}
		return null;
	}

	/**
	 * @return the descricao
	 */
	protected String getDescricaoText(final Element doc) {
		final Element content = doc.select(".anuncioListaInfoDescricao").first();
		final Elements descricao = content.getElementsByTag("a");
		if (!descricao.isEmpty()) {
			return descricao.text().trim();
		}
		return null;
	}

	/**
	 * @return the urlImagem
	 */
	protected String getUrlImagemText(final Element doc) {
		final Elements imagem = doc.select(".anuncioListaImagem").select("img[src]");
		if (!imagem.isEmpty()) {
			final String absUrl = imagem.first().absUrl("src");
			if (absUrl.indexOf("?") > -1) {
				return absUrl.substring(0, absUrl.lastIndexOf("?"));
			}
			return absUrl;
		}
		return null;
	}

	/**
	 * @return the anunciante
	 */
	protected String getAnuncianteText(final Element doc) {
		final Element anunciante = doc.select(".dadosAnunciante .nomeAnunciante").first();
		if (anunciante != null) {
			return anunciante.text().trim();
		}
		return null;
	}

	/**
	 * @return telefone
	 */
	protected String getTelefoneText(final Element doc) {
		final Element telefoneTag = doc.select(".informacoesTelefone .titlePhoneCustomer").first().parent();
		final Elements codigos = telefoneTag.select(".subTitlePrePhone");
		final String ddi = codigos.first().text();
		final String ddd = codigos.get(1).text();
		final String numero = telefoneTag.select(".subTitlePhoneCustomer").first().text();

		return ddi+ddd+numero;
	}
	
	/**
	 * @return the valor
	 */
	protected String getValorText(final Element doc) {
		final Element valorElement = doc.select(".valorImovelConteudo .preco").first();
		if (valorElement != null) {
			return valorElement.text().trim();
		}
		return null;
	}

	/**
	 * Adiciona Imovel processado a lista
	 * 
	 * @param data
	 */
	protected void addElementProcessed(final Imovel data) {
		result.add(data);
		debug(data.getCodigoAnuncio() + " adicionado. rowCount: "+result.size());
	}

	/**
	 * Execute parse html extractor
	 */
	public List<Imovel> execute() {
		/** ThreadPool */
		final ExecutorService executor = Executors.newFixedThreadPool(30);

		try {

			/** properiedades da busca */
			final Set<Entry<Estado, List<WebFilter>>> searchProperties = getURLConnect().entrySet();
			for (final Entry<Estado, List<WebFilter>> search : searchProperties) {

				/** estado */
				final Estado estado = search.getKey();

				/** urls da consulta */
				final List<WebFilter> urls = search.getValue();
				for (final WebFilter filter : urls) {

					/** url da listagem paginada */
					Document doc = Jsoup.parse(new URL(filter.getUrl()).openStream(), "ISO-8859-1", filter.getUrl());
					debug(estado.getDescription());
					debug(filter.getUrl());

					/** total de paginas validas para iterar */
					final Integer totalPages = getTotalPages(doc);
					debug(totalPages.toString());

					info("total de paginas: "+totalPages);

					/** busca em todas as paginas da consulta */
					for (int i = 1; i < totalPages; i++) {
						
						info("total processados: "+result.size());
						info("==============================================================================");
						info("pagina: "+i);
						info("==============================================================================");
						/** carrega proxima pagina */
						if (i > 1) {
							doc = Jsoup.parse(new URL(filter.getUrl()+"?page="+i).openStream(), "ISO-8859-1", filter.getUrl()+"?page="+i);
							break;
						}

						/** elements to iterate */
						final Elements anuncios = getPageElements(doc);
						for (final Element element : anuncios) {
							executor.execute(new Runnable() {
								public void run() {

									/** validate data element */
									if (validateElementToProcess(element)) {

										/** Imovel */
										final Imovel imovel = new Imovel();
										imovel.setEstado(estado);
										imovel.setSiteBusca(getSiteBusca());

										/** tipo operacao */
										final TipoOperacao tipoOperacao = filter.getTipoOperacao();
										imovel.setTipoOperacao(tipoOperacao);
										debug(tipoOperacao.getDescription());

										/** tipo imovel */
										final TipoImovel tipoImovel = filter.getTipoImovel();
										imovel.setTipoImovel(tipoImovel);
										debug(tipoImovel.getDescription());

										/* processa resumo */
										processSummary(imovel, element);

										/* processa detalhamento */
										processDetail(imovel);

										/* add sucessfull element processed */
										addElementProcessed(imovel);
									}
								}
							});
						}
					}
				}
			}
		} catch (Exception e) {
			error(e.getMessage());
			e.printStackTrace();
		} finally {
			executor.shutdown();
			/* waiting for all tasks to complete */
			while(!executor.isTerminated()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) { }
			}
		}

		return getResult();
	}

	/**
	 * Processa resumo do anuncio
	 * 
	 * @param imovel
	 */
	private void processSummary(final Imovel imovel, final Element element) {
		/** url anuncio */
		final String urlText = getUrlText(element);
		imovel.setUrlAnuncio(urlText);
		debug(urlText);

		/** codigo */
		final String codigoText = getCodigoText(element);
		imovel.setCodigoAnuncio(codigoText);
		debug(codigoText);

		/** titulo */
		final String tituloText = getTituloText(element);
		imovel.setTituloResumo(tituloText);
		debug(tituloText);

		/** caracteristica */
		final String caracteristicaText = getCaracteristicaText(element);
		imovel.setCaracteristicasResumo(caracteristicaText);
		debug(caracteristicaText);

		/** descricao */
		final String descriptionText = getDescricaoText(element);
		imovel.setDescricaoResumo(descriptionText);
		debug(descriptionText);

		/** url image */
		final String urlImagemText = getUrlImagemText(element);
		imovel.setImgDestaque(urlImagemText);
		debug(urlImagemText);
	}

	/**
	 * Processa detalhamento do anuncio
	 * 
	 * @param imovel
	 */
	private void processDetail(final Imovel imovel) {
		try {
			/** acessa pagina de detalhamento */
			final Document doc = Jsoup.parse(new URL(imovel.getUrlAnuncio()).openStream(), "ISO-8859-1", imovel.getUrlAnuncio());

			/** anunciante */
			final String anuncianteText = getAnuncianteText(doc);
			imovel.setAnunciante(anuncianteText);
			debug(anuncianteText);

			/** telefone */
			final String telefoneText = getTelefoneText(doc);
			imovel.setTelefone(telefoneText);
			debug(telefoneText);

			/* processa caracteristicas */
			processCarateristicas(imovel, doc);

			/* processa localizacao */
			processLocalizacao(imovel, doc);

			/* processa imagens */
			processImagens(imovel, doc);

			/** valor */
			final String valorText = getValorText(doc);
			imovel.setValor(JsoupUtils.stringToBigDecimal(valorText));
			debug(valorText);

		} catch (Exception e) {
			error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Processa as caracteristicas 
	 * 
	 * @param imovel
	 * @param doc
	 */
	private void processCarateristicas(final Imovel imovel, final Document doc) {
		final Elements dormitorios = doc.select("#linkDetalhesImovel .detalhes li");
		for (final Element element : dormitorios) {
			final String property = element.select(".coluna1").text().trim();
			final String value = element.select(".coluna2").text().trim();
			if (property.startsWith("Dormitórios")) {
				imovel.setDormitorios(JsoupUtils.stringToInteger(value));
				debug(value);
			} else if (property.startsWith("Vagas de garagem")) {
				imovel.setBoxGaragem(JsoupUtils.stringToInteger(value));
				debug(value);
			} else if (property.startsWith("Área total")) {
				imovel.setAreaTotal(JsoupUtils.stringToBigDecimal(value));
				debug(value);
			} else if (property.startsWith("Área privativa")) {
				imovel.setAreaPrivativa(JsoupUtils.stringToBigDecimal(value));
				debug(value);
			} else if (property.startsWith("Imóvel")) {
				final CategoriaImovel categoriaImovel = CategoriaImovel.getEnum(value);
				imovel.setCategoriaImovel(categoriaImovel);
				debug(categoriaImovel.getDescription());
			}
		}
	}

	/**
	 * Processa localizacao do imovel
	 * 
	 * @param imovel
	 * @param doc
	 */
	private synchronized void processLocalizacao(final Imovel imovel, final Document doc) {
		processLocalizacao(imovel, doc, 0);
	}
	
	/**
	 * Processa localizacao do imovel
	 * 
	 * @param imovel
	 * @param doc
	 */
	private synchronized void processLocalizacao(final Imovel imovel, final Document doc, int count) {
		final String latitude = JsoupUtils.getScriptPropertyValue(doc, "latitude").trim();
		final String longitude = JsoupUtils.getScriptPropertyValue(doc, "longitude").trim();
		final String endereco = JsoupUtils.getScriptPropertyValue(doc, "enderecoAlternativo").trim();

		GeocoderHelper.configureDefaultLocation(imovel);

		/* localizacao exata */
		if (!latitude.isEmpty()) {
			imovel.setTipoLocalizacao(TipoLocalizacao.E); //LOCALIZACAO EXATA

			final LatLng location = new LatLng(new BigDecimal(latitude), new BigDecimal(longitude));
			GeocoderHelper.configureLocation(imovel, location);
		} else {
			imovel.setTipoLocalizacao(TipoLocalizacao.A); //LOCALIZACAO APROXIMADA

			/* localizacao aproximada */
			final GeocoderResult geocoderResult = GeocoderHelper.getGeocoderResult(endereco);
			if (geocoderResult != null) {
				final GeocoderGeometry geometry = geocoderResult.getGeometry();
				final LatLng location = geometry.getLocation();
				GeocoderHelper.configureLocation(imovel, location);
			} else {
				/*error("localização não encontrada pelo endereço.");
				if (count < 3) {
					debug("realizando nova pesquisa no google maps");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) { }
					processLocalizacao(imovel, doc, ++count);
				}*/
			}
			imovel.setEndereco(endereco);
		}

		debug(imovel.getLatitude().toString());
		debug(imovel.getLongitude().toString());
		debug(imovel.getEndereco());
	}

	/**
	 * Processa as imagens do anuncio
	 * 
	 * @param imovel
	 * @param doc
	 */
	private void processImagens(final Imovel imovel, final Document doc) {
		final Elements imagens = doc.select(".imagemBoxRodapeThumb").select("img[src]");
		final List<ImagemImovel> imagensImoveis = new ArrayList<>();

		for (final Element imagem : imagens) {
			final String descricao = imagem.attr("title").trim();
			final String absUrl = imagem.absUrl("alt");

			final ImagemImovel imagemImovel = new ImagemImovel();
			imagemImovel.setUrl(absUrl);
			if (!descricao.isEmpty()) {
				imagemImovel.setDescricao(descricao);
			}
			imagemImovel.setImovel(imovel);

			/* adiciona url da imagem */
			imagensImoveis.add(imagemImovel);

			debug(absUrl);
			debug(descricao);
		}

		/** atualiza dados do imovel */
		imovel.setTotalImagens(Integer.valueOf(imagensImoveis.size()));
		imovel.setImagens(new HashSet<>(imagensImoveis));
	}

	/**
	 * Registra informacoes do processo
	 * 
	 * @param info
	 */
	private void info(final String info) {
		System.out.println("| info: " + info);
	}
	
	/**
	 * Registra informacoes do processo
	 * 
	 * @param debug
	 */
	private void debug(final String debug) {
		if (!debug.isEmpty()) {
			System.out.println("| debug: " + debug);
		}
	}

	/**
	 * Registra erros do processo
	 * 
	 * @param error
	 */
	private void error(final String error) {
		System.out.println("| ERROR: " + error);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new JsoupWebScanner().execute();
	}
}