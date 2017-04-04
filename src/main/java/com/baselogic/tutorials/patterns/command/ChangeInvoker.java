package com.baselogic.tutorials.patterns.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by mickknutson on 4/24/15.
 */
public class ChangeInvoker {

    private static final Logger logger = LoggerFactory.getLogger(ChangeInvoker.class);

    // add variable to hold the commands
    private ArrayList<Command> changeCmds = new ArrayList<Command>();

    public ChangeInvoker() {
        logger.info("Invoker created");
    }

    public int setCommand(Command changeCommand) {

        logger.info("Command added to invoker");
        changeCmds.add(changeCommand);
        return changeCmds.indexOf(changeCommand);

    }

    public void contactChanged(int var) {
        changeCmds.get(var).execute();
    }

    public void contactChangeUndone(int var) {
        changeCmds.get(var).undo();
    }

}
