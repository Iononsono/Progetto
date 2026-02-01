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
    private int expG=1000;//per comodità tutti i nemici danno 100 exp

    public Combattimento(Eroe e, Nemico n) {
        this.e = e;
        this.n = n;
        this.statsEroe = new HashMap<>();
        this.statsNemico = new HashMap<>(n.getStats());
        this.statsEroe.put("hp", e.statsTot.getHp());
        this.statsEroe.put("atk", e.statsTot.getAtk());
        this.statsEroe.put("mp", e.statsTot.getMp());
        this.manaMax = statsEroe.get("mp"); //mana max combattimento
    }
    public void avviaCombattimento() {
        System.out.println("Inizio del combattimento tra " + e.getNome() + " e " + n.getNome());

        while (statsEroe.get("hp") > 0 && statsNemico.get("hp") > 0) {
            TurnoEroe();
            if (statsNemico.get("hp") <= 0) {
                System.out.println("["+n.getNome()+"]" + " è stato sconfitto!");
                e.assegnaExp(expG);
                e.controllaQuest(n.getClasse());
                break;
            }
            System.out.println("----------------------------------------");
            TurnoNemico();
            if (statsEroe.get("hp") <= 0) {
                System.out.println(e.getNome() + " è stato sconfitto!");
                break;
            }
            System.out.println("----------------------------------------");
        }
        System.out.println("----------------------------------------");
        System.out.println("Combattimento terminato.");
    }
   
    public float calcoloDanno(float atk, String nome) {    
        Random random = new Random();
        // UC5: Il sistema effettua il tiro di dado 
        float r = random.nextInt(1,7); 
        System.out.println("[" + nome + "] Lancio del dado: " + r); 
        float danno =atk+ atk * r/100;
        return (float) (Math.round(danno * 10.0) / 10.0); 
    }

    public void TurnoEroe() {
    // Implementa la logica del turno dell'eroe
    System.out.println("E' il turno di " + e.getNome() + ". Scegli un'azione:\n|1->Attacca|2->Usa Spell|");
    int sceltaEroe;
    float temp=statsEroe.get("mp");
    try {
        sceltaEroe=input.nextInt();
        input.nextLine(); // Consumare la nuova linea rimasta nel buffer
    } catch (Exception ex) {
        System.out.println("Input non valido. Attacco di default.");
        sceltaEroe = 1;
        input.nextLine();
    }
    switch(sceltaEroe) {
        case 1:
            System.out.println(e.getNome() + " sceglie di attaccare.");
            float dannoEroe = calcoloDanno(statsEroe.get("atk"), e.getNome());
            statsNemico.put("hp", statsNemico.get("hp") - dannoEroe);
            System.out.printf("[%s] attacca per %.1f danni.\nHP rimanenti per [%s]: %.1f\n",e.getNome(), dannoEroe, n.getNome(), statsNemico.get("hp"));
            if(temp +10 > manaMax){
                statsEroe.put("mp", manaMax);                          
            }
            break;
        case 2:
            System.out.println(e.getNome() + " sceglie di usare una spell.");
            int i=0;
            for(Spell s : e.getSpells()) {
                System.out.println(i + ") " + s.getDescCombat());
                i++;
            }
            System.out.println(e.getSpells().size()+") Torna al menù precedente");
            sceltaspell(this.e);
            break;
        default:
            System.out.println("Scelta non valida. Attacco di default.");
            break;
    }
    
    
}
    public void TurnoNemico() {
        float dannoNemico = calcoloDanno(statsNemico.get("atk"), n.getNome());
        statsEroe.put("hp", statsEroe.get("hp") - dannoNemico);
        System.out.printf("[%s] attacca per %.1f danni.\nHP rimanenti per [%s]: %.1f\n",n.getNome(), dannoNemico, e.getNome(), statsEroe.get("hp"));
    }

    public void sceltaspell(Eroe e) {
        System.out.println("Scegli una spell da usare:");
        int spellScelta;
        try {
            spellScelta = input.nextInt();   
            input.nextLine(); // Consumare la nuova linea rimasta nel buffer
        } catch (Exception ex) {
            System.out.println("Input non valido. Torna al turno.");
            return;
        }
        if(spellScelta==e.getSpells().size()){
            TurnoEroe();
            return;
        }        
        Spell s = e.getSpells().get(spellScelta);
        if(!s.applicaEffetto(statsEroe, statsNemico)){
            System.out.println("MP insufficienti per usare " + s.getNome());
            try {
                sceltaspell(e);
            } catch (Exception ex) {
                System.out.println("Errore durante la scelta della spell.");
                input.nextLine();
            }
        }
        
    }
}