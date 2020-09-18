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

    @BeforeClass
    public static void setup(){
        App app = new App(3033);
        app.operations();
    }

    @Test
    public void shouldBeRecibeHtml(){
        HttpResponse resp = new HTTPPetition().get("http://localhost:3033");
        assertEquals(resp.getContentType(),"text/html");
    }

    @Test
    public void shouldBeRecibeJsonObject(){
        HttpResponse resp = new HTTPPetition().get("http://localhost:3033/note");
        assertEquals(resp.getContentType(),"application/json");
    }

    @Test
    public void shouldBeRecibeAJavascriptFile(){
        HttpResponse resp = new HTTPPetition().get("http://localhost:3033/js/app.js");
        System.out.println(resp.getBody());
        assertEquals(resp.getContentType(),"application/javascript");
    }


    @AfterClass
    public static void stop(){
        Spark.stop();
    }
}
