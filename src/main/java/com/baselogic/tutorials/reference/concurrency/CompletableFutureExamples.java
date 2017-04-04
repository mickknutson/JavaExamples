package com.baselogic.tutorials.reference.concurrency;

import java.sql.Statement;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureExamples {

    Statement s;

    CompletableFuture<String> f1;
    CompletableFuture<String> f2;
    CompletableFuture<String> f3;
    CompletableFuture<String> f4;

    public Double foo(){

        Long l;

        // some operation to start CF.
        f1 = new CompletableFuture<>();
        f2 = new CompletableFuture<>();
        f3 = new CompletableFuture<>();
        f4 = new CompletableFuture<>();

        return 1234.5;
    }


    public synchronized static Long foo(Long l){

        Long l2 = 1234L;

//        l;


        // some operation to start CF.
//        f1 = new CompletableFuture<>();
//        f2 = new CompletableFuture<>();
//        f3 = new CompletableFuture<>();
//        f4 = new CompletableFuture<>();

        return l2;
    }

    public void handleFutures(){}

    // thenAccept
    // thenApply
    // thenCompose
    // thenCombine

}
