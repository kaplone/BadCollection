import org.junit.Test;
import fr.kaplone.bad.CoCollection;

import java.time.Duration;
import java.time.Instant;

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

    @Test
    public void newPerfStrings() {
        int size = 2_000;
        Instant in = Instant.now();
        CoCollection coCollection = new CoCollection(size);
        Instant out = Instant.now();
        assertTrue(Duration.between(in, out).toMillis() <  200);
        assertEquals(size, coCollection.getBadCollection().size());
    }
}