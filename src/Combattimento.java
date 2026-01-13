import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Combattimento {
    private Eroe e;
    private Nemico n;
    private Map<String, Float> statsEroe;
    private Map<String, Float> statsNemico;
    public Combattimento(Eroe e, Nemico n) {
        this.e = e;
        this.n = n;
        this.statsEroe = new HashMap<>(e.getStats());
        this.statsNemico = n.getStats();
        avviaCombattimento();
    }
    public void avviaCombattimento() {
        System.out.println("Inizio del combattimento tra " + e.getNome() + " e " + n.getNome());

        while (statsEroe.get("hp") > 0 && statsNemico.get("hp") > 0) {
            // Turno dell'eroe
            float dannoEroe = calcoloDanno(statsEroe.get("atk"));
            statsNemico.put("hp", statsNemico.get("hp") - dannoEroe);
            System.out.printf("[Eroe]:%s attacca per %.1f danni.\n"+
            "HP rimanenti per %s: %.1f\n", 
                          e.getNome(), dannoEroe, n.getNome(), statsNemico.get("hp"));
            if (statsNemico.get("hp") <= 0) {
                System.out.println(n.getNome() + " è stato sconfitto!");
                break;
            }

            // Turno del nemico
            float dannoNemico = calcoloDanno(statsNemico.get("atk"));
            statsEroe.put("hp", statsEroe.get("hp") - dannoNemico);
             System.out.printf("[Nemico]:%s attacca per %.1f danni.\n"+
            "HP rimanenti per %s: %.1f\n",
                          n.getNome(), dannoNemico, e.getNome(), statsEroe.get("hp"));
            System.out.println(n.getNome() + " attacca {" + e.getNome() + "} per " + dannoNemico + " danni. HP rimanenti di {" + e.getNome() + "}: " + statsEroe.get("hp"));

            if (statsEroe.get("hp") <= 0) {
                System.out.println(e.getNome() + " è stato sconfitto!");
                break;
            }
        }

        System.out.println("Combattimento terminato.");
    }
   /*  public float calcoloDanno(float atk) {
        Random random = new Random();
        float r = random.nextFloat(0.1f, 0.6f);
        System.out.printf("Lancio del dado: %d\n", r*100);
        return Float.parseFloat(String.format("%.1f", atk*r)); // Implementa la logica per calcolare il danno in base alle statistiche
    } */
    public float calcoloDanno(float atk) {
    Random random = new Random();
    // UC5: Il sistema effettua il tiro di dado 
    float r = random.nextFloat(0.1f, 0.7f); 
    
    // CORREZIONE: Usa %f per float o casta a (int) se vuoi usare %d
    System.out.printf("Lancio del dado: %d\n", (int)(r * 10)); 
    
    // UC5: Il sistema calcola l'esito combinando tiro di dado e statistiche 
    // Usiamo Math.round per l'arrotondamento invece di parsing di stringhe (più veloce)
    float danno = atk * r;
    return (float) (Math.round(danno * 10.0) / 10.0); 
}
}
