import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class SpellDmgTest {

    @Test
    public void testSpellDmgConstructor() {
        SpellDmg spell = new SpellDmg("Fuoco", "Lancia una palla di fuoco", "Mago", 5.0f, "Danno", 20.0f);
        assertNotNull(spell);
        assertEquals("Fuoco", spell.getNome());
        assertEquals("Lancia una palla di fuoco", spell.getDescrizione());
        assertEquals("Mago", spell.getClasse());
        assertEquals(5.0f, spell.getCostoMP());
        assertEquals("Danno", spell.getTipo());
        assertEquals(20.0f, spell.getValore());
    }

    @Test
    public void testApplicaEffettoSuccess() {
        SpellDmg spell = new SpellDmg("Fuoco", "Lancia una palla di fuoco", "Mago", 5.0f, "Danno", 20.0f);
        Map<String, Float> statsPg = new HashMap<>();
        statsPg.put("mp", 10.0f);
        Map<String, Float> statsNemico = new HashMap<>();
        statsNemico.put("hp", 50.0f);

        boolean result = spell.applicaEffetto(statsPg, statsNemico);
        assertTrue(result);
        assertEquals(5.0f, statsPg.get("mp")); 
        assertEquals(30.0f, statsNemico.get("hp")); 
    }

    @Test
    public void testApplicaEffettoInsufficientMana() {
        SpellDmg spell = new SpellDmg("Fuoco", "Lancia una palla di fuoco", "Mago", 15.0f, "Danno", 20.0f);
        Map<String, Float> statsPg = new HashMap<>();
        statsPg.put("mp", 10.0f);
        Map<String, Float> statsNemico = new HashMap<>();
        statsNemico.put("hp", 50.0f);

        boolean result = spell.applicaEffetto(statsPg, statsNemico);
        assertFalse(result);
        assertEquals(10.0f, statsPg.get("mp")); 
        assertEquals(50.0f, statsNemico.get("hp")); 
    }

    @Test
    public void testGetDescCombat() {
        SpellDmg spell = new SpellDmg("Fuoco", "Lancia una palla di fuoco", "Mago", 5.0f, "Danno", 20.0f);
        String desc = spell.getDescCombat();
        assertEquals("Fuoco (Costo MP: 5.0, Danno: 20.0)", desc);
    }
}