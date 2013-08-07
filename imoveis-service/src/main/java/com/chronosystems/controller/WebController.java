package com.chronosystems.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping("/{estado}/{tipoImovel}/{categoriaImovel}/{tipoLocalizacao}/{limit}")
	public String query(
			@PathVariable("estado") final String estado, 
			@PathVariable("tipoImovel") final String tipoImovel, 
			@PathVariable("categoriaImovel") final String categoriaImovel, 
			@PathVariable("tipoLocalizacao") final String tipoLocalizacao, 
			@PathVariable("limit") final int limit, 
			final ModelMap model) {

		final StringBuilder query = new StringBuilder();
		query.append("select i ");
		query.append("from Imovel i ");
		query.append("where i.estado = '").append(estado.toUpperCase()).append("' ");
 
		/* filters */
		applyFilter(query, "tipoImovel", tipoImovel);
		applyFilter(query, "categoriaImovel", categoriaImovel);
		applyFilter(query, "tipoLocalizacao", tipoLocalizacao);

		final List<ImovelDTO> imoveisDTO = new ArrayList<>(); 
		final List<Imovel> imoveis = imovelService.findByQuery(query.toString());
		for (final Imovel imovel : imoveis) {
			imoveisDTO.add(new ImovelDTO(imovel));
		}

		model.addAttribute("imovelList", imoveisDTO);
		return "index";
	}
	
	private void applyFilter(final StringBuilder query, final String field, final String value) {
		if (value != null && !value.equals("*")) {
			query.append(" and i.").append(field).append(" = '").append(value.toUpperCase()).append("' ");
		}
	}
}