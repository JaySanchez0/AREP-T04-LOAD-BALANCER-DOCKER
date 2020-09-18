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

    private int port = -1;

    public App(){
        cont=90;
    }

    public App(int port){
        this();
        this.port=port;
    }

    /**
     * Load operations
     */
    public void operations(){
        port(getPort());
        get("*",(request, response) ->{
            String url = getUrl(request);
            System.out.println("url: "+url);
            HttpResponse rp = new HTTPPetition().get(url);
            response.header("Content-Type",rp.getContentType());
            return rp.getBody();
        });

        post("*",(req,res)->{
            String url = getUrl(req);
            System.out.println("url: "+url);
            HttpResponse rp = new HTTPPetition().post(url,req.body(),req.headers("Content-Type"));
            res.header("Content-Type",rp.getContentType());
            return rp.getBody();
        });;
    }

    /**
     *
     * @param request Objeto request de spark
     * @return url a la que va a solicitar
     */
    private String getUrl(Request request) {
        String url = "http://ec2-100-25-103-0.compute-1.amazonaws.com:";
        //String url = "http://localhost:";
        if(request.queryString()==null){
            return url+getPortConnection()+request.pathInfo();
        }else{
            return  url+getPortConnection()+request.pathInfo()+"?"+request.queryString();
        }
    }

    /**
     *
     * @return puerto al cual se va a conectar en la aplicacion remota
     */
    public synchronized int getPortConnection(){
        int tmp = cont;
        if(cont==92) cont=90;
        else cont+=1;
        return tmp;
    }

    /**
     *
     * @param args init app
     */
    public static void main( String[] args){
        App app = new App();
        app.operations();
    }

    private int getPort() {
        System.out.println("---- PORT --- "+this.port);
        if(this.port!=-1) return this.port;
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 80;
    }
}
