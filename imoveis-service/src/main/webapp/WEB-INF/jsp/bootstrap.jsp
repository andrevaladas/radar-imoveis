<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>Bootstrap</title>

		<!-- Bootstrap -->
    	<link href="/css/bootstrap.css" rel="stylesheet">
		<link href="/css/bootstrap-responsive.css" rel="stylesheet">
    	<link href="/css/datepicker.css" rel="stylesheet">
	
		<style type="text/css">
			.form-horizontal .control-group {
				margin-bottom: 5px;
			}
			input, textarea, .uneditable-input {
    			width: 75px;
			}
	    </style>
	</head>
<body>
	<div class="navbar navbar-inverse" style="position: static;">
       <div class="navbar-inner">
         <div class="container">
           <a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-inverse-collapse">
             <span class="icon-bar"></span>
             <span class="icon-bar"></span>
             <span class="icon-bar"></span>
           </a>
           <a class="brand" href="#">Title</a>
           <div class="nav-collapse collapse navbar-inverse-collapse">
             <ul class="nav">
               <li class="active"><a href="#">Home</a></li>
               <li><a href="#">Link</a></li>
               <li><a href="#">Link</a></li>
               <li class="dropdown">
                 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                 <ul class="dropdown-menu">
                   <li><a href="#">Action</a></li>
                   <li><a href="#">Another action</a></li>
                   <li><a href="#">Something else here</a></li>
                   <li class="divider"></li>
                   <li class="nav-header">Nav header</li>
                   <li><a href="#">Separated link</a></li>
                   <li><a href="#">One more separated link</a></li>
                 </ul>
               </li>
             </ul>
             <form class="navbar-search pull-left" action="">
               <input type="text" class="search-query span2" placeholder="Search">
             </form>
             <ul class="nav pull-right">
               <li><a href="#">Link</a></li>
               <li class="divider-vertical"></li>
               <li class="dropdown">
                 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                 <ul class="dropdown-menu">
                   <li><a href="#">Action</a></li>
                   <li><a href="#">Another action</a></li>
                   <li><a href="#">Something else here</a></li>
                   <li class="divider"></li>
                   <li><a href="#">Separated link</a></li>
                 </ul>
               </li>
             </ul>
           </div><!-- /.nav-collapse -->
         </div>
       </div><!-- /navbar-inner -->
     </div>
     
     <ul class="breadcrumb" style="margin-bottom: 5px;">
       <li><a href="#">Home</a> <span class="divider">/</span></li>
       <li><a href="#">Library</a> <span class="divider">/</span></li>
       <li class="active">Data</li>
     </ul>

	<legend>Form Example</legend>

	<form:form method="post" action="#" class="form-horizontal">
        <div class="control-group">
       	 	<div class="control-label">Plugin Auto-complete:</div>
       		<div class="controls">
       			<input type="hidden" id="ajax-typeahead-id"/>
				<input type="text" id="ajax-typeahead" class="span3" />
			</div>
        </div>
		<div class="control-group">
       	 	<div class="control-label">Native Auto-complete:</div>
       		<div class="controls">
       			<input type='text' class='ajax-typeahead' data-link='/ajax' />
			</div>
        </div>
        <div class="control-group">
       	 	<div class="control-label">Static Auto-complete:</div>
       		<div class="controls">
				<input type="text" class="span3 " style="margin: 0 auto;" data-provide="typeahead" data-items="4" data-source="[&quot;Alabama&quot;,&quot;Alaska&quot;,&quot;Arizona&quot;,&quot;Arkansas&quot;,&quot;California&quot;,&quot;Colorado&quot;,&quot;Connecticut&quot;,&quot;Delaware&quot;,&quot;Florida&quot;,&quot;Georgia&quot;,&quot;Hawaii&quot;,&quot;Idaho&quot;,&quot;Illinois&quot;,&quot;Indiana&quot;,&quot;Iowa&quot;,&quot;Kansas&quot;,&quot;Kentucky&quot;,&quot;Louisiana&quot;,&quot;Maine&quot;,&quot;Maryland&quot;,&quot;Massachusetts&quot;,&quot;Michigan&quot;,&quot;Minnesota&quot;,&quot;Mississippi&quot;,&quot;Missouri&quot;,&quot;Montana&quot;,&quot;Nebraska&quot;,&quot;Nevada&quot;,&quot;New Hampshire&quot;,&quot;New Jersey&quot;,&quot;New Mexico&quot;,&quot;New York&quot;,&quot;North Dakota&quot;,&quot;North Carolina&quot;,&quot;Ohio&quot;,&quot;Oklahoma&quot;,&quot;Oregon&quot;,&quot;Pennsylvania&quot;,&quot;Rhode Island&quot;,&quot;South Carolina&quot;,&quot;South Dakota&quot;,&quot;Tennessee&quot;,&quot;Texas&quot;,&quot;Utah&quot;,&quot;Vermont&quot;,&quot;Virginia&quot;,&quot;Washington&quot;,&quot;West Virginia&quot;,&quot;Wisconsin&quot;,&quot;Wyoming&quot;]">
			</div>
        </div>

		<div class="control-group">
       	 	<div class="control-label">Data:</div>
       		<div class="controls">
        		<input type="text" class="datepicker" value="25/11/2012" data-date-format="dd/mm/yyyy" data-mask="99/99/9999" id="datepicker">
        	</div>
        </div>
		<div class="control-group">
       	 	<div class="control-label">Nome:</div>
       		<div class="controls">
         		<input class="span5" type="text" placeholder="Nome">
       		</div>
       	</div>
       	<div class="control-group">
       	 	<div class="control-label">Endereço:</div>
	       	<div class="controls controls-row">
	        	<input class="span4" type="text" placeholder="Endereço">
	         	<input class="span1" type="text" placeholder="Número">
	         </div>
       	</div>
       	<div class="control-group">
       	 	<div class="control-label">Cidade:</div>
	       	<div class="controls controls-row">
	         	<input class="span3" type="text" placeholder="Cidade">
	         	<input class="span2" type="text" placeholder="CEP">
	       	</div>
		</div>
       	<div class="control-group">
       	 	<div class="control-label">Estado:</div>
	       	<div class="controls controls-row">
	        	<input class="span2" type="text" placeholder="Estado">
	         	<input class="span3" type="text" placeholder="País">
	       	</div>
		</div>
       	<div class="control-group">
       	 	<div class="control-label">Código:</div>
	       	<div class="controls controls-row">
	        	<input class="span1" type="text" placeholder="Código" disabled>
	         	<input class="span4" type="text" placeholder="Descrição" disabled>
	       	</div>
		</div>
       	<div class="form-actions">
		  	<button type="submit" class="btn btn-primary">Save changes</button>
		  	<button type="button" class="btn">Cancel</button>
		  	<!-- Button to trigger modal -->
			<a href="#myModal" role="button" class="btn" data-toggle="modal">Launch modal</a>
		</div>
    </form:form>

	<div class="accordion" id="accordion2">
	     <div class="accordion-group">
	       <div class="accordion-heading">
	         <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
	           Collapsible Group Item #1
	         </a>
	       </div>
	       <div id="collapseOne" class="accordion-body collapse" style="height: 0px;">
	         <div class="accordion-inner">
	           Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
	         </div>
	       </div>
	     </div>
	     <div class="accordion-group">
	       <div class="accordion-heading">
	         <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
	           Collapsible Group Item #2
	         </a>
	       </div>
	       <div id="collapseTwo" class="accordion-body collapse" style="height: 0px;">
	         <div class="accordion-inner">
	           Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
	         </div>
	       </div>
	     </div>
	     <div class="accordion-group">
	       <div class="accordion-heading">
	         <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree">
	           Collapsible Group Item #3
	         </a>
	       </div>
	       <div id="collapseThree" class="accordion-body collapse" style="height: 0px;">
	         <div class="accordion-inner">
	           Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
	         </div>
	       </div>
	     </div>
     </div>

    <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
	    <div class="modal-header">
	      	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	      	<h3 id="myModalLabel" align="center">Modal Heading</h3>
	    </div>
	    <div class="modal-body">
	      	<h4>Text in a modal</h4>
	      	<p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem.</p>
	
	      	<h4>Popover in a modal</h4>
	      	<p>This <a href="#" role="button" class="btn popover-test" data-content="And here's some amazing content. It's very engaging. right?" data-original-title="A Title">button</a> should trigger a popover on click.</p>
	
	      	<h4>Tooltips in a modal</h4>
	      	<p><a href="#" class="tooltip-test" data-original-title="Tooltip">This link</a> and <a href="#" class="tooltip-test" data-original-title="Tooltip">that link</a> should have tooltips on hover.</p>
	    </div>
	    <div class="modal-footer">
	      	<button class="btn" data-dismiss="modal">Close</button>
	      	<button class="btn btn-primary">Save changes</button>
	    </div>
	</div>
		

	<script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript" src="/js/bootstrap-affix.js"></script>
	<script type="text/javascript" src="/js/bootstrap-alert.js"></script>
	<script type="text/javascript" src="/js/bootstrap-button.js"></script>
	<script type="text/javascript" src="/js/bootstrap-carousel.js"></script>
	<script type="text/javascript" src="/js/bootstrap-collapse.js"></script>
	<script type="text/javascript" src="/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="/js/bootstrap-dropdown.js"></script>
	<script type="text/javascript" src="/js/bootstrap-modal.js"></script>
	<!-- script type="text/javascript" src="/js/bootstrap-popover.js"></script-->
	<script type="text/javascript" src="/js/bootstrap-scrollspy.js"></script>
	<script type="text/javascript" src="/js/bootstrap-tab.js"></script>
	<script type="text/javascript" src="/js/bootstrap-tooltip.js"></script>
	<script type="text/javascript" src="/js/bootstrap-transition.js"></script>
	<script type="text/javascript" src="/js/ajax-typeahead.js"></script>
	<script type="text/javascript" src="/js/bootstrap-inputmask.js"></script>

	<script type="text/javascript">
		$('.datepicker').datepicker()
			.on('changeDate', function(ev){
				$(this).datepicker('hide');
			}
		);
		
		$('#myModal').modal({
			backdrop: 'static'
		});

		//$('.datepicker').inputmask();

		$("#ajax-typeahead").typeahead({
		    ajax: {
		        url: "/ajax2",
		        timeout: 500,
		        triggerLength: 3,
		        method: "get",
		        idProperty: "ajax-typeahead-id",
		        displayField: "name",
		        loadingClass: "loading-circle",
		        preDispatch: function (query) {
		            //showLoadingMask(true);
		            return {
		                search: query,
		                query: query,
		                otherParam: 123
		            }
		        },
		        preProcess: function (data) {
		            //showLoadingMask(false);
		            if (data.success === false) {
		                // Hide the list, there was some error
		                return false;
		            }
		            // We good!
		            return data.options;
		        }
		    }
		});

		$('.ajax-typeahead').typeahead({
		    source: function(query, process) {
		        return $.ajax({
		            url: $(this)[0].$element[0].dataset.link,
		            type: 'get',
		            data: {query: query},
		            dataType: 'json',
		            success: function(json) {
		                return typeof json.options == 'undefined' ? false : process(json.options);
		            }
		        });
		    }
		});
	</script>
	
</body>
</html>