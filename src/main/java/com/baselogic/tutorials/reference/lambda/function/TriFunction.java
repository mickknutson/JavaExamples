package com.baselogic.tutorials.reference.lambda.function;

@FunctionalInterface
public interface TriFunction<T, U, V, R> {

    R accept(T t, U u, V v);

    default void foo(){
        System.err.println("not implemented");
        throw new RuntimeException("not implemented");
    }

//    List<? extends Item> foo;
    /**
     * while(it.hasNext()){
     *     it.next().remove();
     * }
     */

}