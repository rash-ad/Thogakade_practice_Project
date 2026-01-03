package model.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@ToString
public class CustomerTM {
    private String id;
    private String name;
    private String address;
    private Date dob;
    private Double salary;
    private String city;
    private String province;
    private String postalCode;


    public CustomerTM(String id, String title, String name, String postalCode, String address, Date dob, Double salary, String city, String province) {
        this.id=id;
        this.name =title+":"+name;
        this.address=address;
        this.dob=dob;
        this.salary=salary;
        this.city=city+" : "+this.postalCode;
        this.province=province;

        
    }
}