package com.baselogic.tutorials.domain;

import com.baselogic.tutorials.domain.enums.Activity;

/**
 * Customer
 * <p/>
 * <p>Spring Certification objective: 1.2 Lifecycle</p>
 *
 * @author Mick Knutson
 * @see <a href="http://springcert.sourceforge.net/core-3/index.html#beans">Objective 1.2 Lifecycle</a>
 * @see <a href="http://www.baselogic.com">Blog: http://baselogic.com</a>
 * @see <a href="http://linkedin.com/in/mickknutson">LinkedIN: http://linkedin.com/in/mickknutson</a>
 * @see <a href="http://twitter.com/mickknutson">Twitter: http://twitter.com/mickknutson</a>
 * @see <a href="http://github.com/mickknutson">Git hub: http://github.com/mickknutson</a>
 * @see <a href="http://www.packtpub.com/java-ee6-securing-tuning-extending-enterprise-applications-cookbook/book">JavaEE 6 Cookbook Packt</a>
 * @see <a href="http://www.amazon.com/Cookbook-securing-extending-enterprise-applications/dp/1849683166">JavaEE 6 Cookbook Amazon</a>
 * @since 2012
 */
public class Customer extends User { // JavaBean (Java) or POJO (Java) or Bean (Spring)

    private Order order;
    private Address address;
    private PhoneNumber phoneNumber;
    private Activity activity;

    public Customer() {}

    public Customer(Long id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address Address) {
        this.address = address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}
