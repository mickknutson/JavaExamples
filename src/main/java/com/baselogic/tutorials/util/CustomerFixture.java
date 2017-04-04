package com.baselogic.tutorials.util;

import com.baselogic.tutorials.domain.Customer;
import com.baselogic.tutorials.domain.PhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mickknutson on 4/23/15.
 */
public class CustomerFixture {

    public static List<Customer> listOfCustomers(int capacity){

        List<Customer> customers = new ArrayList<>(capacity);

        for(int i = 0; i < capacity; i++){
            Customer c = new Customer();
            c.setFirstName(StringUtilities.getRandomString(4));
            c.setLastName(StringUtilities.getRandomString(10));

            c.setPhoneNumber(
                    randomPhoneNumber()
            );

            customers.add(c);
        }
        return customers;
    }

    public static PhoneNumber randomPhoneNumber(){

        return new PhoneNumber(
                new Random().nextInt(3),
                new Random().nextInt(3),
                new Random().nextInt(4)
        );
    }

}
