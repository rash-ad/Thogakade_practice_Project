package model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Data

public class Item {
    private String id;
    private String name;
    private Double price;
    private Integer quantity;
    private String category;

}
