package swagerTests.tests;

import dto.OrderDTO;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import swagerTests.common.SomeCommonClass;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreOrderDeleteEndpointCheck extends SomeCommonClass {

    @Test
    void checkDeleteEndpointWithPositiveId() {
        OrderDTO orderDto = new OrderDTO.OrderDTOBuilder()
            .withId(12)
            .withPetId()
            .withQuantity(1)
            .withShipData()
            .withComplete()
            .withStatus()
            .build();

        swaggerStore.placeAnOrder(orderDto);
        Response deleteResponse = swaggerStore.deleteAnOrder(orderDto.getId());

        assertEquals(SC_OK, deleteResponse.getStatusCode());
        deleteOrder(orderDto.getId());
    }

    @ParameterizedTest
    @ValueSource(ints = {-11, 0})
    void checkDeleteEndpointWithNegativeDifferentId(int id) {
        OrderDTO orderDto = new OrderDTO.OrderDTOBuilder()
            .withId(id)
            .withPetId()
            .withQuantity(1)
            .withShipData()
            .withComplete()
            .withStatus()
            .build();

        swaggerStore.placeAnOrder(orderDto);
        Response deleteResponse = swaggerStore.deleteAnOrder(orderDto.getId());

        assertEquals(SC_NOT_FOUND, deleteResponse.getStatusCode());
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

        swaggerStore.placeAnOrder(orderDto);
        Headers deleteHeaders = swaggerStore.deleteAnOrder(orderDto.getId()).getHeaders();

        assertEquals("GET, POST, DELETE, PUT", deleteHeaders.get("Access-Control-Allow-Methods").getValue());
        deleteOrder(orderDto.getId());
    }
}
