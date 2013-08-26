/**
 * 
 */
package com.chronosystems.scanner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chronosystems.entity.Bairro;
import com.chronosystems.entity.Cidade;
import com.chronosystems.entity.Imovel;
import com.chronosystems.service.BairroService;
import com.chronosystems.service.CidadeService;
import com.chronosystems.service.ImovelService;

/**
 * @author Andre Valadas
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:**/WEB-INF/applicationContext*.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JsoupWebScannerTest {

	private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private ImovelService imovelService;

	@Autowired
	private CidadeService cidadeService;

	@Autowired
	private BairroService bairroService;

	@Test
	public void testExecuteScanner() {
		info("JsoupWebScannerTest.testExecuteScanner()");
		final List<Imovel> result = new JsoupWebScanner().execute();

		//final ExecutorService executor = Executors.newFixedThreadPool(10);
		info("total result: "+result.size());
		for (final Imovel imovel : result) {

			/** invoke async execute */
			//executor.execute(new Runnable() {
				//public void run() {

					/** valida se tem localizacao */
					if (!validateImovel(imovel)) {
						System.err.println("INVALID RECORD");
						return;
					}

					/** carrega localização */
					final Cidade cidade = cidadeService.find(imovel.getEstado(), imovel.getCidade().getDescricao());
					if (cidade == null) {
						cidadeService.save(imovel.getCidade());
					} else {
						imovel.setCidade(cidade);
					}

					if (imovel.getBairro() != null) {
						final Bairro bairro = bairroService.find(imovel.getCidade(), imovel.getBairro().getDescricao());
						if (bairro == null) {
							imovel.getBairro().setCidade(imovel.getCidade());
							bairroService.save(imovel.getBairro());
						} else {
							imovel.setBairro(bairro);
						}
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
				//}
			//});
		}

		/* waiting for all tasks to complete */
		/*executor.shutdown();
		while(!executor.isTerminated()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { }
		}*/

		info("finished: "+result.size());
	}
	private boolean validateImovel(final Imovel imovel) {
		return imovel.getLatitude() != null && imovel.getLongitude() != null;
	}
	
	private void info(final String info) {
		System.out.println(dateFormat.format(new Date()) + "| test: " + info);
	}
}