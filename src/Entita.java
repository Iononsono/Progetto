import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public abstract class Entita {
    protected String nome;
    protected int livello;
    protected String classe;
    protected Scanner input = new Scanner(System.in);
    public Map<String, Float> stats = new HashMap<>();

    public Entita(String nome, String classe) {
        this.nome=nome;
        this.classe=classe;
        
    }
    public void initBaseStats() {
       
    }
    public String getNome() {
        return nome;
    }
    public String getClasse() {
        return classe;
    }
    public Map<String, Float> getStats() {
        return stats;
    }
    public int getLivello(){
        return livello;
    }

    public void mostraSchedaEntita() {
    System.out.println("\n.-------------------------------------------.");
    System.out.printf("| %-41s |\n", "SCHEDA SOGGETTO: " + getNome().toUpperCase());
    System.out.println("|-------------------------------------------|");
    System.out.printf("| CLASSE: %-27s LV: %d |\n", getClasse(), getLivello());
    }
    
}