package fr.kaplone;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public class BadCollection<E> implements Iterable<E> {

    private final static char SEPARATOR = Default.SEPARATOR;
    private String state = "";

    public BadCollection(){}

    @Override
    public Iterator<E> iterator() {
        return new BadIterator<>(state);
    }

    public void add(E value) {
        this.state += this.state.isEmpty() ? value.toString() : SEPARATOR + value.toString();
    }

    public BadCollection<E> concat(BadCollection<E> other) {
        for (E e : other) {
            this.add(e);
        }
        return null;
    }

    public BadCollection<E> reverse(){
        return null;
    }

    public int size() {
        return (int) Arrays.stream(state.split(SEPARATOR + "")).filter(s -> !s.isEmpty()).count();
    }

    public boolean isEmpty(){
        return state.isEmpty();
    }

    public BadCollection<E> filter(Predicate<E> predicate){
        BadCollection<E> lc = new BadCollection<>();
        Iterator<E> it = this.iterator();
        while (it.hasNext()){
            E elem = it.next();
            if (predicate.test(elem)){
                lc.add(elem);
            }
        }
        return lc;
    }



    public E head(){
        return state.isEmpty() ? null : this.iterator().next();
    }

    public BadCollection<E> tail(){
        return state.isEmpty() ? this : this.allNext();
    }

    private BadCollection<E> allNext(){
        BadCollection<E> lc = new BadCollection<>();
        Iterator<E> it = this.iterator();
        it.next();
        while (it.hasNext()){
            lc.add(it.next());
        }
        return lc;
    }

    public void removeAll(E value){
        if (this.state.contains(value.toString() + SEPARATOR)) {
            this.state = this.state.replaceAll(value.toString() + SEPARATOR, "");
        }

        if (this.state.contains(SEPARATOR + value.toString())) {
            this.state = this.state.replaceAll(SEPARATOR + value.toString(), "");
        }
    }

    public String affBadCollection(){
        return String.join("\n", (BadCollection<String>)this);
    }
}
