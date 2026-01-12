public class Eroe extends Entita {
    protected String classeN;
    protected BaseStats bs;

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
    }

    public void mostraStats() {
        System.out.println("HP: " + bs.hp + ", ATK: " + bs.atk + ", MP: " + bs.mp);
    }
   /*  public String getNome() {
        return nome;
    }
    public String getClasse() {
        return classe;
    }*/

     public void mostraSchedaEntita() {
        System.out.println("Nome: " + getNome());
        System.out.println("Classe: " + getClasse());
        System.out.println("Statistiche:"+ bs.hp + " HP, " + bs.atk + " ATK, " + bs.mp + " MP");
    }
}