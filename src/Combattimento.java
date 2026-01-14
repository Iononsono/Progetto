import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Combattimento {
    private Eroe e;
    private Nemico n;
    private Map<String, Float> statsEroe;
    private Map<String, Float> statsNemico;
    private Scanner input = new Scanner(System.in);
    private float manaRecuperato, manaMax;

    public Combattimento(Eroe e, Nemico n) {
        this.e = e;
        this.manaMax= e.getStats().get("mp");//max per questo personaggio
        this.n = n;
        this.statsEroe = new HashMap<>(e.getStats());
        this.statsNemico = new HashMap<>(n.getStats());
        avviaCombattimento();
    }
    public void avviaCombattimento() {
        System.out.println("Inizio del combattimento tra " + e.getNome() + " e " + n.getNome());

        while (statsEroe.get("hp") > 0 && statsNemico.get("hp") > 0) {
            // Turno dell'eroe
            TurnoEroe();
            if (statsNemico.get("hp") <= 0) {
                System.out.println(n.getNome() + " è stato sconfitto!");
                break;
            }

            // Turno del nemico
            TurnoNemico();
            if (statsEroe.get("hp") <= 0) {
                System.out.println(e.getNome() + " è stato sconfitto!");
                break;
            }
        }

        System.out.println("Combattimento terminato.");
    }
   
    public float calcoloDanno(float atk) {
    Random random = new Random();
    // UC5: Il sistema effettua il tiro di dado 
    float r = random.nextInt(1,7); 
    
    System.out.println("Lancio del dado: " + r); 
    
    float danno =atk+ atk * r/100;
    return (float) (Math.round(danno * 10.0) / 10.0); 
}

public void TurnoEroe() {
    // Implementa la logica del turno dell'eroe
    System.out.println("E' il turno di " + e.getNome() + ". Scegli un'azione:");
    int sceltaEroe;
    float temp=statsEroe.get("mp");
    sceltaEroe=input.nextInt();
    input.nextLine(); // Consumare la nuova linea rimasta nel buffer
    switch(sceltaEroe) {
        case 1:
            System.out.println(e.getNome() + " sceglie di attaccare.");
            if(temp +10 > manaMax){
                statsEroe.put("mp", manaMax);
            }
            break;
        case 2:
            System.out.println(e.getNome() + " sceglie di usare una spell.");
            break;
        default:
            System.out.println("Scelta non valida. Attacco di default.");
            break;
    }
    float dannoEroe = calcoloDanno(statsEroe.get("atk"));
            statsNemico.put("hp", statsNemico.get("hp") - dannoEroe);
            System.out.printf("[Eroe]:%s attacca per %.1f danni.\n"+
            "HP rimanenti per %s: %.1f\n", 
                          e.getNome(), dannoEroe, n.getNome(), statsNemico.get("hp"));
    
}
public void TurnoNemico() {
    // Implementa la logica del turno del nemico
    float dannoNemico = calcoloDanno(statsNemico.get("atk"));
            statsEroe.put("hp", statsEroe.get("hp") - dannoNemico);
             System.out.printf("[Nemico]:%s attacca per %.1f danni.\n"+
            "HP rimanenti per %s: %.1f\n",
                          n.getNome(), dannoNemico, e.getNome(), statsEroe.get("hp"));
            System.out.println(n.getNome() + " attacca {" + e.getNome() + "} per " + dannoNemico + " danni. HP rimanenti di {" + e.getNome() + "}: " + statsEroe.get("hp"));
}
}
