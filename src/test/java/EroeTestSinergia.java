

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class EroeTestSinergia {
    @Test
    void testProgressioneCompleta() {
        Eroe eroe = new Eroe("Hero", "Guerriero", 10.0f, 8.0f, 5.0f);
        Quest q = new Quest("Test", "D", new ObiettivoLivello(6), null);
        eroe.getQuestsEroe().add(q);

        // Azione che scatena l'Observer - assegna abbastanza exp per livello 6
        eroe.assegnaExp(200); 

        assertTrue(q.isCompletata(), "L'Observer non ha notificato correttamente la Quest!");
    }
}