package com.load;

import com.load.request.HTTPPetition;
import com.load.response.HttpResponse;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
        App app = new App(3032);
        app.operations();
    }

    /**
     * Verifica que la respuesta devuelva correctamente los archivos html
     */
    @Test
        public void shouldBeRecibeHtml() throws Exception {
        HttpURLConnection ur = (HttpURLConnection) new URL("http://localhost:3032").openConnection();
        assertEquals(ur.getContentType(),"text/html");
    }

    /**
     * Verifica que la respuesta devuelva correctamente los objetos json
     */
    @Test
    public void shouldBeRecibeJsonObject() throws Exception{
        HttpURLConnection ur = (HttpURLConnection) new URL("http://localhost:3032/note").openConnection();
        assertEquals(ur.getContentType(),"application/json");
    }

    /**
     * Verifica que la respuesta devuelva correctamente los archivos javascript
     */
    @Test
    public void shouldBeRecibeAJavascriptFile() throws Exception{
        HttpURLConnection ur = (HttpURLConnection) new URL("http://localhost:3032/js/app.js").openConnection();
        //HttpResponse resp = new HTTPPetition().get("http://localhost:3032/js/app.js");
        assertEquals(ur.getContentType(),"application/javascript");
    }
    /**
     * Apaga el balanceador de carga
     */
    @AfterClass
    public static void stop(){
        Spark.stop();
    }
}
