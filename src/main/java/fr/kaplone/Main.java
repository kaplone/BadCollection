package fr.kaplone;

import fr.kaplone.RandomGeneration.Complexity;
import fr.kaplone.bad.BadCollection;
import fr.kaplone.bad.CoCollection;
import fr.kaplone.model.Role;
import fr.kaplone.model.User;

import java.util.*;

public class Main {


    static User user;

    public static void main(String[] args) {

        CoCollection cc = new CoCollection(100);
        List<String> ls = cc.getList();

        ls.stream().map(Complexity::new)
                .max(Comparator.comparing(Complexity::getScore))
                .map(a -> {
                    System.out.println("Max:: " + a.getValue() + " --> " + a.getScore());
                    return Optional.of(a);
                });

        ls.stream().map(Complexity::new)
                .min(Comparator.comparing(Complexity::getScore))
                .map(a -> {
                    System.out.println("Min:: " + a.getValue() + " --> " + a.getScore());
                    return Optional.of(a);
                });

        System.out.println("---------------------------");

        BadCollection<String> bc = cc.getBadCollection();

        bc.max(Comparator.comparing(s -> new Complexity(s).getScore()))
                .map(a -> {
                    System.out.println("Max:: " + a + " --> " + new Complexity(a).getScore());
                    return Optional.of(a);
                });

        bc.min(Comparator.comparing(s -> new Complexity(s).getScore()))
                .map(a -> {
                    System.out.println("Min:: " + a + " --> " + new Complexity(a).getScore());
                    return Optional.of(a);
                });

    }


}