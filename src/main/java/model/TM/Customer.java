package model.TM;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter@AllArgsConstructor
@ToString
@Data
public class Customer {
    private String id;
    private String name;
    private String title;
    private LocalDate dob;
    private Double salary;
    private String address;
    private String city;
    private String province;
    private String postalCode;
}
