package com.baselogic.tutorials.patterns.adapter;

import com.baselogic.tutorials.domain.Customer;
import com.baselogic.tutorials.patterns.command.ChangeInvoker;
import com.baselogic.tutorials.patterns.command.Command;
import com.baselogic.tutorials.patterns.command.CommandPattern;
import com.baselogic.tutorials.patterns.factory.FactoryPattern;
import com.baselogic.tutorials.reference.HashcodeExamples;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mickknutson on 4/23/15.
 */
@RunWith(JUnit4.class)
public class AdapterPatternTests {
    private static final Logger logger = LoggerFactory.getLogger(AdapterPatternTests.class);

    private HashcodeExamples hce = new HashcodeExamples();

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
    public void commandPatternTest() throws Exception {

        Customer joeContact = FactoryPattern.staticInstance("Mick", "Knutson");


        logger.info("Our contact before making the change: ");
        logger.info(joeContact.toString());

        logger.info("Create the invoker to store the commands.");
        ChangeInvoker invoker = new ChangeInvoker();

        Command change = new CommandPattern(joeContact, "Address", "922 Wolfbane Road");
        logger.info("Add the change command to the invoker and capture the index location.");

        int i = invoker.setCommand(change);


        logger.info("");
        logger.info("Executing our contact change.");
        logger.info("");
        invoker.contactChanged(i);


        logger.info("");
        logger.info("Our contact after the change. ");
        logger.info("");
        logger.info(joeContact.toString());
        logger.info("");
        logger.info("");
        logger.info("Having a change of heart on the change.");
        logger.info("");

        invoker.contactChangeUndone(i);
        logger.info("");
        logger.info("Our contact after the change of heart. ");
        logger.info("");
        logger.info(joeContact.toString());


    }

}
