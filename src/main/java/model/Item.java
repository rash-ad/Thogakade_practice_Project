package model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    private String itemCode;
    private String description;
    private String packSize;
    private Double unitPrice;
    private int stock;

}
