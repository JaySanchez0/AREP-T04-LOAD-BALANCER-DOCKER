package com.load;

import com.load.request.HTTPPetition;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @BeforeClass
    public static void setup(){
        App app = new App(3032);
        app.operations();
    }

    /**
     * Valida que devuelva un html
     * @throws Exception
     */
    @Test
    public void shouldBeRecibeHtml() throws Exception {
        HttpResponse<String> res = Unirest.get("http://localhost:3032").asString();
        assertEquals(res.getHeaders().getFirst("Content-Type"),"text/html");
    }

    /**
     * Valida que devuelva un objeto json
     * @throws Exception
     */
    @Test
    public void shouldBeRecibeJsonObject() throws Exception{
        HttpResponse<String> res = Unirest.get("http://localhost:3032/note").asString();
        assertEquals(res.getHeaders().getFirst("Content-Type"),"application/json");
    }

    /**
     * Valida que reciba un archivo javascript
     * @throws Exception
     */
    @Test
    public void shouldBeRecibeAJavascriptFile() throws Exception{
        HttpResponse<String> res = Unirest.get("http://localhost:3032/js/app.js").asString();
        assertEquals(res.getHeaders().getFirst("Content-Type"),"application/javascript");
    }

    private int getNoteOcuurences() throws Exception{
        String res = Unirest.get("http://localhost:3032/note").asString().getBody().toString();
        System.out.println(res);
        JSONArray array = new JSONArray(res);
        int cont = 0;
        for(int i=0;i<array.length();i++){
            JSONObject ob = array.getJSONObject(i);
            if(ob.getString("work").equals("testw") && ob.getString("description").equals("test")) cont+=1;
        }
        return cont;
    }

    /**
     * Valida que agregue satisfactoriamente una nueva nota
     * @throws Exception
     */
    @Test
    public void shouldBeAddAnNewNote() throws Exception{
        int c1 = getNoteOcuurences();
        Unirest.post("http://localhost:3032/note").header("Content-Type","application/json").body("{\"work\":\"testw\",\"description\":\"test\"}").asString().getBody();
        int c2 = getNoteOcuurences();
        assertTrue(c2>c1);
    }

    /**
     * Detiene el balanceador de carga
     */
    @AfterClass
    public static void stop(){
        Spark.stop();
    }
}
