package org.netforklabs.librakit.configuration;

import java.util.ArrayList;
import java.util.List;

public class JT {

    public static void printstr(Object... args)
    {
        System.out.println(args);
    }

    public static void main(String[] args) {
        List<String> vargs = new ArrayList<>();

        vargs.add("Hello");
        vargs.add("World");

        printstr(vargs.toArray());
    }

}
