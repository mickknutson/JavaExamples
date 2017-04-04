package com.baselogic.tutorials.reference.enums;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * http://www.baeldung.com/a-guide-to-java-enums
 */
public class PizzaEnumSet {

//    private static EnumSet<PizzaStatus> undeliveredPizzaStatuses =
//            EnumSet.of(PizzaStatus.ORDERED, PizzaStatus.READY);
//
//    private PizzaStatus status;
//
//    public enum PizzaStatus {
//        ...
//    }
//
//    public boolean isDeliverable() {
//        return this.status.isReady();
//    }
//
//    public void printTimeToDeliver() {
//        System.out.println("Time to delivery is " +
//                this.getStatus().getTimeToDelivery() + " days");
//    }
//
//    public static List<Pizza> getAllUndeliveredPizzas(List<Pizza> input) {
//        return input.stream().filter(
//                (s) -> undeliveredPizzaStatuses.contains(s.getStatus()))
//                .collect(Collectors.toList());
//    }
//
//    public void deliver() {
//        if (isDeliverable()) {
//            PizzaDeliverySystemConfiguration.getInstance().getDeliveryStrategy()
//                    .deliver(this);
//            this.setStatus(PizzaStatus.DELIVERED);
//        }
//    }

    // Methods that set and get the status variable.
}
