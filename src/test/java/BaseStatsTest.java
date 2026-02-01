import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class BaseStatsTest {

    @Test
    public void testBaseStatsConstructor() {
        BaseStats bs = new BaseStats("Guerriero");
        assertNotNull(bs);

    }

    @Test
    public void testGetHp() {
        BaseStats bs = new BaseStats("Guerriero");
        bs.sommaStats(10, 8, 5);
        assertEquals(10, bs.getHp());
    }

    @Test
    public void testGetAtk() {
        BaseStats bs = new BaseStats("Guerriero");
        bs.sommaStats(10, 8, 5);
        assertEquals(8, bs.getAtk());
    }

    @Test
    public void testGetMp() {
        BaseStats bs = new BaseStats("Guerriero");
        bs.sommaStats(10, 8, 5);
        assertEquals(5, bs.getMp());
    }

    @Test
    public void testCheckStats() {
        BaseStats bs = new BaseStats("Guerriero");
        assertEquals(23, bs.checkStats(10, 8, 5));
    }

    @Test
    public void testSommaStats() {
        BaseStats bs = new BaseStats("Guerriero");
        bs.sommaStats(15, 10, 7);
        assertEquals(15, bs.getHp());
        assertEquals(10, bs.getAtk());
        assertEquals(7, bs.getMp());
    }
}