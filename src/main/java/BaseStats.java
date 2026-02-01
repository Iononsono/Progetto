import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class BaseStats implements Statistiche {
    protected float hp,atk,mp;
    protected String classe;
    private static Scanner input = new Scanner(System.in);
    float hpG=10, atkG=8, mpG=5;
    float hpM=5, atkM=7, mpM=10;
    float hpA=6, atkA=6, mpA=10;
    private float bHp=0, bAtk=0, bMp=0;
    private List<Float> stats=new ArrayList<>();
    private int pb=15;

    public BaseStats(String classeN) {
        this.classe=classeN;
        this.stats.add(0f); // HP
        this.stats.add(0f); // ATK
        this.stats.add(0f); // MP
    }

    public void setBaseStats(String classeN) {
        switch (classeN) {
        case "Guerriero":
            bHp = hpG; bAtk = atkG; bMp = mpG;
            System.out.println("Statistiche base per Guerriero: HP " + hpG + ", ATK " + atkG + ", MP " + mpG);
            stats = assegnaPunti(pb);
            break;
        case "Mago":
            bHp = hpM; bAtk = atkM; bMp = mpM;
            System.out.println("Statistiche base per Mago: HP " + hpM + ", ATK " + atkM + ", MP " + mpM);
            stats = assegnaPunti(pb);
            break;
        case "Arciere":
            bHp = hpA; bAtk = atkA; bMp = mpA;
            System.out.println("Statistiche base per Arciere: HP " + hpA + ", ATK " + atkA + ", MP " + mpA);
            stats = assegnaPunti(pb);
            break;
        default:
            System.out.println("Classe non riconosciuta!");
            return;
        }
        sommaStats(stats.get(0)+bHp, stats.get(1)+bAtk, stats.get(2)+bMp);
        mostraStats();
    }

    public float checkStats(float hp, float atk, float mp){
        return hp + atk + mp;
    }
    @Override public float getHp() { return hp; }
    @Override public float getAtk() { return atk; }
    @Override public float getMp() { return mp;}

    public void mostraStats() {
        System.out.println("Statistiche senza Equip: HP"  + getHp() + ", ATK " + getAtk() + ", MP " + getMp());
    }

    public void sommaStats(float hp, float atk, float mp) {
        this.hp = hp;
        this.atk = atk;
        this.mp = mp;
    }
    public List<Float> assegnaPunti(int puntiDisponibili) {
        System.out.println("Inserisci " + puntiDisponibili + " punti stats in totale tra[HP/ATK/MP]:");
        boolean Pcorretti = false;
        while(!Pcorretti){
            try {
                float hp=input.nextFloat();
                float atk=input.nextFloat();
                float mp=input.nextFloat();
                if (checkStats(hp, atk, mp) == puntiDisponibili) {
                    stats.set(0, hp);
                    stats.set(1, atk);
                    stats.set(2, mp);
                    Pcorretti=true;
                }else {
                    System.out.println("Errore! Devi distribuire esattamente " + puntiDisponibili + " punti.");    
                }
            } catch (Exception e) {
                System.out.println("Input non valido. Inserisci numeri validi per HP, ATK, MP.");
                input.nextLine(); // consume invalid input
            }
        }
        return stats;
    }


}