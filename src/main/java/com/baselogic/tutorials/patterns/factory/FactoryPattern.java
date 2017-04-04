package com.baselogic.tutorials.patterns.factory;

import com.baselogic.tutorials.patterns.command.*;
import com.baselogic.tutorials.domain.Address;
import com.baselogic.tutorials.domain.Customer;

public final class FactoryPattern {

    public static Customer staticInstance(String firstName,
                                          String lastName){

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        Address address = new Address();
        address.setCity("Park City");
        address.setState("Utah");
        customer.setAddress(address);

        return customer;
    }

    public Customer instance(String firstName,
                             String lastName){

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        Address address = new Address();
        address.setCity("Park City");
        address.setState("Utah");
        customer.setAddress(address);

        return customer;
    }





    public static <T> T genericStaticInstance(Class<T> clazz) {

        Command item = null;

        if (clazz == FooCommand.class) {
            item = new FooCommandImpl();

        } else if (clazz == BarCommand.class) {
            item = new BarCommandImpl();
        }

        return (T) item;
    }


}

