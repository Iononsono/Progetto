import java.util.Scanner;
public class BaseStats {
    protected float hp,atk,mp;
    protected String classe;
    private static Scanner input = new Scanner(System.in);

    public BaseStats(String classeN) {
        this.classe=classeN;
    }

    public void setBaseStats(String classeN) {
        switch (classeN) {
            case "Guerriero":
                System.out.println("Statistiche base per Guerriero: HP 10, ATK 8, MP 5");
                System.out.println("Inserisci 9 punti stats");
                hp =  input.nextInt();
                atk = input.nextInt();
                mp =  input.nextInt();
                if(hp + atk + mp != 9){
                    System.out.println("Hai inserito un totale di " + (hp + atk + mp) + " punti. Devi inserire esattamente 9 punti stats.");
                    setBaseStats(classeN);
                }
                this.hp=hp+10;
                this.atk=atk+8;
                this.mp=mp+5;
                break;
            case "Mago":
                System.out.println("Statistiche base per Mago: HP 5, ATK 7, MP 10");
                System.out.println("Inserisci 9 punti stats");
                
                hp =  input.nextInt();
                atk = input.nextInt();
                mp =  input.nextInt();
                if(hp + atk + mp != 9){
                    System.out.println("Hai inserito un totale di " + (hp + atk + mp) + " punti. Devi inserire esattamente 9 punti stats.");
                    setBaseStats(classeN);
                }
                this.hp=hp+5;
                this.atk=atk+7;
                this.mp=mp+10;
                break;
            case "Arciere":
                System.out.println("Statistiche base per Arciere: HP 6, ATK 6, MP 10");
                System.out.println("Inserisci 9 punti stats");
                hp =  input.nextInt();
                atk = input.nextInt();
                mp =  input.nextInt();
                if(hp + atk + mp != 9){
                    System.out.println("Hai inserito un totale di " + (hp + atk + mp) + " punti. Devi inserire esattamente 9 punti stats.");
                    setBaseStats(classeN);
                }
                this.hp=hp+6;
                this.atk=atk+6;
                this.mp=mp+10;
                break;
        }
        mostraStats();
    }

    public void mostraStats() {
        System.out.println("Statistiche finali per " + classe + ": HP " + hp + ", ATK " + atk + ", MP " + mp);
    }
}
