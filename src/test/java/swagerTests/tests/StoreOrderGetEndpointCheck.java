package swagerTests.tests;

import dto.OrderDTO;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import swagerTests.common.SomeCommonClass;

import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreOrderGetEndpointCheck extends SomeCommonClass {
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 7, 10})
    void checkGetEndpointWithPositiveDifferentId(int id) {
        OrderDTO orderDto = new OrderDTO.OrderDTOBuilder()
            .withId(id)
            .withPetId()
            .withQuantity(1)
            .withShipData()
            .withComplete()
            .withStatus()
            .build();

        swaggerStore.placeAnOrder(orderDto);
        Response getResponse = swaggerStore.getAnOrder(orderDto.getId());

        assertEquals(SC_OK,getResponse.getStatusCode());
        deleteOrder(orderDto.getId());
    }

    @ParameterizedTest
    @ValueSource(ints = {-11, 0, 11, 22})
    void checkGetEndpointWithNegativeDifferentId(int id) {
        OrderDTO orderDto = new OrderDTO.OrderDTOBuilder()
            .withId(id)
            .withPetId()
            .withQuantity(1)
            .withShipData()
            .withComplete()
            .withStatus()
            .build();

        swaggerStore.placeAnOrder(orderDto);
        Response getResponse = swaggerStore.getAnOrder(orderDto.getId());

        assertEquals(SC_NOT_FOUND, getResponse.getStatusCode());
        deleteOrder(orderDto.getId());
    }

    @Test
    void checkOptions() {
        OrderDTO orderDto = new OrderDTO.OrderDTOBuilder()
            .withId(12)
            .withPetId()
            .withQuantity(1)
            .withShipData()
            .withComplete()
            .withStatus()
            .build();

        Headers getHeaders = swaggerStore.placeAnOrder(orderDto).getHeaders();

        Response getResponse = swaggerStore.getAnOrder(orderDto.getId());

        assertEquals(getResponse.getStatusCode(), SC_OK);
        assertEquals("*", getHeaders.get("Access-Control-Allow-Origin").getValue());
        assertEquals("GET, POST, DELETE, PUT", getHeaders.get("Access-Control-Allow-Methods").getValue());
        deleteOrder(orderDto.getId());
    }
}
