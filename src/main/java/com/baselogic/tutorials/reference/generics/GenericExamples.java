package com.baselogic.tutorials.reference.generics;

import com.baselogic.tutorials.reference.interfaces.MyChild;
import com.baselogic.tutorials.reference.interfaces.MyParent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mickknutson on 1/10/17.
 */
public class GenericExamples {

    public void erasure(){
        List<? extends MyParent> parent = new ArrayList<MyChild>();
    }
}
