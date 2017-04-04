package com.baselogic.tutorials.comparable;

import com.baselogic.tutorials.domain.Customer;

import java.util.Comparator;

public final class CustomerComparator implements Comparator<Customer> {

    /**
     * Lambda Usage:
     *      (c1, c2) -> c1.getId().compareTo(c2.getId())
     *
     * @param c1
     * @param c2
     * @return
     */
    @Override
    public int compare(final Customer c1, final Customer c2) {

//        return c1.getId().compareTo(c2.getId());
//        return c1.getLastName().compareTo(c2.getLastName());
        return c1.getFirstName().compareTo(c2.getFirstName());

    }
}
