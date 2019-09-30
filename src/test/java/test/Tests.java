package test;

import config.SwagerStore;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    private static SwagerStore swagerStore = new SwagerStore();

    @Test
    void checkInventoryStatusCode(){
        Response response = swagerStore.getInventory();

        assertEquals(response.getStatusCode(), SC_OK);
//        assertTrue(response.getBody().jsonPath().getInt("sold"));
    }

    @Test
    void checkInventoryHeaders(){
        Headers headers = swagerStore.getInventory().headers();
        assertEquals("*", headers.get("Access-Control-Allow-Origin").getValue());
        assertEquals("GET, POST, DELETE, PUT", headers.get("Access-Control-Allow-Methods").getValue());
    }

    @Test
    void checkInventory(){
        System.out.println(Arrays.toString(swagerStore.getInventory().getContentType().split(",")));
    }
}
