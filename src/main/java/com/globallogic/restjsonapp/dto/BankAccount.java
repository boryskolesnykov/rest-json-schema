package com.globallogic.restjsonapp.dto;

import com.globallogic.restjsonapp.schema_constructor.Scannable;
import com.sun.javafx.beans.annotations.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Scannable
public class BankAccount {

    @Min(1)
    private Long id;

    @Min(0)@Max(Integer.MAX_VALUE)
    @NonNull
    private double amount;

    @NonNull
    private Currency currency;

    private Customer owner;
}
