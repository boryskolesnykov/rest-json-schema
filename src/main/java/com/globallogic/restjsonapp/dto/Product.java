package com.globallogic.restjsonapp.dto;

import com.globallogic.restjsonapp.schema_constructor.Scannable;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@Data
@Scannable
@XmlRootElement
public class Product {

    @Min(1)
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @Min(0)@Max(Integer.MAX_VALUE)
    @NotNull
    private double price;

    private Set<Customer> customers;


    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
