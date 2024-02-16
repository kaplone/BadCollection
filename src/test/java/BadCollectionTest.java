import fr.kaplone.BadCollection;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
    public void removeAll_01() {
        BadCollection<String> lc = new BadCollection<>();
        lc.add("One");
        lc.add("Two");
        lc.add("Three");
        lc.add("One");
        lc.add("Four");
        lc.add("One");

        lc.removeAll("One");

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
        lc.add("OneAndHalf");
        lc.add("Two");
        lc.add("Three");
        lc.add("Four");
        lc.add("FourPlusOne");
        lc.add("One");

        lc.removeAll("One");

        Iterator<String> it = lc.iterator();

        StringBuilder result = new StringBuilder();

        while (it.hasNext()){
            result.append(it.next()).append("\n");
        }

        assertEquals("OneAndHalf\nTwo\nThree\nFour\nFourPlusOne\n", result.toString());
    }

    @Test
    public void sizeOfEmpty(){
        BadCollection<String> lc = new BadCollection<>();
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


}