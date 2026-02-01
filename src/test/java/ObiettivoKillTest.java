import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObiettivoKillTest {

    @Test
    public void testObiettivoKillConstructor() {
        ObiettivoKill obiettivo = new ObiettivoKill("Orco", 5);
        assertNotNull(obiettivo);
        assertEquals("Orco", obiettivo.getNomeNemicoTarget());
        assertEquals(5, obiettivo.getNecessari());
        assertEquals(0, obiettivo.getCorrente());
        assertEquals(5, obiettivo.getTotale());
        assertFalse(obiettivo.isCompletato());
    }

    @Test
    public void testAggiorna() {
        ObiettivoKill obiettivo = new ObiettivoKill("Orco", 3);
        obiettivo.aggiorna("Orco");
        assertEquals(1, obiettivo.getCorrente());
        assertFalse(obiettivo.isCompletato());

        obiettivo.aggiorna("Orco");
        obiettivo.aggiorna("Orco");
        assertEquals(3, obiettivo.getCorrente());
        assertTrue(obiettivo.isCompletato());
    }

    @Test
    public void testAggiornaWrongTarget() {
        ObiettivoKill obiettivo = new ObiettivoKill("Orco", 2);
        obiettivo.aggiorna("Goblin");
        assertEquals(0, obiettivo.getCorrente());
        assertFalse(obiettivo.isCompletato());
    }

    @Test
    public void testGetDescrizione() {
        ObiettivoKill obiettivo = new ObiettivoKill("Orco", 5);
        assertEquals("Uccidi Orco (0/5)", obiettivo.getDescrizione());
        obiettivo.aggiorna("Orco");
        assertEquals("Uccidi Orco (1/5)", obiettivo.getDescrizione());
    }

    @Test
    public void testGetTipo() {
        ObiettivoKill obiettivo = new ObiettivoKill("Orco", 5);
        assertEquals("KILL", obiettivo.getTipo());
    }

    @Test
    public void testGetNomeTarget() {
        ObiettivoKill obiettivo = new ObiettivoKill("Orco", 5);
        assertEquals("Orco", obiettivo.getNomeTarget());
    }
}