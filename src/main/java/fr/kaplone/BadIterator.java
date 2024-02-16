package fr.kaplone;

import java.util.Iterator;

public class BadIterator<E> implements Iterator<E> {

    private final static char SEPARATOR = Default.SEPARATOR;
    private String state;

    public BadIterator(String state) {
        this.state = state;
    }

    @Override
    public boolean hasNext() {
        return !this.state.replaceAll(SEPARATOR + "", "").isEmpty();
    }

    @Override
    public E next() {
        int idx = state.indexOf(SEPARATOR);
        String s;
        if (idx > -1){
            s = state.substring(0, idx);
            state = state.substring(idx + 1);
        }
        else {
            s = state;
            state = "";
        }
        return (E) s;
    }
}
