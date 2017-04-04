package com.baselogic.tutorials.reference.enums;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PizzaTests {

    @Test
    public void givenPizzaOrder_whenReady_thenDeliverable() {

        Pizza testPz = new Pizza();
        testPz.setStatus(Pizza.PizzaStatus.READY);
        assertTrue(testPz.isDeliverable());
    }

}
