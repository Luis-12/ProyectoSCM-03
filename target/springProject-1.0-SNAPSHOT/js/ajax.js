// config=={type: (GET|POST), url: URL, data: JS Object, contentType: (json/xml)
//          success: Succes Callback, error: Error Callaback}
function ajax(config){  
    var req = new XMLHttpRequest();
    req.open( config.type, config.url, true );
    if(config.type==="POST" || config.type==="PUT"){
        req.setRequestHeader("Content-Type", config.contentType);
    }    
    req.setRequestHeader("Accept", "application/json");
    req.onreadystatechange = function(){
        if( req.readyState === 4){ // terminada
            switch (req.status){
                case 200: // ok with response content
                    config.success(JSON.parse(req.responseText));
                    break;
                case 204: // ok with NO response content
                    config.success();
                    break;
                default:
                    if(config.error){
                        config.error(req.status);
                    };
                    break;                    
            }
        }
    };
    if(config.data){
        req.send(config.data); 
    }
    else{
        req.send(); 
    }
}

function errorMessage(status){
    switch(status){
        case 404: return "Registro no encontrado";
        case 403: case 405: return "Usuario no autorizado";
        case 406: return "Registro duplicado";
    }
}