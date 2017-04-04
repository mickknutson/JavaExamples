package com.baselogic.tutorials.domain;

/**
 * Created by mickknutson on 4/28/15.
 */
public final class Account {

    private double balance;

    public Account(double initialBalance) {
        balance = initialBalance;
    }

    public void deposit(double amount) {
        // balance += amount;
        double tempBalance = balance;
        tempBalance = tempBalance + amount;
        balance = tempBalance;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
