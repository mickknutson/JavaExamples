package com.baselogic.tutorials.reference.reflect;

import com.baselogic.tutorials.domain.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by mickknutson on 4/23/15.
 */
@RunWith(JUnit4.class)
public class ReflectionExamplesTests {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionExamplesTests.class);


    //-----------------------------------------------------------------------//
    // Lifecycle Methods
    //-----------------------------------------------------------------------//
    @Before
    public void beforeEachTest() throws Exception {
    }

    @After
    public void afterEachTest() throws Exception {
    }

    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//
    @Test
    public void createObjectTests() throws Exception {

        Class clazz = Customer.class;

        Class[] parmTypes = { Long.class, String.class, String.class, String.class };

        Constructor constructor = null;

        try {
            constructor = clazz.getConstructor(parmTypes);
        } catch (NoSuchMethodException nsme) {
            logger.error("exception: {}", nsme);
        }

        Object[] parms = new Object[4];
        parms[0] = 42L;
        parms[1] = "Mick";
        parms[2] = "Knutson";
        parms[3] = "mickknutson@gmail.com";

        try {
            Customer p = (Customer) constructor.newInstance(parms);
            logger.info(" : {}", p);
        } catch (InstantiationException ie) {
            logger.error("Instantiation Exception " + ie);
        } catch (InvocationTargetException ie) {
            logger.error("Invocation Target Exception " + ie);
        } catch (IllegalAccessException ie) {
            logger.error("Illegal Access Exception " + ie);
        }

    }

    @Test
    public void createCustomerTests(){
        Class clazz = Customer.class;
        Class[] parmTypes = { Long.class, String.class, String.class, String.class };

        try {
            Constructor constructor = clazz.getConstructor(parmTypes);

            Object[] parms = { 42L, "Mick", "Knutson2", "mickknutsonyahoo.com" };
            Customer customer = (Customer) constructor.newInstance(parms);
            logger.info(" : {}", customer);

        } catch (InvocationTargetException ite) {
            logger.info("The invoked method threw an exception: "
                    + ite.getCause().getClass());
            ite.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test(expected = IllegalArgumentException.class)
    public void createCustomerTests_InvocationTargetException() throws Exception{

        Class clazz = Customer.class;

        Class[] parmTypes = { Long.class, String.class, String.class, String.class };

        try {
            @SuppressWarnings("rawtypes")
            Constructor constructor = clazz.getConstructor(parmTypes);

            Object[] parms = { "42L", "Mick", "Knutson2", "mickknutson@live.com" };

            Customer customer = (Customer) constructor.newInstance(parms);
//            fail();
        } catch (InvocationTargetException ite) {
            logger.info("The invoked method threw an exception: "
                    + ite.getCause().getClass());
            ite.printStackTrace();
            throw ite;
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            throw iae;
        }

    }

} // the end...
