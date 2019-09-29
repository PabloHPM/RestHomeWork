package config;

import dto.OrderDto;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static config.ConfigUrl.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;


public class SwagerStore {

    public Response getInventory() {
        return spec()
                .when()
                .get(GET_INVENTORY)
                .then()
                .extract()
                .response();
    }

    void placeAnOrder() {
        spec()
                .body(new OrderDto())
                .when()
                .post(PLACE_ORDER)
                .then()
                .extract()
                .response()
                .then()
                .assertThat()
                .statusCode(SC_OK);
    }

    void getAnOrder() {
        spec()
                .pathParam("orderId","220")
                .when()
                .get(ORDER_ID)
                .then()
                .extract()
                .response()
                .then()
                .assertThat()
                .statusCode(SC_OK);
    }

    void deleteAnOrder() {
        spec()
                .pathParam("orderId","220")
                .when()
                .delete(ORDER_ID)
                .then()
                .extract()
                .response()
                .then()
                .assertThat()
                .statusCode(SC_OK);
    }

    private RequestSpecification spec() {
        return given()
                .baseUri(PETSTORE_SWAGGER_HOME_URL)
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
    }
}
