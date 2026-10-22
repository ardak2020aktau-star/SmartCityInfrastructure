package org.example;

import dsu.DSU;

public class Main {
    public static void main(String[] args) {
        DSU dsu = new DSU(4);

        dsu.union(0, 1);
        dsu.union(1, 2);

        System.out.println(dsu.find(0) == dsu.find(2));
        System.out.println(dsu.find(0) == dsu.find(3));
    }
}
