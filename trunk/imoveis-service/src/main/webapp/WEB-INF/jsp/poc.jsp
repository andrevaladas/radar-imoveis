<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
    <title>POC - Radar Im&oacute;veis</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Mapa de Im&oacute;veis">
    <meta name="author" content="Chrono Systems">

    <script src="/js/jquery.js" type="text/javascript"></script>

    <script src="/js/classes/imoveis.js" type="text/javascript"></script>
    <script type="text/javascript">
    	var map;
    	var $imoveisList = [];

    	$(document).ready(function(){
    		/** carrega dados da consulta */
    		loadDataImoveis();

    		/** mostra dados */
			printData();
    	});

    	/** popula dados dos imoveis filtrados */
    	function loadDataImoveis() {
			<c:forEach items="${imovelList}" var="data" varStatus="status">
				// popula objeto imovel
				var $imovel = new Imovel();

				//@base
				$imovel.setId('${data.id}');
				$imovel.setEstado('${data.estado}', '${data.estado.description}');
				$imovel.setSiteBusca('${data.siteBusca}', '${data.siteBusca.description}');
				$imovel.setTipoOperacao('${data.tipoOperacao}', '${data.tipoOperacao.description}');
				$imovel.setTipoImovel('${data.tipoImovel}', '${data.tipoImovel.description}');
				$imovel.setCategoriaImovel('${data.categoriaImovel}', '${data.categoriaImovel.description}');
				//@end

				//@resumo
				$imovel.setUrlAnuncio('${data.urlAnuncio}');
				$imovel.setCodigoAnuncio('${data.codigoAnuncio}');
				$imovel.setTituloResumo('${data.tituloResumo}');
				$imovel.setCaracteristicasResumo('${data.caracteristicasResumo}');
				$imovel.setDescricaoResumo('${data.descricaoResumo}');
				$imovel.setImgDestaque('${data.imgDestaque}');
				$imovel.setTotalImagens('${data.totalImagens}');
				//@end

				//@detalhamento
				$imovel.setAnunciante('${data.anunciante}');
				$imovel.setTelefone('${data.telefone}');
				$imovel.setDormitorios('${data.dormitorios}');
				$imovel.setBoxGaragem('${data.boxGaragem}');
				$imovel.setAreaTotal('${data.areaTotalFormatted}');
				$imovel.setAreaPrivativa('${data.areaPrivativaFormatted}');
				$imovel.setValor('${data.valorFormatted}');
				//@end

				//@localizacao
				$imovel.getLocalizacao().setEndereco('${data.endereco}');
				$imovel.getLocalizacao().setLatitude('${data.latitude}');
				$imovel.getLocalizacao().setLongitude('${data.longitude}');
				$imovel.getLocalizacao().setTipoLocalizacao('${data.tipoLocalizacao}', '${data.tipoLocalizacao.description}');
				//@end

				//add imovel
				$imoveisList.push($imovel);
			</c:forEach>
    	}

    	/** carrega dados */
    	function printData() {
			for (var i = 0; i < $imoveisList.length; i++) {
				var $imovel = $imoveisList[i];
				/** show data */
				showData($imovel);
			}
    	}

    	/** show data */
    	function showData(imovel) {
		    document.write(imovel.mostraValores());
		}
	</script>
  </head>

  <body>
  </body>
</html>
