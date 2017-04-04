package com.baselogic.tutorials.patterns.factory;

import com.baselogic.tutorials.domain.SimpleBean;

/**
 * this class implements two patterns of Singleton and of Factory, the singleton
 * factory allows to transform in singleton any other class, without changing
 * it's code.
 *
 * This version generates the singleton class in the moment the class is loaded.
 *
 *
 * @author Mick Knutson
 */
public class SingletonFactory {

    private final static SingletonFactory singletonFactory = new SingletonFactory();

    private SimpleBean bean = null;

    // private constructor, we don't want the class to be instantiated from
    // others.
    private SingletonFactory() {
        //does nothing
    }

    public static SingletonFactory getSingletonFactory() {
        return singletonFactory;
    }



    /**
     * returns the SimpleBean object contained in the singleton, the object is created lazily
     *
     * @return   the "singletonized" class
     */
    public SimpleBean singleInstance() {

        if (this.bean == null){

            synchronized ( this ) {
                if ( this.bean == null ) {
                    this.bean = new SimpleBean();
                }
            }
        }
        return this.bean;
    }


}
