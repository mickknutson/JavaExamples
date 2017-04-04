package com.baselogic.tutorials.patterns.factory;

import com.baselogic.tutorials.domain.SimpleBean;

/**
 * this class implements two patterns of Singleton and of Factory, the singleton
 * factory allows to transform in singleton any other class, without changing
 * it's code.
 *
 * This version implements the lazy behavior, it generates the singleton class
 * only at the very first call of the singleton
 *
 * @author Mick Knutson
 */
public class LazySingletonFactory {

    static private LazySingletonFactory singletonFactory;

    private SimpleBean bean;

    // private constructor, we don't want the class to be instantiated from
    // others.
    private LazySingletonFactory() {
        this.bean = new SimpleBean();
    }

    /**
     * Singleton Getter
     */
    public static LazySingletonFactory getSingletonFactory() {

        if (singletonFactory == null) {

            synchronized (singletonFactory) {
                if (singletonFactory == null) {
                    singletonFactory = new LazySingletonFactory();
                }
            }
        }

        return singletonFactory;
    }

    public SimpleBean getSimpleBean() {
        return this.bean;
    }

}
