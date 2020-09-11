package com.load;

import com.load.request.HTTPPetition;
import com.load.response.HttpResponse;
import spark.Request;
import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App {

    private int cont;

    public App(){
        cont=90;
    }

    /**
     * Load operations
     */
    public void operations(){
        port(80);
        get("*",(request, response) ->{
            String url = getUrl(request);
            HttpResponse rp = new HTTPPetition().get(url);
            response.header("Content-Type",rp.getContentType());
            return rp.getBody();
        });

        post("*",(req,res)->{
            String url = getUrl(req);
            HttpResponse rp = new HTTPPetition().post(url,req.body(),req.headers("Content-Type"));
            res.header("Content-Type",rp.getContentType());
            return rp.getBody();
        });;
    }

    private String getUrl(Request request) {
        if(request.queryString()==null){
            return "http://localhost:"+getPortConnection()+request.pathInfo();
        }else{
            return  "http://localhost:"+getPortConnection()+request.pathInfo()+"?"+request.queryString();
        }
    }


    public synchronized int getPortConnection(){
        int tmp = cont;
        if(cont==92) cont=90;
        else cont+=1;
        return tmp;
    }
    public static void main( String[] args){
        App app = new App();
        app.operations();
    }
}
