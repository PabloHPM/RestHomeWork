package dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private String complete;

    public OrderDto(){
        this.id = 220;
        this.petId = 1;
        this.quantity = 12;
        this.shipDate = String.valueOf(LocalDateTime.now()).concat("+0000");
        this.status = "Available";
        this.complete = "false";
    }

}
