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
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=drawing"></script>

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobubble/src/infobubble.js"></script>

    <script src="/js/classes/imoveis.js" type="text/javascript"></script>
    <script type="text/javascript">
    	var map;
    	var $imoveisList = [];
    	var $fullBounds = new google.maps.LatLngBounds();

    	$(document).ready(function(){
    		/** carrega dados da consulta */
    		loadDataImoveis();

    		/** inicializa mapa */
    		initialize();

    		/** adiciona marcadores no mapa */
			printMarkers();
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
			setTimeout(function(){alert("Total de "+$imoveisList.length+" imoveis encontraos!")},3000);
    	}

    	/** add marker */
    	function addMarker($imovel) {
    		/** zoom control */
    		$fullBounds.extend($imovel.getLocalizacao().getLatLng());

			var $marker = new google.maps.Marker({
		    	position: $imovel.getLocalizacao().getLatLng(),
		    	title: $imovel.getCodigoAnuncio(),
		    	map: map,
		    	draggable: false,
		    	animation: google.maps.Animation.DROP,
		    	imovel: $imovel,
		    	icon : '/img/markers/home-'+$imovel.getLocalizacao().getTipoLocalizacao().getValue()+'.png'
		  	});

			/** click do marker */
			google.maps.event.addListener($marker, 'click', function(event) {

				//show info
				var infoBubble = new InfoBubble({
					maxWidth: 400,
					maxHeight: 400
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

    	/** inicializa mapa */
		function initialize() {

			var mapOptions = {
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
					position : google.maps.ControlPosition.LEFT_TOP
				},
				scaleControl : true,
				scaleControlOptions : {
					position : google.maps.ControlPosition.TOP_LEFT
				},
				streetViewControl : true,
				streetViewControlOptions : {
					position : google.maps.ControlPosition.LEFT_TOP
				}
			}

			map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

			var drawingManager = new google.maps.drawing.DrawingManager(
			{
				drawingControl : true,
				drawingControlOptions : {
					position : google.maps.ControlPosition.TOP_CENTER,
					drawingModes : [
							google.maps.drawing.OverlayType.CIRCLE
					]
				},
				markerOptions : {
					icon : '/img/markers/home-E.png'
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

			// event handler for drawingManager shapes
	        function setClickEvent(shape) {
	             google.maps.event.addListener(shape, 'click', function(){
	                //Colocar mensaje en Formato Dialgo UI
	                if(confirm('Desea Eliminar El Punto de Control')){                      
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
	                }
	            });
	        }

			google.maps.event.addListener(drawingManager,'circlecomplete', function(circle){
				radius = circle.getRadius();
		        centro = circle.getCenter();
		        //document.getElementById("posicion").innerHTML=centro;
		        //document.getElementById("radio").innerHTML=radius;
		        alert('click');

		        //circle.setOptions({editable:false}); // <-- **** add this line
		        //drawingManager.setOptions({
		          //drawingControl: false
		        //});

		        google.maps.event.addListener(circle, 'radius_changed', function(){
		            radius = circle.getRadius();
		            alert("radius: "+radius);
		            //document.getElementById("radio").innerHTML=radius;
		        });

		        google.maps.event.addListener(circle, 'center_changed', function(){
		            centro = circle.getCenter();
		            alert("centro: "+centro);
		            //document.getElementById("posicion").innerHTML=centro;

		        });

		        setClickEvent(circle);
			});
		}

		//google.maps.event.addDomListener(window, 'load', initialize);
	</script>
  </head>

  <body>
    <div id="map-canvas"></div>
  </body>
</html>
