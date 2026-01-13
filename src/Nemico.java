
public class Nemico extends Entita {
protected float hp,atk,mp;
    
 public Nemico(String nome, String classe) {
        
        super(nome, classe);
        System.out.println("Nemico creato correttamente: " + nome + " [" + this.classe + "]");
        initBaseStats();
    }
    
 public void initBaseStats() {
        // Implementa la logica per impostare le statistiche di base in base alla classe
       
        System.out.println("Inizializzazione statistiche base per "+ this.classe);
        switch (this.classe) {
            case "goblin":
                hp=input.nextInt();
                atk=input.nextInt();
                mp=input.nextInt();
                System.out.println("Statistiche Goblin: HP " + hp + ", ATK " + atk + ", MP " + mp);
                break;
            case "boss":
                hp=input.nextInt();
                atk=input.nextInt();
                mp=input.nextInt();
                System.out.println("Statistiche Boss: HP " + hp + ", ATK " + atk + ", MP " + mp);
                break;
        }
        stats.put("hp", hp);
        stats.put("atk", atk);
        stats.put("mp", mp);

    }
    
    
}
