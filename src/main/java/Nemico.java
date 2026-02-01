public class Nemico extends Entita {
protected float hp,atk,mp;
    
 public Nemico(String nome, String classe) {
        
        super(nome, classe);
        System.out.println("Nemico creato correttamente: " + nome + " [" + this.classe + "]");
    }
    
 public void initBaseStats() {
        System.out.println("Inizializzazione statistiche base per "+ this.classe);
        try {
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
        } catch (Exception e) {
            System.out.println("Input non valido per statistiche. Impostazione valori di default.");
            hp = 10;
            atk = 5;
            mp = 3;
            input.nextLine(); // consume invalid input
        }
        stats.put("hp", hp);
        stats.put("atk", atk);
        stats.put("mp", mp);

    }
    
    public Nemico(String nome, String classe, float hp, float atk, float mp) {
        super(nome, classe);
        stats.put("hp", hp);
        stats.put("atk", atk);
        stats.put("mp", mp);
        
    }
    public void mostraSchedaEntita() {
        super.mostraSchedaEntita();
        System.out.println("STATISTICHE: "+ getStats());
    }
}
