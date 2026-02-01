import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EquipTest {

    @Test
    public void testEquipConstructor() {
        BaseStats bs = new BaseStats("Guerriero");
        bs.sommaStats(10, 8, 5);
        Equip equip = new Equip(bs, "Spada", "Arma", "Guerriero", 5, 0, 0);
        assertNotNull(equip);
        assertEquals("Spada", equip.getNome());
        assertEquals("Arma", equip.getTipo());
        assertEquals("Guerriero", equip.getClasse());
        assertEquals(5, equip.getAtkBonus());
        assertEquals(0, equip.getHpBonus());
        assertEquals(0, equip.getMpBonus());
    }

    @Test
    public void testGetAtkWithBonus() {
        BaseStats bs = new BaseStats("Guerriero");
        bs.sommaStats(10, 8, 5);
        Equip equip = new Equip(bs, "Spada", "Arma", "Guerriero", 5, 0, 0);
        assertEquals(13, equip.getAtk()); // 8 + 5
    }

    @Test
    public void testGetHpWithBonus() {
        BaseStats bs = new BaseStats("Guerriero");
        bs.sommaStats(10, 8, 5);
        Equip equip = new Equip(bs, "Armatura", "Corpo", "Guerriero", 0, 10, 0);
        assertEquals(20, equip.getHp()); // 10 + 10
    }

    @Test
    public void testGetMpWithBonus() {
        BaseStats bs = new BaseStats("Guerriero");
        bs.sommaStats(10, 8, 5);
        Equip equip = new Equip(bs, "Anello", "Accessorio", "Guerriero", 0, 0, 3);
        assertEquals(8, equip.getMp()); // 5 + 3
    }
}