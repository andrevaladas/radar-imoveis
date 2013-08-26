package com.chronosystems.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chronosystems.dto.ImovelDTO;
import com.chronosystems.dto.ListImovelDTO;
import com.chronosystems.entity.Imovel;
import com.chronosystems.service.ImovelService;

@Controller
@RequestMapping("/imoveis")
public class WebController {

	@Autowired
	private ImovelService imovelService;
	
	@RequestMapping("/index")
	public String index(final ModelMap model) {
		final List<ImovelDTO> imoveisDTO = new ArrayList<>(); 
		final List<Imovel> imoveis = imovelService.findAll();
		for (final Imovel imovel : imoveis) {
			imoveisDTO.add(new ImovelDTO(imovel));
		}
		model.addAttribute("imovelList", imoveisDTO);
		return "index";
	}

	@RequestMapping("/index2")
	public String index2(final ModelMap model) {
		final ListImovelDTO listImovelDTO = new ListImovelDTO();

		final List<Imovel> imoveis = imovelService.findAll();
		for (final Imovel imovel : imoveis) {
			listImovelDTO.addImovel(new ImovelDTO(imovel));
		}
		model.addAttribute("imovelList", listImovelDTO);
		return "index2";
	}

	@RequestMapping("/{id}")
	public String imoveis(@PathVariable("id") final Long id, final ModelMap model) {
		final Imovel imovel = imovelService.findById(id);
		model.addAttribute("imovelList", Arrays.asList(new ImovelDTO(imovel)));
		return "poc";
	}

	@RequestMapping(value="/json/data/{id}", method = RequestMethod.GET)
	public @ResponseBody ImovelDTO getImovelData(@PathVariable Long id) {
		return new ImovelDTO(imovelService.findById(id));
	}

	@RequestMapping(value="/json/list", method = RequestMethod.GET)
	public @ResponseBody List<ImovelDTO> listJSON() {
 
		final List<ImovelDTO> imoveisDTO = new ArrayList<>(); 
		final List<Imovel> imoveis = imovelService.findAll();
		for (final Imovel imovel : imoveis) {
			imoveisDTO.add(new ImovelDTO(imovel));
		}
		
		return imoveisDTO;
	}

	@RequestMapping("/query")
	public @ResponseBody List<ImovelDTO> query(
		@RequestParam(value = "estado", required = true) String estado,
		@RequestParam(value = "cidade", required = false) Long cidade,
		@RequestParam(value = "bairro", required = false) Long bairro,

		@RequestParam(value = "site-busca", required = false) String siteBusca,
		@RequestParam(value = "tipo-operacao", required = false) String tipoOperacao,
		@RequestParam(value = "tipo-imovel", required = false) String tipoImovel,
		@RequestParam(value = "categoria-imovel", required = false) String categoriaImovel,
		@RequestParam(value = "tipo-localizacao", required = false) String tipoLocalizacao
	) {
		
		final StringBuilder query = new StringBuilder();
		query.append("select i ");
		query.append("from Imovel i ");
		query.append("where i.estado = '").append(estado.toUpperCase()).append("' ");
 
		/* filters */
		applyFilter(query, "cidade.id", cidade);
		applyFilter(query, "bairro.id", bairro);

		applyFilter(query, "siteBusca", siteBusca);
		applyFilter(query, "tipoOperacao", tipoOperacao);
		applyFilter(query, "tipoImovel", tipoImovel);
		applyFilter(query, "categoriaImovel", categoriaImovel);
		applyFilter(query, "tipoLocalizacao", tipoLocalizacao);

		final List<ImovelDTO> imoveisDTO = new ArrayList<>(); 
		final List<Imovel> imoveis = imovelService.findByQuery(query.toString());
		for (final Imovel imovel : imoveis) {
			imoveisDTO.add(new ImovelDTO(imovel));
		}

		return imoveisDTO;
	}

	private void applyFilter(final StringBuilder query, final String field, Object value) {
		if (value != null && !value.equals("*")) {
			if (value instanceof String) {
				value = "'"+value+"'";
			}
			query.append(" and i.").append(field).append(" = ").append(value).append(" ");
		}
	}
}