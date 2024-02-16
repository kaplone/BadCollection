import org.junit.Test;
import utils.CoCollection;

import static org.junit.Assert.*;

public class CoCollectionTest {

    @Test
    public void newStringEmpty() {
        CoCollection coCollection = new CoCollection(0);
        assertEquals(0, coCollection.getBadCollection().size());
        assertEquals(coCollection.getBadCollection().size(), coCollection.getBadCollection().size());
        assertTrue(coCollection.getBadCollection().isEmpty());
        assertEquals(coCollection.affBadCollection(), coCollection.affList());
    }


    @Test
    public void newString() {
        CoCollection coCollection = new CoCollection(10);
        assertEquals(10, coCollection.getBadCollection().size());
        assertEquals(coCollection.getBadCollection().size(), coCollection.getBadCollection().size());
        assertFalse(coCollection.getBadCollection().isEmpty());
        assertEquals(coCollection.affBadCollection(), coCollection.affList());
    }
}