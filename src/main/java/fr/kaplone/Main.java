package fr.kaplone;

import fr.kaplone.RandomGeneration.Complexity;

public class Main {

    public static void main(String[] args) {
        String pass = "TR<{!\"W#U`:o,&}I %5'6KXaE7ew|MdsrQ)lp";
        System.out.println(new Complexity(pass).getGains());
        System.out.println(new Complexity(pass).getScore());

    }
}