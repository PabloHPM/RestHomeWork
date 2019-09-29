package test;

import config.SwagerStore;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    private static SwagerStore swagerStore = new SwagerStore();

    @Test
    void checkInventoryStatusCode(){
        assertEquals(swagerStore.getInventory().getStatusCode(), SC_OK);
    }

    @Test
    void checkInventory(){
        System.out.println(Arrays.toString(swagerStore.getInventory().getContentType().split(",")));
    }
}
