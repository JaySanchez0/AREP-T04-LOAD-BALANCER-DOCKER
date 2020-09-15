package com.load.request;


import com.load.response.HttpResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HTTPPetition {

    public HTTPPetition(){

    }

    /**
     * Peticion get al servidor
     * @param url url a la cual se va a realizar la peticion
     * @return respuesta de la solicitud
     */
    public HttpResponse get(String url){
        return makePetition("GET",url,null,null);
    }

    /**
     * Peticion post al servidor
     * @param url url a la que se va a realizar peticion
     * @param body cuerpo de la peticion
     * @param type tipo del contenido correspondiente a el cuerpo.
     * @return un objeto que representa la respuesta
     */
    public HttpResponse post(String url,String body,String type){
        return makePetition("POST",url,body,type);
    }

    private HttpResponse makePetition(String method,String url,String body,String type) {
        try {
            HttpURLConnection ur = (HttpURLConnection) new URL(url).openConnection();
            ur.setRequestMethod(method);
            if(type!=null) ur.setRequestProperty("Content-Type",type);
            if(body!=null) ur.setRequestProperty("Content-Length",String.valueOf(body.getBytes().length));
            if(body!=null){
                ur.setDoOutput(true);
                ur.getOutputStream().write(body.getBytes());
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(ur.getInputStream()));
            String line;
            String resp="";
            while ((line=in.readLine())!=null) resp=resp+line+"\n";
            System.out.println("-- Respuesta --");
            if(method.equals("POST")) System.out.println(resp);
            HttpResponse response = new HttpResponse(resp,ur.getHeaderField("Content-Type"));
            in.close();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
