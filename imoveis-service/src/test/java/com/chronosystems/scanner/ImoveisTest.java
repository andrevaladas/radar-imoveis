/**
 * 
 */
package com.chronosystems.scanner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chronosystems.entity.Bairro;
import com.chronosystems.entity.Imovel;
import com.chronosystems.service.BairroService;
import com.chronosystems.service.CidadeService;
import com.chronosystems.service.ImovelService;
import com.imoveis.selenium.maps.search.AddressFinder;

/**
 * @author Andre Valadas
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:**/WEB-INF/applicationContext*.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImoveisTest {

	private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Autowired
	private ImovelService imovelService;

	@Autowired
	private CidadeService cidadeService;

	@Autowired
	private BairroService bairroService;

	@Test
	public void testFindAll() {
		final List<Imovel> imoveis = imovelService.findAll();
		for (final Imovel imovel : imoveis) {
			info(imovel.getEndereco());
			AddressFinder.getInstance().configureLocation(imovel);
			info(imovel.getLatitude().toString());
			info(imovel.getLongitude().toString());
			info(imovel.getEndereco());
			imovelService.save(imovel);
			info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

			//System.out.println(imovel.getId() + "|" + imovel.getCodigoAnuncio() + "|" + imovel.getLatitude() + "|" + imovel.getLongitude() + "|" + imovel.getValor() + "|imagens: " + imovel.getImagens().size());
		}
		AddressFinder.getInstance().release();
		Assert.assertTrue("Imovel can't be null ", imoveis.size() > 0);
	}

	@Test
	public void testFindBairros() {
		final List<Bairro> bairros = bairroService.findAll();
		for (final Bairro bairro : bairros) {
			info(bairro.getId() +"|"+ bairro.getDescricao() +"|"+ bairro.getCidade().getDescricao() +"|"+ bairro.getCidade().getEstado().getDescription());
		}
		Assert.assertTrue("Imovel can't be null ", bairros.size() > 0);
	}
	
	@Ignore
	@Test
	public void testFindByQuery() {

		final StringBuilder query = new StringBuilder();
		query.append("select count(i) ");
		query.append("from Imovel i ");
		//query.append("where i.tipoLocalizacao = 'A'");
		query.append("group by i.latitude, i.longitude ");
		//query.append("having count(i) > 4 ");

		final List<Imovel> findAll = imovelService.findByQuery(query.toString());
		for (final Imovel imovel : findAll) {
			System.out.println(imovel.getId() + "|" + imovel.getCodigoAnuncio() + "|" + imovel.getLatitude() + "|" + imovel.getLongitude() + "|" + imovel.getValor() + "|imagens: " + imovel.getImagens().size());
		}
		Assert.assertTrue("Imovel can't be null ", findAll.size() > 0);
	}

	private void info(final String info) {
		System.out.println(dateFormat.format(new Date()) + "| test: " + info);
	}
}