package TM;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartTM {
    private String itemCode;
    private  String description;
    private String packSize;
    private  Double unitPrice;
    private Integer stock;
}
