package com.load.response;

public class HttpResponse {

    private String contentType;

    private String body;

    /**
     *
     * @param body cuerpo del contenido repondido por el servidor
     * @param contentType tipo de respuesta devuelta por el servidor
     */
    public HttpResponse(String body,String contentType){
        this.contentType=contentType;
        this.body = body;
    }

    /**
     *
     * @return tipo de respuesta devuelta por el servidor
     */
    public String getContentType() {
        return contentType;
    }

    /**
     *
     * @return respuesta dada por el servidor
     */
    public String getBody() {
        return body;
    }
}
