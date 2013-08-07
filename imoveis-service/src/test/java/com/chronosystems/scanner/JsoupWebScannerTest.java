/**
 * 
 */
package com.chronosystems.scanner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chronosystems.entity.Imovel;
import com.chronosystems.service.ImovelService;

/**
 * @author Andre Valadas
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:**/WEB-INF/applicationContext*.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JsoupWebScannerTest {

	private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Autowired
	private ImovelService imovelService;

	@Test
	public void testExecuteScanner() {
		info("JsoupWebScannerTest.testExecuteScanner()");

		final ExecutorService executor = Executors.newFixedThreadPool(10);

		final List<Imovel> result = new JsoupWebScanner().execute();
		info("total result: "+result.size());
		for (final Imovel imovel : result) {

			/** invoke async execute */
			executor.execute(new Runnable() {
				public void run() {

					/** valida se tem localizacao */
					if (!validateImovel(imovel)) {
						return;
					}

					/** verifica se existe */
					final Imovel entity = imovelService.findByCodigoAnuncio(imovel.getCodigoAnuncio());
					if (entity != null) {
						imovel.setId(entity.getId());
						BeanUtils.copyProperties(imovel, entity, new String[] {"id", "imagens", "dataInclusao", "ativo" });
						BeanUtils.copyProperties(imovel.getImagens(), entity.getImagens(), new String[] { "id" });
						entity.setDataAlteracao(Calendar.getInstance().getTime());
						imovelService.save(entity);
					} else {
						imovel.setDataInclusao(Calendar.getInstance().getTime());
						imovelService.save(imovel);
					}
				}
			});
		}

		/* waiting for all tasks to complete */
		executor.shutdown();
		while(!executor.isTerminated()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { }
		}

		info("finished");
	}
	private boolean validateImovel(final Imovel imovel) {
		return imovel.getLatitude() != null && imovel.getLongitude() != null;
	}
	
	@Test
	public void testFindAll() {
		final List<Imovel> findAll = imovelService.findAll();
		for (final Imovel imovel : findAll) {
			System.out.println(imovel.getEndereco());
			//System.out.println(imovel.getId() + "|" + imovel.getCodigoAnuncio() + "|" + imovel.getLatitude() + "|" + imovel.getLongitude() + "|" + imovel.getValor() + "|imagens: " + imovel.getImagens().size());
		}
		Assert.assertTrue("Imovel can't be null ", findAll.size() > 0);
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