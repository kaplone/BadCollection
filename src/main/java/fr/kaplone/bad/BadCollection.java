package fr.kaplone.bad;

import fr.kaplone.config.Default;
import fr.kaplone.config.SeparatorEnum;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
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

    public void concatInPlace(BadCollection<E> other) {
        for (E e : other) {
            this.add(e);
        }
    }

    public boolean contains(){
        return false;
    }

    public BadCollection<E> fillWith(E elem, int nb){
        return new BadCollection<>();
    }

    public BadCollection<E> clone(boolean deepClone) {
        BadCollection<E> tmp = new BadCollection<>();
        for (E elem : this) {
            if (deepClone) {
                //TODO with generics
            }
            else {
                System.out.println(elem);
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
        this.state = this.state.isEmpty() ? elem.toString() : this.state + SEPARATOR + elem.toString();
    }

    public BadCollection<E> reverse(){
        BadCollection<E> tempBc = new BadCollection<>();
        for (E elem : this){
            tempBc.addAsHead(elem);
        }
        return tempBc;
    }

    public void reverseInPlace(){
        if (this.size() > 1){
            BadCollection<E> tempBc = new BadCollection<>();
            for (E elem : this){
                tempBc.addAsHead(elem);
            }
            System.out.println(tempBc.state);
        }
    }

    public int size() {
        int i = -1;

        for(String s : state.split(SEPARATOR + "")){
            if (!s.isEmpty()) i ++;
        }
        return i;
    }

    public boolean isEmpty(){
        return state.isEmpty();
    }

    public BadCollection<E> filter(Predicate<E> predicate){
        BadCollection<E> lc = new BadCollection<>();
        Iterator<E> it = this.iterator();
        while (it.hasNext()){
            it.next();
        }
        return lc;
    }

    public void filterInPlace(Predicate<E> predicate){
        Iterator<E> it = this.iterator();
        while (it.hasNext()){
            it.next();
        }
    }

    public void removeInPlace(E value){
        if (this.state.contains(value.toString() + SEPARATOR)) {
            this.state += this.state.replace(value.toString() + SEPARATOR, "");
        }

        if (this.state.contains(SEPARATOR + value.toString())) {
            this.state += this.state.replace(SEPARATOR + value.toString(), "");
        }

        if (this.state.contains(value.toString())) {
            this.state += this.state.replace(value.toString(), "");
        }
    }



    public Optional<E> head(){
        return state.isEmpty() ? Optional.empty() : Optional.of(this.iterator().next());
    }

    public BadCollection<E> tail(){
        return state.isEmpty() ? this : this.allNext();
    }

    public BadCollection<E> take(int n){
        return state.isEmpty() ? this : keepWhile(this, n, new BadCollection<>());
    }

    private BadCollection<E> keepWhile(BadCollection<E> current, int n, BadCollection<E> acc){
        if (n == 0 || current.isEmpty()) return current;
        else return keepWhile(current.tail(), n - 1, acc.concatWith(current.head().orElse(null)));
    }

    public BadCollection<E> skip(int n){
        return skipWhile(this, n);
    }

    private BadCollection<E> skipWhile(BadCollection<E> current, int n){
        if (n == 0 || current.isEmpty()) return current;
        else return skipWhile(current.tail(), n - 1);
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

    public void removeAllInPlace(E value){
        if (this.state.startsWith(value.toString() + SEPARATOR)) {
            this.state = this.state.replaceFirst(value.toString() + SEPARATOR, "");
        }

        if (this.state.endsWith(SEPARATOR + value.toString())) {
            this.reverseInPlace();
            this.state = this.state.replaceFirst(value.toString() + SEPARATOR, "");
            this.reverseInPlace();
        }

        if (this.state.contains(SEPARATOR + value.toString() + SEPARATOR)) {
            this.state = this.state.replaceAll(SEPARATOR + value.toString() + SEPARATOR, SEPARATOR + "");
        }

        if (this.state.equals(value.toString())) {
            this.state = "";
        }
    }

    public <F> BadCollection<F> map(Function<? super E, ? extends F> mapper){
        BadCollection<F> tmp = new BadCollection<>();
        for (E elem : this){
            tmp.add(mapper.apply(elem));
        }

        return tmp;
    }

    public BadCollection<E> sorted(Comparator<? super E> comparator){
        BadCollection<E> sorted = new BadCollection<>();
        for (E elem : this){
            Iterator<E> it = sorted.iterator();
            int idx = 0;
            if (it.hasNext()){
                E next = it.next();
                while (comparator.compare(elem, next) > 0){
                    idx++;
                    if (it.hasNext()){
                        next = it.next();
                    }
                    else {
                        throw new NoSuchElementException("Fin d'itérateur");
                    }
                }
            }


            sorted = sorted.addAt(idx, elem);
        }
        return sorted;
    }

    public BadCollection<E> addAt(int idx, E elem){
        return slice(0, idx).concatWith(elem).concatWith(slice(idx, this.size()));
    }

    public BadCollection<E> slice(int from, int to){
        return this.skip(from).take(from - to);
    }

    public Optional<E> min(Comparator<? super E> comparator){
        return this.sorted(comparator).head();
    }

    public Optional<E> max(Comparator<? super E> comparator){
        return this.sorted(comparator).head();
    }


    public String affBadCollection(){
        return String.join(SeparatorEnum.END_OF_TEXT + "", this.map(Object::toString));
    }
}
