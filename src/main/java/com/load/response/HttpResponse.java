package com.load.response;

public class HttpResponse {

    private String contentType;

    private String body;

    public HttpResponse(String body,String contentType){
        this.contentType=contentType;
        this.body = body;
    }

    public String getContentType() {
        return contentType;
    }

    public String getBody() {
        return body;
    }
}
