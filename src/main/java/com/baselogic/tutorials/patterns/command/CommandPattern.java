package com.baselogic.tutorials.patterns.command;

import com.baselogic.tutorials.domain.Customer;

/**
 * Created by mickknutson on 4/23/15.
 */
public final class CommandPattern implements Command {

    private Customer cmdCustomer;
    private String newData;
    private String oldData;
    private String field;

    public CommandPattern(Customer contact, String field, String newData) {
        cmdCustomer = contact;
        this.newData = newData;
        this.field = field;
    }

    @Override
    public void execute() {

//        if (field == "Name") {
//            oldData = cmdCustomer.getFirstName();
//            cmdCustomer.setFirstName(newData);
//        } else if (field == "Address") {
//            oldData = cmdCustomer.getAddress();
//            cmdCustomer.setAddress(newData);
//        } else if (field == "City") {
//            oldData = cmdCustomer.getCity();
//            cmdCustomer.setCity(newData);
//        } else if (field == "State") {
//            oldData = cmdCustomer.getState();
//            cmdCustomer.setState(newData);
//        } else if (field == "Phone") {
//            oldData = cmdCustomer.getPhone();
//            cmdCustomer.setPhone(newData);
//        } else {
//            oldData = "unknown";
//        }
    }

    @Override
    public void undo() {
//        if (field == "Name") {
//            cmdCustomer.setFirstName(oldData);
//        } else if (field == "Address") {
//            cmdCustomer.setAddress(oldData);
//        } else if (field == "City") {
//            cmdCustomer.setCity(oldData);
//        } else if (field == "State") {
//            cmdCustomer.setState(oldData);
//        } else if (field == "Phone") {
//            cmdCustomer.setPhone(oldData);
//        }
    }
}

