package dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Random;

@Data
public class OrderDto {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private String complete;

    public OrderDto() {
        this.id = 220;
        this.petId = getRndValue();
        this.quantity = getRndValue();
        this.shipDate = String.valueOf(LocalDateTime.now()).concat("+0000");
        this.status = "Available";
        this.complete = "True";
    }

    private int getRndValue() {
        return new Random().nextInt(100);
    }

}
