package fr.kaplone.bad;

import fr.kaplone.config.Default;

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

    public String addByRef(E elem){
        return elem.hashCode() + "";
    }

    public String getByRef(String ref){
        return null;
    }

    public void concatInPlace(BadCollection<E> other) {
        for (E e : other) {
            this.add(e);
        }
    }

    public BadCollection<E> clone(boolean deepClone) {
        BadCollection<E> tmp = new BadCollection<>();
        for (E elem : this) {
            if (deepClone) {
                //TODO
            }
            else {
                tmp.add(elem);
            }
        }
        return tmp;
    }

    public BadCollection<E> concatWith(E other) {
        BadCollection<E> tmp = this.clone(false);
        tmp.add(other);
        return tmp;
    }

    public BadCollection<E> concatWith(BadCollection<E> others) {
        BadCollection<E> tmp = this.clone(false);
        for (E e : others) {
            tmp.add(e);
        }
        return tmp;
    }

    public void addAsHead(E elem){
        this.state = this.state.isEmpty() ? elem.toString() : elem.toString() + SEPARATOR + this.state;
    }

    public BadCollection<E> reverse(){
        return null;
    }

    public void reverseInPlace(){
        if (this.size() > 1){
            BadCollection<E> tempBc = new BadCollection<>();
            for (E elem : this){
                tempBc.addAsHead(elem);
            }
            this.state = tempBc.state;
        }
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

    public BadCollection<E> take(int n){
        return state.isEmpty() ? this : keepWhile(this, n, new BadCollection<>());
    }

    private BadCollection<E> keepWhile(BadCollection<E> current, int n, BadCollection<E> acc){
        if (n == 0 || current.isEmpty()) return acc;
        else return keepWhile(current.tail(), n - 1, acc.concatWith(current.head()));
    }

    public BadCollection<E> skip(int n){
        return null;
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
