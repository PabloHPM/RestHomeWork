package swagerTests.tests;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import swagerTests.common.SomeCommonClass;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreInventoryGetEndpointCheck extends SomeCommonClass {

    @Test
    void checkInventoryStatusCode() {
        Response response = swaggerStore.getInventory();
        Headers headers = response.headers();

        assertEquals(response.getStatusCode(), SC_OK);
        assertEquals(1, response.getBody().jsonPath().getInt("mine"));
        assertEquals(18, response.getBody().jsonPath().getInt("sold"));
        assertEquals(2, response.getBody().jsonPath().getInt("swagerTests"));
        assertEquals(3, response.getBody().jsonPath().getInt("custom"));
        assertEquals("*", headers.get("Access-Control-Allow-Origin").getValue());
        assertEquals("GET, POST, DELETE, PUT", headers.get("Access-Control-Allow-Methods").getValue());
    }
}
