import fr.kaplone.Default;
import fr.kaplone.SeparatorEnum;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class DefaultTest {

    @Test
    public void readTest() {
        String key = "app.separator.code";
        SeparatorEnum attendu = SeparatorEnum.BELL;
        char separator = attendu.getValue();

        Map<String, String> config =  Default.readConfig();
        assertEquals(config.get(key), attendu.name());

        SeparatorEnum separatorEnum = SeparatorEnum.valueOf(config.get(key));
        assertEquals(attendu, separatorEnum);

        assertEquals(separator, separatorEnum.getValue());

    }

}