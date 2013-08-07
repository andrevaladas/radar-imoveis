//Classe Imovel
function Imovel(){
	
	//@interface
    var id;
    var estado;
    var siteBusca;
    var tipoOperacao;
    var tipoImovel;
    var categoriaImovel;

    var urlAnuncio;
    var codigoAnuncio;
    var tituloResumo;
    var caracteristicasResumo;
    var descricaoResumo;
    var imgDestaque;
    var totalImagens;

    var anunciante;
    var telefone;
    var dormitorios;
    var boxGaragem;
    var areaTotal;
    var areaPrivativa;
    var valor;

    var localizacao;
    //@end

    //@constructe
    this.localizacao = new Localizacao();
    //@end

    //@implementation
    this.getLocalizacao=function(){
        return this.localizacao;
    };

    this.mostraValores=function() {
        return '<b>Id:</b> '+this.id+
        	   '<br/><b>Estado:</b> '+this.estado.getValue()+' - '+this.estado.getDescription()+
        	   '<br/><b>Site:</b> '+this.siteBusca.getValue()+' - '+this.siteBusca.getDescription()+
        	   '<br/><b>Opera&ccedil;&atilde;o:</b> '+this.tipoOperacao.getValue()+' - '+this.tipoOperacao.getDescription()+
        	   '<br/><b>Tipo Im&oacute;vel:</b> '+this.tipoImovel.getValue()+' - '+this.tipoImovel.getDescription()+
        	   '<br/><b>Categoria Im&oacute;vel:</b> '+this.categoriaImovel.getValue()+' - '+this.categoriaImovel.getDescription()+
        	   '<br/>'+

               '<br/><b>URL:</b> <a href="'+this.urlAnuncio+'" target="_blank"> >>Acesse esse an&uacute;ncio<< </a>'+
               '<br/><b>C&oacute;digo:</b> '+this.codigoAnuncio+
               '<br/><b>T&iacute;tulo:</b> '+this.tituloResumo+
               '<br/><b>Caracter&iacute;sticas:</b> '+this.caracteristicasResumo+
               '<br/><b>Descri&ccedil;&atilde;o:</b> '+this.descricaoResumo+
               '<br/><b>Imagem Destaque:</b> '+this.imgDestaque+
               '<br/><b>Total de Imagens:</b> '+this.totalImagens+
        	   '<br/>'+

        	   '<br/><b>Anunciante:</b> '+this.anunciante+
        	   '<br/><b>Telefone:</b> '+this.telefone+
        	   '<br/><b>Dormit&oacute;rios:</b> '+this.dormitorios+
        	   '<br/><b>Box Garagem:</b> '+this.boxGaragem+
        	   '<br/><b>&Aacute;rea Total:</b> '+this.areaTotal+
        	   '<br/><b>&Aacute;rea Privativa:</b> '+this.areaPrivativa+
               '<br/><b>Valor:</b> '+this.valor+
               '<br/>'+

               '<br/><b>Localiza&ccedil;&atilde;o:</b>'+
               '<br/><b>* Endere&ccedil;o:</b>'+this.getLocalizacao().getEndereco()+
               '<br/><b>**Latitude:</b> '+this.getLocalizacao().getLatitude()+
               '<br/><b>**Longitude:</b> '+this.getLocalizacao().getLongitude()+
               '<br/><b>***Tipo Localiza&ccedil;&atilde;o:</b> '+this.getLocalizacao().getTipoLocalizacao().getValue()+
               '<br/><b>***Tipo Localiza&ccedil;&atilde;o:</b> '+this.getLocalizacao().getTipoLocalizacao().getDescription();
    };
    
    this.getMarkerIcon=function() {
    	//CA("Casa"), AP("Apartamento"), CO("Comercial"), TE("Terreno"), NA("Desconhecido");
    	return '/img/markers/'+this.tipoImovel.getValue()+'-'+this.getLocalizacao().getTipoLocalizacao().getValue()+'.png';
    };

    this.getId=function(){
        return this.id;
    };
    this.setId=function(_id){
        this.id = _id;
    };

    this.getEstado=function(){
        return this.estado;
    };
    this.setEstado=function(_value, _description){
        this.estado = new Enum(_value, _description);;
    };

    this.getSiteBusca=function(){
        return this.siteBusca;
    };
    this.setSiteBusca=function(_value, _description){
        this.siteBusca = new Enum(_value, _description);;
    };

    this.getTipoOperacao=function(){
        return this.tipoOperacao;
    };
    this.setTipoOperacao=function(_value, _description){
        this.tipoOperacao = new Enum(_value, _description);;
    };
    
    this.getTipoImovel=function(){
        return this.tipoImovel;
    };
    this.setTipoImovel=function(_value, _description){
        this.tipoImovel = new Enum(_value, _description);;
    };

    this.getCategoriaImovel=function(){
        return this.categoriaImovel;
    };
    this.setCategoriaImovel=function(_value, _description){
        this.categoriaImovel = new Enum(_value, _description);;
    };
    
    this.getUrlAnuncio=function(){
        return this.urlAnuncio;
    };
    this.setUrlAnuncio=function(_urlAnuncio){
        this.urlAnuncio = _urlAnuncio;
    };

    this.getCodigoAnuncio=function(){
        return this.codigoAnuncio;
    };
    this.setCodigoAnuncio=function(_codigoAnuncio){
        this.codigoAnuncio = _codigoAnuncio;
    };
    
    this.getTituloResumo=function(){
        return this.tituloResumo;
    };
    this.setTituloResumo=function(_tituloResumo){
        this.tituloResumo = _tituloResumo;
    };
    
    this.getCaracteristicasResumo=function(){
        return this.caracteristicasResumo;
    };
    this.setCaracteristicasResumo=function(_caracteristicasResumo){
        this.caracteristicasResumo = _caracteristicasResumo;
    };
    
    this.getDescricaoResumo=function(){
        return this.descricaoResumo;
    };
    this.setDescricaoResumo=function(_descricaoResumo){
        this.descricaoResumo = _descricaoResumo;
    };
    
    this.getImgDestaque=function(){
        return this.imgDestaque;
    };
    this.setImgDestaque=function(_imgDestaque){
        this.imgDestaque = _imgDestaque;
    };
    
    this.getTotalImagens=function(){
        return this.totalImagens;
    };
    this.setTotalImagens=function(_totalImagens){
        this.totalImagens = _totalImagens;
    };

    this.getAnunciante=function(){
        return this.anunciante;
    };
    this.setAnunciante=function(_anunciante){
        this.anunciante = _anunciante;
    };

    this.getTelefone=function(){
        return this.telefone;
    };
    this.setTelefone=function(_telefone){
        this.telefone = _telefone;
    };

    this.getDormitorios=function(){
        return this.dormitorios;
    };
    this.setDormitorios=function(_dormitorios){
        this.dormitorios = _dormitorios;
    };

    this.getBoxGaragem=function(){
        return this.boxGaragem;
    };
    this.setBoxGaragem=function(_boxGaragem){
        this.boxGaragem = _boxGaragem;
    };

    this.getAreaTotal=function(){
        return this.areaTotal;
    };
    this.setAreaTotal=function(_areaTotal){
        this.areaTotal = _areaTotal;
    };

    this.getAreaPrivativa=function(){
        return this.areaPrivativa;
    };
    this.setAreaPrivativa=function(_areaPrivativa){
        this.areaPrivativa = _areaPrivativa;
    };

    this.getValor=function(){
        return this.valor;
    };
    this.setValor=function(_valor){
        this.valor = _valor;
    };
}

//Classe Localizacao
function Localizacao() {

	//@interface
	var endereco;
	var latitude;
	var longitude;
	var tipoLocalizacao;

	this.getEndereco=function(){
        return this.endereco;
    };
    this.setEndereco=function(_endereco){
        this.endereco = _endereco;
    };

    this.getLatitude=function(){
        return this.latitude;
    };
    this.setLatitude=function(_latitude){
        this.latitude = _latitude;
    };

    //google.maps.LatLng
    this.getLatLng=function(){
        return new google.maps.LatLng(this.latitude, this.longitude);
    };

    this.getLongitude=function(){
        return this.longitude;
    };
    this.setLongitude=function(_longitude){
        this.longitude = _longitude;
    };

    this.getTipoLocalizacao=function(){
        return this.tipoLocalizacao;
    };
    this.setTipoLocalizacao=function(_value, _description){
        this.tipoLocalizacao = new Enum(_value, _description);
    };
}

//Enum
function Enum(_value, _description) {

	//@interface
	var value = _value;
	var description = _description;

	this.getValue=function(){
        return value;
    };
    this.getDescription=function(){
        return description;
    };
}