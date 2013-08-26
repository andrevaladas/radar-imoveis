<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
    <title>Radar Im&oacute;veis</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Mapa de Im&oacute;veis">
    <meta name="author" content="Chrono Systems">

    <link href="https://developers.google.com/maps/documentation/javascript/examples/default.css" rel="stylesheet">
    <style>
		#rightSide
		{
		     height: 500px;
		     width: 200px;
		     background: black;
		     float: right;
		     display: none;    
		     color: white;
		     font-size: xx-large;
		}
		#click
		{
		     height: 25px;
		     width: 25px;
		     background: aqua;
		     float: right;
		     margin-top: 20px;
		}
    </style>

    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=drawing"></script>
	<!--script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js" language="javascript" type="text/javascript"></script-->
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>

	<script type="text/javascript" src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobubble/src/infobubble.js"></script>
	<script type="text/javascript" src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/markerclusterer/src/markerclusterer.js"></script> 

    <script src="/js/classes/imoveis.js" type="text/javascript" charset="UTF-8"></script>
    <script src="/js/component/slider-filter.js" type="text/javascript" charset="UTF-8"></script>
    <link href="/css/component/slider-filter.css" rel="stylesheet" charset="UTF-8">
    <link href="/css/loader/loader.css" rel="stylesheet" charset="UTF-8">

    <script type="text/javascript">
    	var map;
    	var markerCluster;
    	var $allMarkers = [];
    	var $imoveisList = [];
    	var $clusterMap = {};
    	var $fullBounds = new google.maps.LatLngBounds();

    	jQuery.ajaxSetup({
   		  	beforeSend: function() {
   		    	$('#loader').show();
   		  	},
   		  	complete: function(){
   		    	$('#loader').fadeOut(1000);
   		  	},
   		  	success: function() {}
   		});

    	$(document).ready(function(){
    		/** carrega dados da consulta */
    		loadDataImoveis();

    		/** inicializa mapa */
    		initialize();

    		/** adiciona marcadores no mapa */
			printMarkers();

    		/** adiciona controle de cluster */
			//applyMarkerCluster();
    		
			$('#click').click(function()
			{
			    $("#rightSide").animate({width:'toggle'},500);       
			});
    	});

    	function filterLocation() {
    		var $filterData = '';
    		$('#slider-filter option:selected').each(function(){
    			if ($(this).val()) {
	    			if ($filterData) {
	    				$filterData += '&';
	    			}
	    			$filterData += $(this).parent().attr('id') +'='+ $(this).val();
    			}
    		});

    		$.ajax({
    			type: "GET",
    			url: "${pageContext.request.contextPath}/imoveis/query",
    			cache: false,
    			async: true,
    			contentType: "application/json",
    			data: $filterData, 
    			success: function(data){
    				/*reseta mapa */
    				map.clearOverlays();

    				if (data.length < 1) {
    					alert("Nenhum registro encontrado!");
    					return;
    				}
    				/* carrega dados da consulta json */
    				loadDataImoveisJson(data);

    				/** adiciona marcadores no mapa */
    				printMarkers();

    				//$('#result').html("First Name:- " + obj.firstName +"Last Name:- " + obj.lastName + "Email:- " + obj.email);
    			}, 
    			error: function(){	 
    				alert('Error while request..'); 
    			} 
    		});
    	}

    	
    	/** popula dados dos imoveis filtrados */
    	function loadDataImoveisJson(imoveisList) {
    		for(var count=0; count<imoveisList.length; count++){
    			var data = imoveisList[count];
				// popula objeto imovel
				var $imovel = new Imovel();
	
				//@base
				$imovel.setId(data.id);
				$imovel.setEstado(data.estado, data.estado.description);
				$imovel.setSiteBusca(data.siteBusca, data.siteBusca.description);
				$imovel.setTipoOperacao(data.tipoOperacao, data.tipoOperacao.description);
				$imovel.setTipoImovel(data.tipoImovel, data.tipoImovel.description);
				$imovel.setCategoriaImovel(data.categoriaImovel, data.categoriaImovel.description);
				//@end
	
				//@resumo
				$imovel.setUrlAnuncio(data.urlAnuncio);
				$imovel.setCodigoAnuncio(data.codigoAnuncio);
				$imovel.setTituloResumo(data.tituloResumo);
				$imovel.setCaracteristicasResumo(data.caracteristicasResumo);
				$imovel.setDescricaoResumo(data.descricaoResumo);
				$imovel.setImgDestaque(data.imgDestaque);
				$imovel.setTotalImagens(data.totalImagens);
				//@end
	
				//@detalhamento
				$imovel.setAnunciante(data.anunciante);
				$imovel.setTelefone(data.telefone);
				$imovel.setDormitorios(data.dormitorios);
				$imovel.setBoxGaragem(data.boxGaragem);
				$imovel.setAreaTotal(data.areaTotalFormatted);
				$imovel.setAreaPrivativa(data.areaPrivativaFormatted);
				$imovel.setValor(data.valorFormatted);
				//@end
	
				//@localizacao
				$imovel.getLocalizacao().setEndereco(data.endereco);
				$imovel.getLocalizacao().setLatitude(data.latitude);
				$imovel.getLocalizacao().setLongitude(data.longitude);
				$imovel.getLocalizacao().setTipoLocalizacao(data.tipoLocalizacao, data.tipoLocalizacao.description);
				//@end
	
				//add imovel
				$imoveisList.push($imovel);
    		}
    	}
    	
    	/** popula dados dos imoveis filtrados */
    	function loadDataImoveis() {
			<c:forEach items="${imovelList}" var="data" varStatus="status">
			// popula objeto imovel
			var $imovel = new Imovel();

			//@base
			$imovel.setId("${data.id}");
			$imovel.setEstado("${data.estado}", "${data.estado.description}");
			$imovel.setSiteBusca("${data.siteBusca}", "${data.siteBusca.description}");
			$imovel.setTipoOperacao("${data.tipoOperacao}", "${data.tipoOperacao.description}");
			$imovel.setTipoImovel("${data.tipoImovel}", "${data.tipoImovel.description}");
			$imovel.setCategoriaImovel("${data.categoriaImovel}", "${data.categoriaImovel.description}");
			//@end

			//@resumo
			$imovel.setUrlAnuncio("${data.urlAnuncio}");
			$imovel.setCodigoAnuncio("${data.codigoAnuncio}");
			$imovel.setTituloResumo("${data.tituloResumo}");
			$imovel.setCaracteristicasResumo("${data.caracteristicasResumo}");
			$imovel.setDescricaoResumo("${data.descricaoResumo}");
			$imovel.setImgDestaque("${data.imgDestaque}");
			$imovel.setTotalImagens("${data.totalImagens}");
			//@end

			//@detalhamento
			$imovel.setAnunciante("${data.anunciante}");
			$imovel.setTelefone("${data.telefone}");
			$imovel.setDormitorios("${data.dormitorios}");
			$imovel.setBoxGaragem("${data.boxGaragem}");
			$imovel.setAreaTotal("${data.areaTotalFormatted}");
			$imovel.setAreaPrivativa("${data.areaPrivativaFormatted}");
			$imovel.setValor("${data.valorFormatted}");
			//@end

			//@localizacao
			$imovel.getLocalizacao().setEndereco("${data.endereco}");
			$imovel.getLocalizacao().setLatitude("${data.latitude}");
			$imovel.getLocalizacao().setLongitude("${data.longitude}");
			$imovel.getLocalizacao().setTipoLocalizacao("${data.tipoLocalizacao}", "${data.tipoLocalizacao.description}");
			//@end

			//add imovel
			$imoveisList.push($imovel);
			</c:forEach>
    	}

    	/** carrega assincronamente os marcadores no mapa */
    	function printMarkers() {
			for (var i = 0; i < $imoveisList.length; i++) {
				var $imovel = $imoveisList[i];
				/** adicona marcador */
				addMarker($imovel);
			}

			/** to zooming to fit all markers on that page */
			if ($imoveisList.length > 0) {
				map.fitBounds($fullBounds);
			}
			//setTimeout(function(){alert("Total de "+$imoveisList.length+" imoveis encontraos!")},10000);
    	}

    	function addMarkerControls($marker) {
    		/** zoom control */
    		$fullBounds.extend($marker.imovel.getLocalizacao().getLatLng());
			/* markers control */
			$allMarkers.push($marker);

			/* cluster manager */
			var maplKey = $marker.imovel.tipoImovel.getValue();

			var maplValues = $clusterMap[maplKey];
			if (maplValues == null) {
				maplValues = [];
			}
			maplValues.push($marker);
			$clusterMap[maplKey] = maplValues;
    	}
    	
    	/** add marker */
    	function addMarker($imovel) {

			var $marker = new google.maps.Marker({
		    	position: $imovel.getLocalizacao().getLatLng(),
		    	title: $imovel.getCodigoAnuncio(),
		    	map: map,
		    	draggable: false,
		    	animation: google.maps.Animation.DROP,
		    	imovel: $imovel,
		    	icon : $imovel.getMarkerIcon()
		  	});

    		/* add markers controls */
    		addMarkerControls($marker);

			/** click do marker */
			google.maps.event.addListener($marker, 'click', function(event) {
				//toggleBounce(this);

				//show info
				var infoBubble = new InfoBubble({
					maxWidth: 400,
					maxHeight: 400
					/*map: map,
					content: content,
					position: event.latLng,
					shadowStyle: 0,
					padding: 10,
					borderRadius: 10,
					borderWidth: 1,
					borderColor: '#ccc',
					backgroundColor: '#fff',
					maxWidth: 200,
					maxHeight: 50,
					arrowSize: 10,
					arrowPosition: 50,
					disableAutoPan: true,
					arrowStyle: 2*/
			    });

				var div = document.createElement('DIV');
		        div.innerHTML = this.imovel.mostraValores();

		        //adiciona tabs de informações
		        infoBubble.addTab(' Im&oacute;vel ', div);
		        infoBubble.addTab(' Imagem ', "<img src='"+this.imovel.getImgDestaque()+"' alt='Imagem'>");
		        
		        //abre info
				infoBubble.open(map, this);
			});
		}

    	/** toggle */
		function toggleBounce(marker) {
			if (marker.getAnimation() != null) {
			    marker.setAnimation(null);
			} else {
			    marker.setAnimation(google.maps.Animation.BOUNCE);
			}
		}

    	/** inicializa mapa */
		function initialize() {

			var googleMapOptions = {
				zoom : 14,
				center : new google.maps.LatLng(-30.019481, -51.178136),
				mapTypeId : google.maps.MapTypeId.ROADMAP,
				mapTypeControl : true,
				mapTypeControlOptions : {
					style : google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
					position : google.maps.ControlPosition.TOP_RIGHT
				},
				panControl : false,
				panControlOptions : {
					position : google.maps.ControlPosition.TOP_RIGHT
				},
				zoomControl : true,
				zoomControlOptions : {
					style : google.maps.ZoomControlStyle.LARGE,
					position : google.maps.ControlPosition.TOP_LEFT
				},
				scaleControl : true,
				scaleControlOptions : {
					position : google.maps.ControlPosition.RIGHT_BOTTOM	
				},
				streetViewControl : true,
				streetViewControlOptions : {
					position : google.maps.ControlPosition.TOP_LEFT
				}
			}

			/** initialize map */
			map = new google.maps.Map(document.getElementById('map_canvas'), googleMapOptions);

			/** CLEAR MARKERS */
			google.maps.Map.prototype.clearOverlays = function() {
				for( i=0;i<$allMarkers.length; i++ ) {
					$allMarkers[i].setMap(null);
	    		}
	    		$allMarkers = [];
		
	    		if (markerCluster != undefined) {
	    			markerCluster.clearMarkers();
	    		}

	        	$imoveisList = [];
	        	$clusterMap = {};
	        	$fullBounds = new google.maps.LatLngBounds();
			}

			/** initialize left panel */
			var sliderFilterDiv = document.createElement('div');
		    var sliderFilterControl = new SliderFilter(sliderFilterDiv, map);
		    sliderFilterDiv.index = -500;
		    map.controls[google.maps.ControlPosition.TOP_LEFT].push(sliderFilterDiv);

		    /** drawning manager */
			var drawingManager = new google.maps.drawing.DrawingManager(
			{
				drawingControl : true,
				drawingControlOptions : {
					position : google.maps.ControlPosition.TOP_CENTER,
					drawingModes : [
						google.maps.drawing.OverlayType.CIRCLE
					]
				},
				circleOptions : {
					strokeColor : "#ff0000",
					strokeOpacity : 0.8,
					strokeWeight : 1,
					fillColor : "#b0c4de",
					fillOpacity : 0.50,
					map : map,
					radius : 1000, //1km
					clickable : true,
					editable : true,
					zIndex : 1
				}
			});

			drawingManager.setMap(map);

			google.maps.event.addListener(drawingManager,'circlecomplete', function(circle){

				/* confirm dialog */
		        if(!confirm('Aplicar esse filtro?')) {
		        	circle.setMap(null);
		        	return;
		        }

				/* set to default hand cursor */
		        drawingManager.setDrawingMode(null);

				/* listeners */
		        google.maps.event.addListener(circle, 'radius_changed', function(){
		        	showAllMarkers();
		        	applyCircleFilter(circle);
		        });
		        google.maps.event.addListener(circle, 'center_changed', function(){
		        	showAllMarkers();
		        	applyCircleFilter(circle);
		        });

		        /* events */
		        applyDeleteCircleClickEvent(circle);
		        applyCircleFilter(circle);
			});
		}

    	// Marker Cluster
    	function applyMarkerCluster() {
    		//CA("Casa"), AP("Apartamento"), CO("Comercial"), TE("Terreno"), NA("Desconhecido");
    		
    		/*for(var key in $clusterMap) {
    			for (var i = 0; i < $clusterMap[key].length; i++) {
    				var data = $clusterMap[key][i];
    				alert(data.imovel.getValor());
    			}
    		}*/
    		
    		var styles = [[{
    	        url: '/img/clusters/CA.png',
    	        height: 37,
    	        width: 32,
    	        anchor: [22, 0],
    	        textColor: '#FFFFFF',
    	        textSize: 10
    	      }, {
    	        url: '/img/clusters/CA-1.png',
    	        height: 37,
    	        width: 32,
    	        anchor: [22, 0],
    	        textColor: '#FFFFFF',
    	        textSize: 10
    	      }, {
    	        url: '/img/clusters/CA-2.png',
    	        height: 37,
    	        width: 32,
    	        anchor: [24, 0],
    	        textColor: '#808080',
    	        textSize: 10
    	      }],
    	      /** apartments */
    	      [{
    	    	url: '/img/clusters/AP.png',
      	        height: 37,
      	        width: 32,
      	        anchor: [22, 0],
      	        textColor: '#FFFFFF',
      	        textSize: 10
      	      }, {
      	        url: '/img/clusters/AP-1.png',
      	        height: 37,
      	        width: 32,
      	        anchor: [22, 0],
      	        textColor: '#FFFFFF',
      	        textSize: 10
      	      }, {
      	        url: '/img/clusters/AP-2.png',
      	        height: 37,
      	        width: 32,
      	        anchor: [24, 0],
      	        textColor: '#808080',
      	        textSize: 10
      	      }]];

    		var halfMarkers = $allMarkers.length / 2;
    		var houses = $allMarkers.slice(0, halfMarkers);
    		var apartments = $allMarkers.slice(halfMarkers, $allMarkers.length);
    		
    		markerCluster = new MarkerClusterer(map, houses, {
    			title: ' Casas encontradas nessa regiao',
    	    	//maxZoom: zoom,
    	        //gridSize: size,
    	        styles: styles[0]
			});
        	google.maps.event.addListener(markerCluster, 'clusterclick', 
        		function(cluster) { 
        			var clickedMakrers = cluster.getMarkers(); 
        	  		alert("casas: "+cluster.getMarkers().length);
        		}
        	);
        	
        	markerCluster = new MarkerClusterer(map, apartments, {
        		title: apartments.length+' Apartamentos encontrados nessa regiao',
    	    	//maxZoom: zoom,
    	        //gridSize: size,
    	        styles: styles[1]
			});
        	google.maps.event.addListener(markerCluster, 'clusterclick', 
        		function(cluster) { 
        			var clickedMakrers = cluster.getMarkers(); 
        	  		alert("apartamentos: "+cluster.getMarkers().length);
        		}
        	);
    	}
    	
		// event handler for drawingManager shapes
        function applyDeleteCircleClickEvent(shape) {
             google.maps.event.addListener(shape, 'click', function(){
                //Coloca mansagem em formato Dialog UI
                if(confirm('Deseja excluir esse filtro?')) {
                    shape.setMap(null);
                    drawingManager.setOptions({
	                    //drawingMode: google.maps.drawing.OverlayType.MARKER,
	                    drawingControl: true,
	                    drawingControlOptions: {
	                        position: google.maps.ControlPosition.TOP_CENTER,
	                        drawingModes: [
	                            google.maps.drawing.OverlayType.CIRCLE
	                            //google.maps.drawing.OverlayType.POLYGON
	                        ]
	                      }
                    });

                    /* show all markers again */
                    showAllMarkers();
                }
            });
        }
    	
    	function applyCircleFilter(circle) {
    		var bounds = circle.getBounds();
    		var center = circle.getCenter();
    		var radius = circle.getRadius();
    		for( i=0;i<$allMarkers.length; i++ ) {
    			var latLng = $allMarkers[i].position;
    			if (bounds.contains(latLng) && google.maps.geometry.spherical.computeDistanceBetween(center, latLng) <= radius) {
    				
    			} else {
    				$allMarkers[i].setMap(null);
    			}
    		}
    	}
    	
    	function showAllMarkers() {
    		for( i=0;i<$allMarkers.length; i++ ) {
				$allMarkers[i].setMap(map);
    		}
    	}
		//google.maps.event.addDomListener(window, 'load', initialize);

	</script>
  </head>

  <body>
  	<div id="loader"><img src="/img/loader/drip-loader.gif"/></div>
  	<div id='rightSide'>
    	Something here from google
	</div>
	<div id='click'> > </div>
    <div id="map_canvas"></div>
  </body>
</html>
