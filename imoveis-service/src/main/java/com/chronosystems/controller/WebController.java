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
}