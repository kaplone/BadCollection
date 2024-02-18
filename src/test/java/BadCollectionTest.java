import fr.kaplone.RandomGeneration.Complexity;
import fr.kaplone.bad.BadCollection;
import org.junit.Assert;
import org.junit.Test;
import fr.kaplone.bad.CoCollection;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class BadCollectionTest {

    @Test
    public void empty() {
        BadCollection<String> lc = new BadCollection<>();

        Iterator<String> it = lc.iterator();

        StringBuilder result = new StringBuilder();

        while (it.hasNext()){
            result.append(it.next()).append("\n");
        }

        assertTrue(result.toString().isEmpty());
    }

    @Test
    public void add() {
        BadCollection<String> lc = new BadCollection<>();
        lc.add("One");
        lc.add("Two");
        lc.add("Three");
        lc.add("One");
        lc.add("Four");

        Iterator<String> it = lc.iterator();

        StringBuilder result = new StringBuilder();

        while (it.hasNext()){
            result.append(it.next()).append("\n");
        }

        assertEquals("One\nTwo\nThree\nOne\nFour\n", result.toString());
    }

    @Test
    public void removeAll_00() {
        BadCollection<String> lc = new BadCollection<>();
        lc.add("One");
        lc.removeAllInPlace("One");
        assertEquals(0, lc.size());
    }

    @Test
    public void removeAll_01() {
        BadCollection<String> lc = new BadCollection<>();
        lc.add("One");
        lc.add("Two");
        lc.add("Three");
        lc.add("One");
        lc.add("Four");
        lc.add("One");

        lc.removeAllInPlace("One");

        Iterator<String> it = lc.iterator();

        StringBuilder result = new StringBuilder();

        while (it.hasNext()){
            result.append(it.next()).append("\n");
        }

        assertEquals("Two\nThree\nFour\n", result.toString());
    }

    @Test
    public void removeAll_02() {
        BadCollection<String> lc = new BadCollection<>();
        lc.add("One");
        lc.add("OneAndQuarter");
        lc.add("OneAndHalf");
        lc.add("Two");
        lc.add("Three");
        lc.add("Four");
        lc.add("FourPlusOne");
        lc.add("One");

        lc.removeAllInPlace("One");

        Iterator<String> it = lc.iterator();

        StringBuilder result = new StringBuilder();

        while (it.hasNext()){
            result.append(it.next()).append("\n");
        }

        assertEquals("OneAndQuarter\nOneAndHalf\nTwo\nThree\nFour\nFourPlusOne\n", result.toString());
    }

    @Test
    public void reverseInPlace(){
        CoCollection lc = new  CoCollection(15);
        lc.getBadCollection().reverseInPlace();
        Collections.reverse(lc.getList());
        assertEquals(15, lc.getBadCollection().size());
        assertEquals(lc.affBadCollection(), lc.affList());
    }

    @Test
    public void reverseInPlace2(){
        CoCollection lc = new  CoCollection(15);
        lc.getBadCollection().reverseInPlace();
        lc.getBadCollection().reverseInPlace();
        assertEquals(15, lc.getBadCollection().size());
        assertEquals(lc.affBadCollection(), lc.affList());
    }

    @Test
    public void sizeOfEmpty(){
        BadCollection<String> lc = new BadCollection<>();
        assertEquals(0, lc.size());
    }

    @Test
    public void sizeUpAndDownEmpty(){
        BadCollection<String> lc = new BadCollection<>();
        lc.add("UP");
        assertEquals(1, lc.size());
        lc.removeInPlace("UP");
        lc.affBadCollection();
        assertEquals(0, lc.size());
    }

    @Test
    public void sizeOfEmptyCoCollection(){
        BadCollection<String> lc = new CoCollection(0).getBadCollection();
        assertEquals(0, lc.size());
    }

    @Test
    public void sizeOfOne(){
        BadCollection<String> lc = new  CoCollection(1).getBadCollection();
        assertEquals(1, lc.size());
    }

    @Test
    public void sizeOfOneHundred(){
        CoCollection lc = new  CoCollection(100);
        Assert.assertEquals(100, lc.getBadCollection().size());
        Assert.assertEquals(lc.getBadCollection().size(), lc.getList().size());
        assertEquals(lc.affBadCollection(), lc.affList());
    }

    @Test
    public void concatInPlace() {
        CoCollection lc = new  CoCollection(100);
        CoCollection lc2 = new  CoCollection(15);
        lc.getBadCollection().concatInPlace(lc2.getBadCollection());
        lc.getList().addAll(lc2.getList());
        assertEquals(115,  lc.getBadCollection().size());
        assertEquals(lc.affBadCollection(), lc.affList());
    }

    @Test
    public void mapToupper(){
        CoCollection lc = new  CoCollection(100);
        assertEquals(lc.getBadCollection().map(String::toUpperCase).affBadCollection(),
                     lc.getList().stream().map(String::toUpperCase).collect(Collectors.joining("\n")));
    }

    @Test
    public void concatWith() {
        String elem = "ABCDE";
        CoCollection lc = new CoCollection(100);
        BadCollection<String> nouvelleBc = lc.getBadCollection().concatWith(elem);
        List<String> nouvelleC = new ArrayList<>(lc.getList());
        nouvelleC.add(elem);
        assertEquals(100, lc.getBadCollection().size());
        assertEquals(101, nouvelleBc.size());
        assertEquals(nouvelleBc.affBadCollection(), String.join("\n", nouvelleC));
    }

    @Test
    public void take_0_0(){
        CoCollection lc = new CoCollection(0);
        assertEquals(lc.getBadCollection().take(0).affBadCollection(),
                lc.getList().stream().limit(0).collect(Collectors.joining("\n")));
    }

    @Test
    public void take_100_10(){
        CoCollection lc = new CoCollection(100);
        assertEquals(lc.getBadCollection().take(10).affBadCollection(),
                lc.getList().stream().limit(10).collect(Collectors.joining("\n")));
    }

    @Test
    public void take_10_100(){
        CoCollection lc = new CoCollection(10);
        assertEquals(lc.getBadCollection().take(100).affBadCollection(),
                lc.getList().stream().limit(100).collect(Collectors.joining("\n")));
    }

    @Test
    public void quotesTest(){
        BadCollection<String > lc = new BadCollection<>();
        String quotes = '"' + "";
        String multiQuotes = quotes+quotes+quotes+quotes+quotes+quotes+quotes+quotes;
        lc.add(multiQuotes);
        System.out.println(lc.affBadCollection());
        List<String> list = new ArrayList<>();
        list.add(multiQuotes);
        String affList = String.join("\n", list);
        System.out.println(affList);
        assertEquals(affList, lc.affBadCollection());
    }

    @Test
    public void escapeTest(){
        BadCollection<String > lc = new BadCollection<>();
        String escape = '\\' + "";
        String doubleEscape = escape + escape;
        String tripleEscape = escape + escape + escape;
        lc.add(escape);
        lc.add(doubleEscape);
        lc.add(tripleEscape);
        System.out.println(lc.affBadCollection());
        List<String> list = new ArrayList<>();
        list.add(escape);
        list.add(doubleEscape);
        list.add(tripleEscape);
        String affList = String.join("\n", list);
        System.out.println(affList);
        assertEquals(affList, lc.affBadCollection());
    }

    @Test
    public void skip_0_0(){
        CoCollection lc = new CoCollection(0);
        assertEquals(lc.getBadCollection().skip(0).affBadCollection(),
                lc.getList().stream().skip(0).collect(Collectors.joining("\n")));
    }

    @Test
    public void skip_0_10(){
        CoCollection lc = new CoCollection(0);
        assertEquals(lc.getBadCollection().skip(10).affBadCollection(),
                lc.getList().stream().skip(10).collect(Collectors.joining("\n")));
    }

    @Test
    public void skip_10_0(){
        CoCollection lc = new CoCollection(10);
        assertEquals(lc.getBadCollection().skip(0).affBadCollection(),
                lc.getList().stream().skip(0).collect(Collectors.joining("\n")));
    }

    @Test
    public void skip_100_11(){
        CoCollection lc = new CoCollection(100);
        assertEquals(89, lc.getBadCollection().skip(11).size());
        assertEquals(lc.getBadCollection().skip(11).affBadCollection(),
                lc.getList().stream().skip(11).collect(Collectors.joining("\n")));
    }

    @Test
    public void removeOne(){
        CoCollection lc = new CoCollection(100);
        BadCollection<String> toRemove = lc.getBadCollection()
                .filter(e -> e.contains("8"))
                .skip(3)
                .take(1);
        if(!toRemove.isEmpty()){
            lc.getBadCollection().removeInPlace(toRemove.head().get());
        }


        List<String> toRemoveL = lc.getList().stream()
                .filter(e -> e.contains("8"))
                .skip(3)
                .limit(1)
                .collect(Collectors.toList());
        lc.getList().remove(toRemoveL.get(0));

        assertEquals(99, lc.getBadCollection().size());
        assertEquals(lc.affBadCollection(), lc.affList());
    }

    @Test
    public void filterInPlace(){
        CoCollection lc = new CoCollection(100);

        lc.getBadCollection().filterInPlace(e -> e.contains("4"));
        lc.getList().removeIf(e -> e.contains("4"));

        assertTrue(100 > lc.getBadCollection().size());
        assertEquals(lc.affList(), lc.affBadCollection());

    }

    @Test
    public void filterTest(){
        int size = 2743;
        CoCollection coCollection = new CoCollection(size);
        int position = coCollection.getList().get(0).length() / 2;
            String keep = coCollection.getList().get(size/2).substring(position, position + 1);
        System.out.println(keep);
        System.out.println(coCollection.getList().stream().filter(e -> e.contains(keep)).collect(Collectors.joining("\n", "-----------\n", "\n-----------")));
        //System.out.println(coCollection.getBadCollection().filter(e -> e.contains(keep)).affBadCollection());
        System.out.println(coCollection.getList().stream().filter(e -> e.contains(keep)).count());
        Assert.assertEquals(coCollection.getList().stream().filter(e -> e.contains(keep)).count(),
                     coCollection.getBadCollection().filter(e -> e.contains(keep)).size());
    }

    @Test
    public void testSortedByComplexity(){
        CoCollection cc = new CoCollection(100);
        List<String> ls = cc.getList();

        String resMax1 = ls.stream().map(Complexity::new)
                .max(Comparator.comparing(Complexity::getScore))
                .map(a ->  "Max:: " + a.getValue() + " --> " + a.getScore()).get();

        String resMin1 = ls.stream().map(Complexity::new)
                .min(Comparator.comparing(Complexity::getScore))
                .map(a -> "Min:: " + a.getValue() + " --> " + a.getScore()).get();

        System.out.println("---------------------------");

        BadCollection<String> bc = cc.getBadCollection();

        String resMax2 = bc.max(Comparator.comparing(s -> new Complexity(s).getScore()))
                .map(a -> "Max:: " + a + " --> " + new Complexity(a).getScore()).get();

        String resMin2 = bc.min(Comparator.comparing(s -> new Complexity(s).getScore()))
                .map(a -> "Min:: " + a + " --> " + new Complexity(a).getScore()).get();

        assertEquals(resMax1, resMax2);
        assertEquals(resMin1, resMin2);

        System.out.println(resMax2);
        System.out.println(resMin2);
    }

    @Test
    public void chaining(){
        CoCollection lc = new CoCollection(100);

        BadCollection<String> nouvelleBc = lc.getBadCollection()
                .filter(e -> e.contains("7"))
                .take(5)
                .concatWith(lc.getBadCollection().filter(e -> e.contains("Y")).take(5))
                .map(e -> e.replaceAll("Y", "+-+").replaceAll("7", "-+-"));

        List<String> nouvelleC = Stream.concat(lc.getList()
                        .stream()
                        .filter(e -> e.contains("7"))
                        .limit(5),
                lc.getList()
                        .stream()
                        .filter(e -> e.contains("Y"))
                        .limit(5))
                .map(e -> e.replaceAll("Y", "+-+").replaceAll("7", "-+-"))
                .collect(Collectors.toList());

        assertEquals(nouvelleBc.affBadCollection(), String.join("\n", nouvelleC));
    }
}