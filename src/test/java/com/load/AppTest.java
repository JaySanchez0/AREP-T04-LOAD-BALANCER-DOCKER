package com.load;

import com.load.request.HTTPPetition;
import com.load.response.HttpResponse;
import junit.framework.TestCase;
import junit.framework.TestSuite;
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
    /**
     * Inicia el balanceador de carga
     */
    @BeforeClass
    public static void setup(){
        App.main(null);
    }

    /**
     * Verifica que la respuesta devuelva correctamente los archivos html
     */
    @Test
    public void shouldBeRecibeHtml(){
        HttpResponse resp = new HTTPPetition().get("http://localhost:3032");
        assertEquals(resp.getContentType(),"text/html");
    }

    /**
     * Verifica que la respuesta devuelva correctamente los objetos json
     */
    @Test
    public void shouldBeRecibeJsonObject(){
        HttpResponse resp = new HTTPPetition().get("http://localhost:3032/note");
        assertEquals(resp.getContentType(),"application/json");
    }

    /**
     * Verifica que la respuesta devuelva correctamente los archivos javascript
     */
    @Test
    public void shouldBeRecibeAJavascriptFile(){
        HttpResponse resp = new HTTPPetition().get("http://localhost:3032/js/app.js");
        assertEquals(resp.getContentType(),"application/javascript");
    }

    /**
     * Verifica que ejecute correctamente una peticion post
     */
    @Test
    public void shouldBeSendAnPostRequest(){
        HttpResponse resp = new HTTPPetition().post("http://localhost:3032/note","{\"work\":\"w\",\"description\":\"make the homework\"}","application/json");
        resp = new HTTPPetition().get("http://localhost:3032/note");
        assertTrue(resp.getBody().contains("\"work\":\"w\"") && resp.getBody().contains("\"description\":\"make the homework\""));
    }

    /**
     * Apaga el balanceador de carga
     */
    @AfterClass
    public static void stop(){
        Spark.stop();
    }
}
