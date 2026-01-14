import java.util.ArrayList;
import java.util.List;

public class Eroe extends Entita {
    protected String classeN;
    protected BaseStats bs;
    private List<Spell> SpellsEroe = new ArrayList<>();

    public Eroe(String nome, String classe) {
        // Chiama il costruttore di Entita per inizializzare nome e classe
        super(nome, classe);
        
        
       
            if (!classe.equals("Guerriero") && !classe.equals("Mago") && !classe.equals("Arciere")) {
                System.out.println("Classe non valida. Impostazione classe predefinita a Guerriero.");
              this.classe="Guerriero";
              
              
            }
            
             System.out.println("Eroe creato correttamente: " + nome + " [" + this.classe + "]");
            this.bs = new BaseStats(this.classe);
            initBaseStats();
        }

      
    

    public void initBaseStats() {
        // Implementa la logica per impostare le statistiche di base in base alla classe
       
        System.out.println("Inizializzazione statistiche base per "+ this.classe);
        this.bs.setBaseStats(this.classe);
        stats.put("hp", bs.hp);
        stats.put("atk", bs.atk);
        stats.put("mp", bs.mp);
    }
    

    public Eroe(String nome, String classe, float hp, float atk, float mp) {
        super(nome, classe);
        stats.put("hp", hp);
        stats.put("atk", atk);
        stats.put("mp", mp);
        
    }
    
    public void addSpells(List<Spell> listaSpells) {
        for(Spell s : listaSpells) {
            if(s.getClasse().equals(this.classe)) {
                System.out.printf(" %s[%s] è stata appresa da %s[%s]\n", s.getNome(),s.getClasse(),this.nome,this.classe);
                SpellsEroe.add(s);
            }
        }
        
    }
     
}