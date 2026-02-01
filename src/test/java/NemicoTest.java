import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class NemicoTest {

    @Test
    public void testNemicoConstructorWithStats() {
        Nemico nemico = new Nemico("Goblin", "goblin", 20.0f, 5.0f, 3.0f);
        assertNotNull(nemico);
        assertEquals("Goblin", nemico.getNome());
        assertEquals("goblin", nemico.getClasse());

        Map<String, Float> stats = nemico.getStats();
        assertEquals(20.0f, stats.get("hp"));
        assertEquals(5.0f, stats.get("atk"));
        assertEquals(3.0f, stats.get("mp"));
    }

    @Test
    public void testNemicoConstructorWithoutStats() {

        Nemico nemico = new Nemico("Orco", "boss");
        assertNotNull(nemico);
        assertEquals("Orco", nemico.getNome());
        assertEquals("boss", nemico.getClasse());

        Map<String, Float> stats = nemico.getStats();
        assertTrue(stats.isEmpty()); 
    }
}