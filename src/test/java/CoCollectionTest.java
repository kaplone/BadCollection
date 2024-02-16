import org.junit.Test;

import static org.junit.Assert.*;

public class CoCollectionTest {

    @Test
    public void newStringEmpty() {
        CoCollection coCollection = new CoCollection(0);
        assertEquals(0, coCollection.badCollection.size());
        assertEquals(coCollection.getBadCollection().size(), coCollection.badCollection.size());
        assertTrue(coCollection.getBadCollection().isEmpty());
        assertEquals(coCollection.affBadCollection(), coCollection.affList());
    }


    @Test
    public void newString() {
        CoCollection coCollection = new CoCollection(10);
        assertEquals(10, coCollection.badCollection.size());
        assertEquals(coCollection.getBadCollection().size(), coCollection.badCollection.size());
        assertFalse(coCollection.getBadCollection().isEmpty());
        assertEquals(coCollection.affBadCollection(), coCollection.affList());
    }
}