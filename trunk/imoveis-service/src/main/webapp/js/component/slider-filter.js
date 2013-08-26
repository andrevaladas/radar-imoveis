function SliderFilter(controlDiv, map) {

    var control = this;
    control.isOpen = true;

    /** CARREGA PAGINA COM LAYOUT */
    $(controlDiv).load("/component/slider-filter", function () {
    	setTimeout(function() {

    		//busca estados - JSON
	        $.getJSON("/get/estados", function (data) {
		    	console.log( "success" );
		    	$.each(data, function(key, val) {
		    		console.log('@@@@@@@@@@@@@@ '+$("#estado"));
		    		console.log( "estado: "+key+"|"+val );
		    		$("<option/>").attr("value", key).text(val).appendTo($("#estado"));
	    	  	});
		    })
		    .done(function() {

		    	/** ESTADO CHANGE */
		    	$('#estado').change( function () {
		    		//remove cidades
			        $("#cidade").find("option:gt(0)").remove();
			        //remove bairros
			        $("#bairro").find("option:gt(0)").remove();

			        //valida estado selecionado
			    	if (!$(this).val()) {
			    		return;
			    	}

			        //busca cidades - JSON
			        $.getJSON("/get/cidades", {
			            estado: $(this).val()
			        }, function (data) {
			        	if (!data) {
			        		return;
			        	}
			        	$.each(data, function(key, val) {
				    		console.log( "cidade: "+key+"|"+val );
				    		$("<option/>").attr("value", key).text(val).appendTo($('#cidade'));
			    	  	});
			        })
			        .done(function() {

			        	/** CIDADE CHANGE */
				    	$('#cidade').change( function () { 
				    		//remove bairos
					        $("#bairro").find("option:gt(0)").remove();

					        //valida estado selecionado
					    	if (!$(this).val()) {
					    		return;
					    	}

					        //busca bairros - JSON
					        $.getJSON("/get/bairros", {
					            cidade: $(this).val()
					        }, function (data) {
					        	if (!data) {
					        		return;
					        	}
					        	$.each(data, function(key, val) {
						    		console.log( "bairro: "+key+"|"+val );
						    		$("<option/>").attr("value", key).text(val).appendTo($('#bairro'));
					    	  	});
					        })
					        .done(function() {})
						    .fail(function() { console.log( "get/bairros error" ); });
					    });
			        })
				    .fail(function() { console.log( "get/cidades error" ); });
			    });

		    	/** DEFAULT ESTADO */
		    	$('#estado option[value="RS"]').prop('selected', true).change();

		    	/** PARAMETROS IMOVEIS */
		    	$.getJSON("/get/sites-busca", function (data) {
			    	$.each(data, function(key, val) {
			    		console.log( "site-busca: "+key+"|"+val );
			    		$("<option/>").attr("value", key).text(val).appendTo($("#site-busca"));
		    	  	});
			    });
		    	$.getJSON("/get/tipos-operacoes", function (data) {
			    	$.each(data, function(key, val) {
			    		console.log( "tipo-operacao: "+key+"|"+val );
			    		$("<option/>").attr("value", key).text(val).appendTo($("#tipo-operacao"));
		    	  	});
			    }).done(function() { 
		    		$('#tipo-operacao option:eq(1)').prop('selected', true) 
		    	});
		    	$.getJSON("/get/tipos-imoveis", function (data) {
			    	$.each(data, function(key, val) {
			    		console.log( "tipo-imovel: "+key+"|"+val );
			    		$("<option/>").attr("value", key).text(val).appendTo($("#tipo-imovel"));
		    	  	});
			    }).done(function() { 
		    		$('#tipo-imovel option:eq(1)').prop('selected', true) 
		    	});
		    	$.getJSON("/get/categorias-imoveis", function (data) {
		    		$.each(data, function(key, val) {
		    			console.log( "categoria-imovel: "+key+"|"+val );
		    			$("<option/>").attr("value", key).text(val).appendTo($("#categoria-imovel"));
		    		});
		    	}).done(function() { 
		    		$('#categoria-imovel option:eq(1)').prop('selected', true) 
		    	});
		    	$.getJSON("/get/tipos-localizacao", function (data) {
		    		$.each(data, function(key, val) {
		    			console.log( "tipo-localizacao: "+key+"|"+val );
		    			$("<option/>").attr("value", key).text(val).appendTo($("#tipo-localizacao"));
		    		});
		    	}).done(function() { 
		    		$('#tipo-localizacao option:eq(1)').prop('selected', true) 
		    	});

		    	/** ACAO BOTOES */
			    $('#toggleButton').click( function() {
			        if (control.isOpen) {
			            $("#slider-filter").animate({
			                "marginLeft": "-=150px"
			            }, {
			                duration: 500,
			                step: function() {
			                    google.maps.event.trigger(map, 'resize');
			                }
			            });
			            control.isOpen = false;
			            toggleButton.value = 'Open';
			        } else {
			            $("#slider-filter").animate({
			                "marginLeft": "+=150px"
			            }, {
			                duration: 500,
			                step: function() {
			                    google.maps.event.trigger(map, 'resize');
			                }
			            });
			            control.isOpen = true;
			            toggleButton.value = 'Close';
			        };
			    });

			    $('#filterBtn').click( function() {
			    	if ($('#estado').val()) {
			    		filterLocation();
			    	} else {
			    		alert('Informe um Estado!');
			    	}
			    });
		    	console.log( "second success" ); 
		    })
		    .fail(function() { console.log( "error" ); })
		    .always(function() { console.log( "complete" ); });
    	}, 300);
	});
}