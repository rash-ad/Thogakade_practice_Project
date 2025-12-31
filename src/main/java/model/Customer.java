package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Customer {
    private String id;
    private String name;
    private String address;
    private Date DOB;
    private Double Salary;
    private String city;
    private String province;


    public Customer(String id,String title,String name, String postalCode,String address, Date DOB, Double Salary, String city, String province) {
        this.id=id;
        this.name =title+":"+name;
        this.address=address;
        this.DOB=DOB;
        this.Salary=Salary;
        this.city=city+" : "+postalCode;
        this.province=province;

        
    }
}