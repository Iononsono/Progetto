import java.util.Scanner;
public class BaseStats {
    protected float hp,atk,mp;
    protected String classe;
    private static Scanner input = new Scanner(System.in);
    float hpG=10, atkG=8, mpG=5;
    float hpM=5, atkM=7, mpM=10;
    float hpA=6, atkA=6, mpA=10;

    public BaseStats(String classeN) {
        this.classe=classeN;
    }

    public void setBaseStats(String classeN) {
        switch (classeN) {
            case "Guerriero":
                System.out.println("Statistiche base per Guerriero: HP " + hpG + ", ATK " + atkG + ", MP " + mpG);
                System.out.println("Inserisci 9 punti stats");
                hp =  input.nextInt();
                atk = input.nextInt();
                mp =  input.nextInt();
                if(checkStats(hp,atk,mp) != 9){
                    System.out.println("Hai inserito un totale di " + (hp + atk + mp) + " punti. Devi inserire esattamente 9 punti stats.");
                    setBaseStats(classeN);
                }
                sommaStats(hp+hpG, atk+atkG, mp+mpG);
                break;
            case "Mago":
                System.out.println("Statistiche base per Mago: HP " + hpM + ", ATK " + atkM + ", MP " + mpM);
                System.out.println("Inserisci 9 punti stats");
                
                hp =  input.nextInt();
                atk = input.nextInt();
                mp =  input.nextInt();
                if(checkStats(hp,atk,mp) != 9){
                    System.out.println("Hai inserito un totale di " + (hp + atk + mp) + " punti. Devi inserire esattamente 9 punti stats.");
                    setBaseStats(classeN);
                }
                sommaStats(hp+hpM, atk+atkM, mp+mpM);
                break;
            case "Arciere":
                System.out.println("Statistiche base per Arciere: HP " + hpA + ", ATK " + atkA + ", MP " + mpA);
                System.out.println("Inserisci 9 punti stats");
                hp =  input.nextInt();
                atk = input.nextInt();
                mp =  input.nextInt();
                if(checkStats(hp,atk,mp) != 9){
                    System.out.println("Hai inserito un totale di " + (hp + atk + mp) + " punti. Devi inserire esattamente 9 punti stats.");
                    setBaseStats(classeN);
                }
                sommaStats(hp+hpA, atk+atkA, mp+mpA);
                break;
        }
        mostraStats();
    }

    public float checkStats(float hp, float atk, float mp){
        return hp + atk + mp;
    }

    public void mostraStats() {
        System.out.println("Statistiche finali per " + classe + ": HP " + hp + ", ATK " + atk + ", MP " + mp);
    }

    public void sommaStats(float hp, float atk, float mp) {
        this.hp = hp;
        this.atk = atk;
        this.mp = mp;
    }

}