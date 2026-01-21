import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Eroe extends Entita {
    protected String classeN;
    protected Statistiche statsTot;
    private List<Spell> SpellsEroe = new ArrayList<>();
    private List<Quest> QuestsEroe = new ArrayList<>();
    public Map<Equip,Boolean> Inv = new HashMap<>();
    private BaseStats bs;


    public Eroe(String nome, String classe) {
        // Chiama il costruttore di Entita per inizializzare nome e classe
        super(nome, classe);
            if (!classe.equals("Guerriero") && !classe.equals("Mago") && !classe.equals("Arciere")) {
                System.out.println("Classe non valida. Impostazione classe predefinita a Guerriero.");
              this.classe="Guerriero";
            }
            
             System.out.println("Eroe creato correttamente: " + nome + " [" + this.classe + "]");
            this.bs = new BaseStats(this.classe);
            initBaseStats(bs);
            addSpells(this.SpellsEroe);
        }

      
    public void stampaInv() {
    int i = 0;
    for (Map.Entry<Equip, Boolean> entry : Inv.entrySet()) {
        Equip e = entry.getKey();
        boolean montato = entry.getValue();
        
        // %-2d = indice (2 spazi)
        // %-25s = nome oggetto (25 spazi)
        // %-12s = tipo tra parentesi quadre (12 spazi)
        System.out.printf("| %-2d) %-25s [%-10s] %s |\n", 
                          i, 
                          e.getNome(), 
                          e.getTipo(), 
                          montato ? "[E]" : "   ");
        i++;
    }
}
    public void stampaSpells() {
    if (SpellsEroe.isEmpty()) {
        System.out.println("|   (Nessun incantesimo appreso)            |");
    } else {
        for (Spell s : SpellsEroe) {
            System.out.printf("| • %-39s |\n", s.getDescCombat());
        }
    }
    }
    public void initBaseStats(BaseStats bs) {
        System.out.println("Inizializzazione statistiche base per "+ this.classe);
        bs.setBaseStats(this.classe);
        this.statsTot = bs;
        stats.put("hp", statsTot.getHp());
        stats.put("atk", statsTot.getAtk());
        stats.put("mp", statsTot.getMp());
    }
    

    public Eroe(String nome, String classe, float hp, float atk, float mp) {
        super(nome, classe);
        stats.put("hp", hp);
        stats.put("atk", atk);
        stats.put("mp", mp);
        this.bs = new BaseStats(classe);
        this.bs.sommaStats(hp, atk, mp);
        this.statsTot = this.bs;
    }
    
    public void addSpells(List<Spell> listaSpells) {
        for(Spell s : listaSpells) {
            if(s.getClasse().equals(this.classe)) {
                System.out.printf(" %s[%s] è stata appresa da %s[%s]\n", s.getNome(),s.getClasse(),this.nome,this.classe);
                SpellsEroe.add(s);
            }
        }
        
    }
    public void accettaQuest(List<Quest> listaQuests){
        int i=0;
        Quest qe= null;
       for(Quest q : listaQuests) {
        System.out.print(i+")");
        q.stampaQuest();
        i++;
        }
        System.out.println("Seleziona Quest");
        int index=input.nextInt();
        input.nextLine();
        QuestsEroe.add(listaQuests.get(index));
        System.out.println("Quest "+listaQuests.get(index).getTitolo()+" accettata buona fortuna."); 
    }
    public void controllaQuest(Object evento){
        for(Quest q : QuestsEroe) {
        q.aggiorna(evento);
        if(q.isCompletata()){
            Inv.put(q.getPremio(), false);
            System.out.println("Hai ricevuto il premio della quest: "+q.getPremio().getNome());
            }  
        }  

    }
    public void equipItem(int index) {
    if (index >= 0 && index < Inv.size()) {
        List<Equip> keys = new ArrayList<>(Inv.keySet());
        Equip scelto = keys.get(index);
        if(!scelto.getClasse().equals(this.classe) && !scelto.getClasse().equals("Universale")){
            System.out.println("Non puoi equipaggiare " + scelto.getNome() + " perché non è adatto alla classe " + this.classe);
            return;
        }

        for(Map.Entry<Equip, Boolean> entry : Inv.entrySet()) {
            Equip equip = entry.getKey();
            Boolean equipped = entry.getValue();
            // 3. Rimuovi l'equipaggiamento attualmente equipaggiato dello stesso tipo
            if (equipped && equip.getTipo().equals(scelto.getTipo())) {
                System.out.println("Hai sostituito l'equipaggiamento: " + equip.getNome()+" con " + scelto.getNome());
                Inv.put(equip, false);
                break;
            }
        }
        Inv.put(scelto, true);
        System.out.println("Hai equipaggiato: " + scelto.getNome());
        calcolaStatsFinali();
        
        
    } else {
        System.out.println("Ritorna al menu precedente");
    }
}

    public List<Spell> getSpells() {
        return SpellsEroe;
    }
    @Override
    public void mostraSchedaEntita() {
    super.mostraSchedaEntita(); 
    System.out.println("\n[ STATISTICHE TOTALI ]");
    System.out.println("----------------------------------------");
    System.out.printf("| %-10s: %6.1f | %-10s: %6.1f |\n", 
                      "Punti Vita", statsTot.getHp(), 
                      "Punti Mana", statsTot.getMp());
    System.out.printf("| %-10s: %6.1f |\n", 
                      "Attacco", statsTot.getAtk());             
    System.out.println("----------------------------------------");
    System.out.println("\n> INCANTESIMI:");
    stampaSpells();
    System.out.println("\n> INVENTARIO:");
    stampaInv();
    System.out.println("========================================\n");
}
    public void calcolaStatsFinali() {
    Statistiche calcolo = this.bs;

    // 2. Scorriamo l'inventario e "avvolgiamo" l'eroe con gli oggetti attivi
    for (Map.Entry<Equip, Boolean> entry : Inv.entrySet()) {
        if (entry.getValue()) { // Se è equipaggiato (true)
            Equip oggetto = entry.getKey();
            // Creiamo il decoratore usando il costruttore a 7 parametri
            calcolo = new Equip(calcolo, oggetto.getNome(), oggetto.getTipo(), 
                                oggetto.getClasse(), oggetto.getAtkBonus(), 
                                oggetto.getHpBonus(), oggetto.getMpBonus());
        }
    }
    this.statsTot = calcolo;
}
}