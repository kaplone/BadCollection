package fr.kaplone.bad;

public class BadReference<E> {

    public String write(E elem){
        String address = "";

        return address;
    }

    public E read(String s){
        E elem = (E) s;

        return elem;
    }
}
