package com.load.request;


import com.load.response.HttpResponse;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

public class HTTPPetition {

    public HTTPPetition(){

    }

    public HttpResponse get(String url){
        return makePetition("GET",url,null,null);
    }

    public HttpResponse post(String url,String body,String type){
        return makePetition("POST",url,body,type);
    }

    private HttpResponse makePetition(String method,String url,String body,String type){
        String host = null;
        int port =0;
        String path=null;
        String query=null;
        try {
            host = new URL(url).getHost();
            port = new URL(url).getPort();
            path = new URL(url).getPath();
            query= new URL(url).getQuery();
            System.out.println(host+" "+port+" "+path+" "+query);
            Socket socket = new Socket(host,port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            if(query!=null) out.println(method+" "+path+"?"+query+" HTTP/1.1");
            else out.println(method+" "+path+" HTTP/1.1");
            if(type!=null) out.println("Content-Type : "+type);
            out.println();
            if(body!=null) out.println(body);
            String ln = null;
            while ((ln=in.readLine()).length()>0){

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
