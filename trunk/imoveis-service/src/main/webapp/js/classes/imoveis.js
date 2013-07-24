//Classe Imovel
function Imovel(){
	
	//@interface
    var id;
    var urlAnuncio;
    var codigoAnuncio;
    var tituloResumo;
    var caracteristicasResumo;
    var descricaoResumo;
    var imgDestaque;
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
        return '<b>C&oacute;digo:</b> '+this.codigoAnuncio+
               '<br/><b>URL:</b> <a href="'+this.urlAnuncio+'" target="_blank"> >>Acesse esse an&uacute;ncio<< </a>'+
               '<br/><b>T&iacute;tulo:</b> '+this.tituloResumo+
               '<br/><b>Valor:</b> '+this.valor+
               '<br/><b>Localiza&ccedil;&atilde;o:</b><br/><b>* Endere&ccedil;o:</b>'+this.localizacao.getEndereco()+
               '<br/><b>**Latitude:</b> '+this.localizacao.getLatitude()+
               '<br/><b>**Longitude:</b> '+this.localizacao.getLongitude()+
               '<br/><b>***Tipo Localiza&ccedil;&atilde;o:</b> '+this.localizacao.getTipoLocalizacao();
    };

    this.getId=function(){
        return this.id;
    };
    this.setId=function(_id){
        this.id = _id;
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
    
    this.getValor=function(){
        return this.valor;
    };
    this.setValor=function(_valor){
        this.valor = _valor;
    };
}

//Classe Localizacao
function Localizacao() {
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
    this.setTipoLocalizacao=function(_tipoLocalizacao){
        this.tipoLocalizacao = _tipoLocalizacao;
    };
}