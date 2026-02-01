import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QuestTest {

    @Test
    public void testQuestConstructor() {
        BaseStats bs = new BaseStats("Guerriero");
        bs.sommaStats(10, 8, 5);
        Equip premio = new Equip(bs, "Spada Magica", "Arma", "Guerriero", 10, 0, 0);
        ObiettivoKill obiettivo = new ObiettivoKill("Orco", 5);
        Quest quest = new Quest("Uccidi Orchi", "Sconfiggi 5 Orchi", obiettivo, premio);

        assertNotNull(quest);
        assertEquals("Uccidi Orchi", quest.getTitolo());
        assertEquals("Sconfiggi 5 Orchi", quest.getDescrizione());
        assertEquals(premio, quest.getPremio());
        assertFalse(quest.isCompletata());
        assertFalse(quest.isPremioRiscattato());
    }

    @Test
    public void testAggiornaQuest() {
        BaseStats bs = new BaseStats("Guerriero");
        bs.sommaStats(10, 8, 5);
        Equip premio = new Equip(bs, "Spada Magica", "Arma", "Guerriero", 10, 0, 0);
        ObiettivoKill obiettivo = new ObiettivoKill("Orco", 2);
        Quest quest = new Quest("Uccidi Orchi", "Sconfiggi 2 Orchi", obiettivo, premio);

        quest.aggiorna("Orco");
        assertFalse(quest.isCompletata());

        quest.aggiorna("Orco");
        assertTrue(quest.isCompletata());
    }

    @Test
    public void testGetProgressBar() {
        BaseStats bs = new BaseStats("Guerriero");
        bs.sommaStats(10, 8, 5);
        Equip premio = new Equip(bs, "Spada Magica", "Arma", "Guerriero", 10, 0, 0);
        ObiettivoKill obiettivo = new ObiettivoKill("Orco", 10);
        Quest quest = new Quest("Uccidi Orchi", "Sconfiggi 10 Orchi", obiettivo, premio);

        String progress = quest.getProgressBar();
        assertTrue(progress.contains("0%"));

        obiettivo.aggiorna("Orco");
        progress = quest.getProgressBar();
        assertTrue(progress.contains("10%"));
    }

    @Test
    public void testSetPremioRiscattato() {
        BaseStats bs = new BaseStats("Guerriero");
        bs.sommaStats(10, 8, 5);
        Equip premio = new Equip(bs, "Spada Magica", "Arma", "Guerriero", 10, 0, 0);
        ObiettivoKill obiettivo = new ObiettivoKill("Orco", 1);
        Quest quest = new Quest("Uccidi Orchi", "Sconfiggi 1 Orco", obiettivo, premio);

        assertFalse(quest.isPremioRiscattato());
        quest.setPremioRiscattato(true);
        assertTrue(quest.isPremioRiscattato());
    }
}