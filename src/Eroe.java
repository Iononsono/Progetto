import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Eroe extends Entita {
    protected String classeN;
    protected Statistiche statsTot;
    private List<Spell> SpellsEroe = new ArrayList<>();
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
        int i=0;
        System.out.println("Inventario di " + this.nome + ":");
        for (Map.Entry<Equip, Boolean> entry : Inv.entrySet()) {
            Equip equip = entry.getKey();
            Boolean equipped = entry.getValue();
            System.out.println(i+")" + equip.getNome() + " [" + equip.getClasse() + "][" + equip.getTipo() + "] " + (equipped ? " (Equipaggiato)" : ""));
            i++;
        }
    }
    public void stampaSpells() {
        System.out.println("Spells conosciute da " + this.nome + ":");
        for (Spell s : SpellsEroe) {
            System.out.printf("- %s (Costo MP: %.1f, Tipo: %s, Valore: %.1f)\n", s.getNome(), s.getCostoMP(), s.getTipo(), s.getValore());
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
    public void mostraSchedaEntita() {
        super.mostraSchedaEntita();
        System.out.println("--- STATISTICHE TOTALI (con Equip) ---");
        System.out.println("HP: " + statsTot.getHp());
        System.out.println("ATK: " + statsTot.getAtk());
        System.out.println("MP: " + statsTot.getMp());
        stampaSpells();
        stampaInv();
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