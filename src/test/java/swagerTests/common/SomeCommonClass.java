package swagerTests.common;

import config.SwaggerStore;

public class SomeCommonClass {

    public static SwaggerStore swaggerStore = new SwaggerStore();

    public void deleteOrder(int id){
        swaggerStore.deleteAnOrder(id);
    }
}
