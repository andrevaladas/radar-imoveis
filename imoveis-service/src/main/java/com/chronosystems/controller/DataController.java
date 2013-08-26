package com.chronosystems.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chronosystems.entity.Bairro;
import com.chronosystems.entity.Cidade;
import com.chronosystems.entity.enumeration.CategoriaImovel;
import com.chronosystems.entity.enumeration.Estado;
import com.chronosystems.entity.enumeration.SimNao;
import com.chronosystems.entity.enumeration.SiteBusca;
import com.chronosystems.entity.enumeration.TipoImovel;
import com.chronosystems.entity.enumeration.TipoLocalizacao;
import com.chronosystems.entity.enumeration.TipoOperacao;
import com.chronosystems.entity.enumeration.util.EnumUtils;
import com.chronosystems.service.BairroService;
import com.chronosystems.service.CidadeService;

@Controller
public class DataController {

	@Autowired
	private CidadeService cidadeService;

	@Autowired
	private BairroService bairroService;

	/** Enums */
	@RequestMapping(value="/get/sites-busca", method = RequestMethod.GET)
	public @ResponseBody Map<?, ?> getSitesBusca() {
		return EnumUtils.toMap(SiteBusca.class);
	}
	@RequestMapping(value="/get/tipos-operacoes", method = RequestMethod.GET)
	public @ResponseBody Map<?, ?> getTiposOperacoes() {
		return EnumUtils.toMap(TipoOperacao.class);
	}
	@RequestMapping(value="/get/tipos-imoveis", method = RequestMethod.GET)
	public @ResponseBody Map<?, ?> getTiposImoveis() {
		return EnumUtils.toMap(TipoImovel.class);
	}
	@RequestMapping(value="/get/categorias-imoveis", method = RequestMethod.GET)
	public @ResponseBody Map<?, ?> getCategorias() {
		return EnumUtils.toMap(CategoriaImovel.class);
	}
	@RequestMapping(value="/get/tipos-localizacao", method = RequestMethod.GET)
	public @ResponseBody Map<?, ?> getTiposLocalizacao() {
		return EnumUtils.toMap(TipoLocalizacao.class);
	}

	@RequestMapping(value="/get/sim-nao", method = RequestMethod.GET)
	public @ResponseBody Map<?, ?> getSimNao() {
		return EnumUtils.toMap(SimNao.class);
	}

	/**
	 * Estados
	 * @return
	 */
	@RequestMapping(value="/get/estados", method = RequestMethod.GET)
	public @ResponseBody Map<?, ?> getEstados() {
		return EnumUtils.toMap(Estado.class);
	}

	/**
	 * Cidades 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/get/cidades", method = RequestMethod.GET)
	public @ResponseBody Map<?, ?> getCidades(@RequestParam(value = "estado", required = true) String estado) {
		if (!StringUtils.isEmpty(estado)) {
			final Estado enumEstado = EnumUtils.findByValue(Estado.class, estado);
			final List<Cidade> cidades = cidadeService.findByEstado(enumEstado);
			if (!cidades.isEmpty()) {

				final Map<Long, String> data = new HashMap<>();
				for (final Cidade cidade : cidades) {
					data.put(cidade.getId(), cidade.getDescricao());
				}
				return data;
			}
		}
		return null;
	}

	/**
	 * Bairros
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/get/bairros", method = RequestMethod.GET)
	public @ResponseBody Map<?, ?> getBairros(@RequestParam(value = "cidade", required = true) Long idCidade) {
		if (idCidade != null) {
			final Cidade cidade = new Cidade();
			cidade.setId(Long.valueOf(idCidade));

			final List<Bairro> bairros = bairroService.findByCidade(cidade);
			if (!bairros.isEmpty()) {

				final Map<Long, String> data = new HashMap<>();
				for (final Bairro bairro : bairros) {
					data.put(bairro.getId(), bairro.getDescricao());
				}
				return data;
			}
		}
		return null;
	}
}