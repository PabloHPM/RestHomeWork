package swagerTests.tests;

import dto.OrderDTO;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import swagerTests.common.SomeCommonClass;

import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreOrderPostEndpointCheck extends SomeCommonClass {

    @Test
    void createOrder() {
        OrderDTO orderDto =
            new OrderDTO.OrderDTOBuilder().withId(12).withPetId().withQuantity(1).withShipData().withComplete()
                                          .withStatus().build();

        Response postResponse = swaggerStore.placeAnOrder(orderDto);
        Response getResponse = swaggerStore.getAnOrder(orderDto.getId());

        assertEquals(postResponse.getStatusCode(), SC_OK);
        assertEquals(postResponse.jsonPath().getInt("id"), orderDto.getId());
        assertEquals(postResponse.jsonPath().getInt("petId"), orderDto.getPetId());
        assertEquals(postResponse.jsonPath().getInt("quantity"), orderDto.getQuantity());
        assertEquals(postResponse.jsonPath().getString("shipDate"), orderDto.getShipDate());
        assertEquals(postResponse.jsonPath().getString("status"), orderDto.getStatus());
        assertEquals(postResponse.jsonPath().getString("complete"), orderDto.getComplete());

        assertEquals(getResponse.jsonPath().getInt("id"), orderDto.getId());
        assertEquals(getResponse.jsonPath().getInt("petId"), orderDto.getPetId());
        assertEquals(getResponse.jsonPath().getInt("quantity"), orderDto.getQuantity());
        assertEquals(getResponse.jsonPath().getString("shipDate"), orderDto.getShipDate());
        assertEquals(getResponse.jsonPath().getString("status"), orderDto.getStatus());
        assertEquals(getResponse.jsonPath().getString("complete"), orderDto.getComplete());

        deleteOrder(orderDto.getId());
    }

    @ParameterizedTest
    @ValueSource(ints = {-11, 0})
    void createOrderWithNegativeScenariosForId(int id) {
        OrderDTO orderDto =
            new OrderDTO.OrderDTOBuilder()
                .withId(id)
                .withPetId()
                .withQuantity(1)
                .withShipData()
                .withComplete()
                .withStatus()
                .build();

        Response postResponse = swaggerStore.placeAnOrder(orderDto);
        assertEquals(postResponse.getStatusCode(), SC_NOT_FOUND);
        deleteOrder(orderDto.getId());
    }

    @ParameterizedTest
    @ValueSource(ints = {-11, 0})
    void createOrderWithNegativeScenariosForQuantity(int quantity) {
        OrderDTO orderDto =
            new OrderDTO.OrderDTOBuilder()
                .withId(12)
                .withPetId()
                .withQuantity(quantity)
                .withShipData()
                .withComplete()
                .withStatus()
                .build();

        Response postResponse = swaggerStore.placeAnOrder(orderDto);
        assertEquals(SC_BAD_REQUEST, postResponse.getStatusCode());
        deleteOrder(orderDto.getId());
    }

    @Test
    void checkOptions() {
        OrderDTO orderDto =
            new OrderDTO.OrderDTOBuilder().withId(12).withPetId().withQuantity(1).withShipData().withComplete()
                                          .withStatus().build();

        Headers postHeaders = swaggerStore.placeAnOrder(orderDto).getHeaders();
        Response postResponse = swaggerStore.placeAnOrder(orderDto);

        assertEquals(postResponse.getStatusCode(), SC_OK);
        assertEquals("*", postHeaders.get("Access-Control-Allow-Origin").getValue());
        assertEquals("GET, POST, DELETE, PUT", postHeaders.get("Access-Control-Allow-Methods").getValue());
        deleteOrder(orderDto.getId());
    }
}
