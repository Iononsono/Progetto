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
    private int expN;
    private int expAttuale=0;

    public Eroe(String nome, String classe) {
        // Costruttore console
        super(nome, classe);
            if (!classe.equals("Guerriero") && !classe.equals("Mago") && !classe.equals("Arciere")) {
                System.out.println("Classe non valida. Impostazione classe predefinita a Guerriero.");
              this.classe="Guerriero";
            }
            this.livello=livello;
            expAttuale=0;
            this.livello=5;
            this.expN=livello*10;
            System.out.println("Eroe creato correttamente: " + nome + " [" + this.classe + "]");
            this.bs = new BaseStats(this.classe);
            initBaseStats(bs);
            addSpells(this.SpellsEroe);
        }
    public Eroe(String nome, String classe, float hp, float atk, float mp) {
        //Costruttore file
        super(nome, classe);
        expAttuale=0;
        this.livello=5;
        stats.put("hp", hp);
        stats.put("atk", atk);
        stats.put("mp", mp);
        this.bs = new BaseStats(classe);
        this.bs.sommaStats(hp, atk, mp);
        this.statsTot = this.bs;
    }
    public void stampaQuest(){
        if (QuestsEroe.isEmpty()) {
        System.out.println(" > Nessuna missione in corso.");
    } else {
        for (Quest q : QuestsEroe) {
            String stato = q.getObiettivo().isCompletato() ? "[COMPLETATA]" : q.getProgressBar();
            System.out.printf(" - %-15s %s (%d/%d)\n", 
                q.getTitolo(), 
                stato, 
                q.getObiettivo().getCorrente(), 
                q.getObiettivo().getTotale());
        }
    }
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
    public void assegnaExp(int expG){
        if(expAttuale+expG>=expN){
            levelUP(expG);
        }
        else{
            expAttuale+=expG;
            System.out.println("Hai guadagnato "+expG+" punti esperienza. Esperienza attuale: "+expAttuale+"/"+expN);
        }
    }
    public void stampaSpells() {
        if (SpellsEroe.isEmpty()) {
            System.out.println("|   (Nessun incantesimo appreso)            |");
        } else {
            for (Spell s : SpellsEroe) {
                System.out.printf("|  %-39s |\n", s.getDescCombat());
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
    public void addSpells(List<Spell> listaSpells) {
        for(Spell s : listaSpells) {
            if(s.getClasse().equals(this.classe)) {
                SpellsEroe.add(s);
            }
        }
        
    }
    public void accettaQuest(List<Quest> listaQuests){
        int i=0;
        for(Quest q : listaQuests) {
            System.out.print(i+")");
            q.stampaQuest();
            i++;
            }
        System.out.println("Seleziona Quest");
        while(true){
            int index=input.nextInt();
            if(index==-1) break;
            if(index>=0&&index<listaQuests.size()){
                QuestsEroe.add(listaQuests.get(index));
                System.out.println("Quest "+listaQuests.get(index).getTitolo()+" accettata buona fortuna.\n Scegli un altra quest o esci[-1]");
            }
            
        }
        input.nextLine();
         
    }
    public void controllaQuest(Object evento){
        for(Quest q : QuestsEroe) {
        q.aggiorna(evento);
        if(q.isCompletata()&& !q.isPremioRiscattato()){
            Inv.put(q.getPremio(), false);
            q.setPremioRiscattato(true);
            System.out.println("Hai ricevuto il premio della quest: "+q.getPremio().getNome());
            }  
        }  

    }
    public void equipItem(int index) {
        if(index >= 0 && index < Inv.size()) {
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
        //per pulizia di codice sarebbe stato meglio fare una classe inventario così da evitare una ricerca ad ogni equipaggiamento
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
    System.out.println("\n> QUEST:");
    stampaQuest();
    System.out.println("========================================\n");
    //aggiungi leggi quest
}
    public void calcolaStatsFinali() {
    Statistiche calcolo = this.bs;
    for (Map.Entry<Equip, Boolean> entry : Inv.entrySet()) {
        if (entry.getValue()) { // Se è equipaggiato (true)
            Equip oggetto = entry.getKey();
            calcolo = new Equip(calcolo, oggetto.getNome(), oggetto.getTipo(), 
                                oggetto.getClasse(), oggetto.getAtkBonus(), 
                                oggetto.getHpBonus(), oggetto.getMpBonus());
        }
    }
    this.statsTot = calcolo;
    }
    
    public void levelUP(int expG){
        int vlv = livello;
        int expTotale = expAttuale + expG;
        while(expTotale >= expN){
            expTotale -= expN;
            livello++;
            // Aggiorna expN per il nuovo livello
            expN = livello * 10;
            // Controlla quest per ogni livello salito
            
        }
        expAttuale = expTotale;
        if (livello > vlv) {
            System.out.println("========================================");
            System.out.println("|Complimenti sei salito di livello!" + vlv + "->" + livello + "|");
            System.out.println("========================================");
            int puntiBonus = 3 * (livello - vlv);
            List<Float> stats = bs.assegnaPunti(puntiBonus);
            bs.sommaStats(bs.getHp() + stats.get(0), bs.getAtk() + stats.get(1), bs.getMp() + stats.get(2));
            calcolaStatsFinali();
            bs.mostraStats();
            controllaQuest(livello);
        }
    }
    public List<Quest> getQuestsEroe(){
        return QuestsEroe;
    }
}
