import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Gamesystem {
    private Eroe er;
    private Nemico n;
    private Spell s;
    private Equip eq;
    private Quest q;    
    private List<Eroe> listaEroi = new ArrayList<>();
    private List<Nemico> listaNemici = new ArrayList<>();
    private List<Spell> listaSpells = new ArrayList<>();
    private List<Equip> listaEquip = new ArrayList<>();
    protected static final Scanner input = new Scanner(System.in);
    private String[] dati;
    private List<Quest> listaQuest = new ArrayList<>();
    private Combattimento c = null;
    public Gamesystem() {
       
    }
        
    public void start() {
        System.out.println("Benvenuto in Bit And Blade!");
        letturadatiFile();
        mostraMenu();

        input.close();
    }
  
    public void selezioneAttore() {
        
        String nome;
        String classe;
        int scelta;
        System.out.println("Sei admin o player?(0 o 1)"); 
        int Attore = input.nextInt();
        if(Attore == 0){
           System.out.println("Benvenuto Admin!");
           System.out.println("\n=== MENU Admin ===");
           System.out.println("1 - Crea Nemico");
           System.out.println("2 - Crea Equipaggiamento");
           System.out.println("3 - Crea Quest");
           scelta = input.nextInt();
           input.nextLine(); 
           switch(scelta){
            case 1:
                System.out.println("Inserisci il nome del nemico:");
                nome = input.nextLine();
                System.out.println("Specifica se il nemico è un boss o un goblin:");
                classe= input.nextLine();
                Nemico n=new Nemico(nome, classe);
                listaNemici.add(n);
                salvaSuFile(n);
                System.out.println("Nemico creato: " + n.getNome() + " Classe: " + n.getClasse());
                break;
            case 2:
                eq=creaEquip();
                salvaSuFileEq(eq);
                break;    
            case 3:
                q=creaQuest();
                salvasuFileQ(q);
                break;
           }
        } else {
            System.out.println("Benvenuto Player!");
            System.out.println("\n=== MENU PLAYER ===");
            System.out.println("1 - Crea Personaggio");
            System.out.println("2 - Equipaggia Oggetto");
            System.out.println("3 - Avvia Combattimento");
            System.out.println("4 - Accetta Quest");
            System.out.println("0 - Esci");
            System.out.print("Scelta: ");
            scelta = input.nextInt();
            input.nextLine();
            switch (scelta) {
                case 1:
                    System.out.println("Inserisci il nome del tuo eroe:"); 
                    nome = input.nextLine();   
                    System.out.println("Nome eroe scelto: " + nome);
                    System.out.println("Scegli la classe del tuo eroe: 1-Guerriero 2-Mago 3-Arciere");
                    classe = input.nextLine();
                    Eroe er = new Eroe(nome, classe);
                    listaEroi.add(er);
                    salvaSuFile(er);
                    break;
                case 2:
                    System.out.println("A quale personaggio vuoi equipaggiare l'oggetto ?");
                    String nomeEroe = input.nextLine();
                    Entita e= trovaEntita(nomeEroe);
                    Eroe ero=(Eroe) e;
                    ero.stampaInv();
                    int equipScelto=0;
                    while(equipScelto!=-1){
                    System.out.println("Scegli l'equipaggiamento da equipaggiare:");
                    equipScelto = input.nextInt();
                    input.nextLine();
                    ero.equipItem(equipScelto);
                    }
                    break;
                case 3:
                    avviaCombattimento();
                    break;
                case 4:
                    accettaQuest();
                    break;
                case 0:
                    System.out.println("Uscita dal menu player.");
                    break;
            }
            

            
            
        }
    }
    public void scegliEquipaggiamento() {
    
        System.out.println("Per quale eroe vuoi scegliere l'equipaggiamento?");
        String nomeEroe = input.nextLine();
        Entita e= trovaEntita(nomeEroe);
        Eroe ero=(Eroe) e;
        if (ero != null) {
            stampaEquip();
            int scelta=0;
            while(scelta!=-1){
            System.out.println("Scegli l'equipaggiamento da inserire nel inventario di " + e.getNome() + ":(-1 per uscire)");
            scelta = input.nextInt();
            Equip equipScelto = null;
            if (scelta >= 0 && scelta < listaEquip.size()) {
                equipScelto = listaEquip.get(scelta);
                ero.Inv.put(equipScelto, false); // Aggiunge l'equipaggiamento all'inventario come non equipaggiato
            }else {
                System.out.println("Ritorna al menu");
            }
        }
    }else {
            System.out.println("Eroe non trovato. Ritorna al menu.");
        }

            
    }
    public void letturadatiFile() {
        String FileEroe= "src/eroi.txt";
        String FileNemico= "src/nemici.txt";
        String FileSpell="src/spells.txt";
        String FileEquip="src/equip.txt";
        String FileQuest="src/quest.txt";
        leggiFileSpells(FileSpell);
        


        try (BufferedReader br = new BufferedReader(new FileReader(FileEroe))) {
            String riga;
            // Legge riga per riga finché il file non è vuoto
            while ((riga = br.readLine()) != null) {
                if (riga.trim().isEmpty()) continue; // Salta righe vuote

                String[] dati = riga.split("\\|");
               
                er= new Eroe(dati[0], dati[1], Float.parseFloat(dati[2]), Float.parseFloat(dati[3]), Float.parseFloat(dati[4]));
                listaEroi.add(er);
                er.addSpells(listaSpells);
            }

        } catch (Exception e) {
            System.err.println("Errore nel caricamento file eroe: " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FileNemico))) {
            String riga;
            // Legge riga per riga finché il file non è vuoto
            while ((riga = br.readLine()) != null) {
                if (riga.trim().isEmpty()) continue; // Salta righe vuote

                String[] dati = riga.split("\\|");
               
                n= new Nemico(dati[0], dati[1], Float.parseFloat(dati[2]), Float.parseFloat(dati[3]), Float.parseFloat(dati[4]));
                listaNemici.add(n);
            }

        } catch (Exception e) {
            System.err.println("Errore nel caricamento file nemico: " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FileEquip))) {
            String riga;
            // Legge riga per riga finché il file non è vuoto
            while ((riga = br.readLine()) != null) {
                if (riga.trim().isEmpty()) continue; // Salta righe vuote

                String[] dati = riga.split("\\|");
               
                eq= new Equip(dati[0], dati[1],dati[2], Integer.parseInt(dati[3]), Integer.parseInt(dati[4]), Integer.parseInt(dati[5]));
                listaEquip.add(eq);
                
            }

        } catch (Exception e) {
            System.err.println("Errore nel caricamento file equip: " + e.getMessage());
        }

         try (BufferedReader br = new BufferedReader(new FileReader(FileQuest))) {
            String riga;
            // Legge riga per riga finché il file non è vuoto
            while ((riga = br.readLine()) != null) {
                if (riga.trim().isEmpty()) continue; // Salta righe vuote

                String[] dati = riga.split("\\|");
                
                q = QuestFactory.crea(dati[0], dati[1], dati[2], dati[3], Integer.parseInt(dati[4]), trovaEquip(dati[5]));
                listaQuest.add(q);
                System.out.println("Add quest:"+q.getTitolo());
            }

        } catch (Exception e) {
            System.err.println("Errore nel caricamento file eroe: " + e.getMessage());
        }
    }

    public Equip creaEquip() {
            System.out.println("Inserisci il nome dell'equipaggiamento:");
            input.nextLine(); 
            String nomeEq = input.nextLine();
            System.out.println("Inserisci il tipo di equipaggiamento (es. arma, armatura):");
            String tipoEq= input.nextLine();
            int sceltaClasse;
            System.out.println("Specifica la classe per cui è destinato l'equipaggiamento:\n1-Guerriero\n2-Mago\n3-Arciere");
            sceltaClasse= input.nextInt();
            String classeEq;
            switch(sceltaClasse){
                case 1:
                    classeEq="Guerriero";
                    break;
                case 2:
                    classeEq="Mago";
                    break;
                case 3:
                    classeEq="Arciere";
                    break;
                default:
                    System.out.println("Scelta non valida. Impostazione classe Universale.");
                    classeEq="Universale";
                    break;
                }
            System.out.println("Inserisci il bonus ATK:");
            int atkBonus= input.nextInt();
            System.out.println("Inserisci il bonus HP:");
            int hpBonus= input.nextInt();
            System.out.println("Inserisci il bonus MP:");
            int mpBonus= input.nextInt();
            input.nextLine();
            Equip eq=new Equip(nomeEq, tipoEq, classeEq, atkBonus, hpBonus, mpBonus);
            listaEquip.add(eq); 
            System.out.println("Equipaggiamento creato: " + eq.getNome() + " Tipo: " + eq.getTipo());
            return eq;
    }
    public Quest creaQuest(){
        System.out.println("Inserisci nome della quest:");
        String titoloQuest = input.nextLine();
        System.out.println("Inserisci la descrizione:");
        String descQuest = input.nextLine();
        System.out.println("Inserire l'obbiettivo della quest:[KILL/....]");
        String obQuest= input.nextLine();
        System.out.println("Inserire il Target[LIVELLO/<NEMICO>]:");
        String targetQuest = input.nextLine();
        System.out.println("Inserire il numuero per il target scelto:");
        int numTargQuest = input.nextInt();
        System.out.println("Premio casuale o specifico? [-1/Numero equipaggiamento]");
        stampaEquip();
        int premioScelto = input.nextInt();
        Random random = new Random();
    
        if(premioScelto==-1){
        premioScelto=random.nextInt(listaEquip.size());
        }
        Equip eq=listaEquip.get(premioScelto);
        Quest q = QuestFactory.crea(titoloQuest,descQuest,obQuest, targetQuest, numTargQuest,eq); 
        listaQuest.add(q);
        return q;
    }
    public void salvaSuFile(Entita en) {
        String FileEroe= "src/eroi.txt";
        String FileNemico= "src/nemici.txt";
        String File;
        if (en.classe.equals("goblin") || en.classe.equals("boss")) {
            File = FileNemico;
        }
        else  File = FileEroe;
        
        try (FileWriter fw = new FileWriter(File, true); 
             PrintWriter out = new PrintWriter(fw)) {
            
            String dati = String.format("%s|%s|%.1f|%.1f|%.1f\n",
                en.getNome(),
                en.getClasse(),
                en.getStats().get("hp"),
                en.getStats().get("atk"),
                en.getStats().get("mp")
            );
            
            out.println(dati);
            System.out.println("Salvataggio completato per: " + en.getNome());
            
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }
    public void salvaSuFileEq(Equip eq) {
        String FileEquip= "src/equip.txt";
        
        try (FileWriter fw = new FileWriter(FileEquip, true); // 'true' aggiunge al file senza cancellare il vecchio
             PrintWriter out = new PrintWriter(fw)) {
            
            // Recuperiamo i dati dall'equipaggiamento
            String dati = String.format("%s|%s|%s|%d|%d|%d\n",
                eq.getNome(),
                eq.getTipo(),
                eq.getClasse(),
                eq.getAtkBonus(),
                eq.getHpBonus(),
                eq.getMpBonus()
            );
            
            out.println(dati);
            System.out.println("Salvataggio completato per l'equipaggiamento: " + eq.getNome());
            
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio dell'equipaggiamento: " + e.getMessage());
        }
    }    
    public void salvasuFileQ(Quest q) {
        String FileQuest= "src/quest.txt";
        
        try (FileWriter fw = new FileWriter(FileQuest, true); // 'true' aggiunge al file senza cancellare il vecchio
             PrintWriter out = new PrintWriter(fw)) {
            
            // Recuperiamo i dati dalla quest
            String dati = String.format("%s|%s|%s|%s|%d|%s\n",
                q.getTitolo(),
                q.getDescrizione(),
                q.getObbiettivoDesc(),
                q.getTarget(),
                q.getQuantita(),
                q.getPremio().getNome()
            );
            
            out.println(dati);
            System.out.println("Salvataggio completato per la quest: " + q.getTitolo());
            
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio della quest: " + e.getMessage());
        }
    }   
    public void mostraScheda() {
        System.out.println("Di quale personaggio vuoi vedere la scheda?");
        String nomeEroe = input.nextLine();
        Entita e= trovaEntita(nomeEroe);
        if (e != null) {
            e.mostraSchedaEntita();
        }else {
            System.out.println("Personaggio non trovato.");
        }     
    }
    public void stampaEquip() {
        int i=0;
        for (Equip eq : listaEquip) {
            System.out.println(i + ")" + eq.getNome() + " (Tipo: " + eq.getTipo() + ", ATK: " + eq.getAtkBonus() + ", HP: " + eq.getHpBonus() + ", MP: " + eq.getMpBonus() + ")");
            i++;
        }
    }
    public void stampaVeloce() {
        System.out.println("Lista Eroi:");
        for (Eroe e : listaEroi) {
            System.out.println("- " + e.getNome() + " (Classe: " + e.getClasse() + ")");
         }
        System.out.println("Lista Nemici:");
        for (Nemico n : listaNemici) {
            System.out.println("- " + n.getNome() + " (Classe: " + n.getClasse() + ")");
         }
    }
    public void avviaCombattimento() {
        System.out.println("Quale eroe vuoi fare combattere?");
        String nomeEroe = input.nextLine();
        Entita e= trovaEntita(nomeEroe);
        Eroe ero=(Eroe) e;
        if (ero != null) {
            System.out.println("Con quale nemico vuoi far combattere " + e.getNome() + "?");;
            Nemico n= listaNemici.get(0); // Per semplicità, selezioniamo il primo nemico della lista
            if (n != null) {
                c=new Combattimento(ero, n);
                c.avviaCombattimento();
            } else {
                System.out.println("Nemico non trovato.");
            }
        } else {
            System.out.println("Eroe non trovato.");
        }
        
    }
    public void accettaQuest(){
    System.out.println("Quale Eroe vuole accettare la Quest?");    
    String nomeEroe=input.nextLine();
    er=trovaEroe(nomeEroe);   
    er.accettaQuest(listaQuest); 
    }
    public Equip trovaEquip(String dati){
        for(Equip q: listaEquip){
            if(q.getNome().equals(dati)) return q;
        }
        return null;
    }
    public Entita trovaEntita(String nome) {
        for (Eroe e : listaEroi) {
            if (e.getNome().equals(nome)) {
                return e;
            }
        }
        for (Nemico n : listaNemici) {
            if (n.getNome().equals(nome)) {
                return n;
            }
        }
        return null; 
    }
    
    public void leggiFileSpells(String FileSpell) {
        try (BufferedReader br = new BufferedReader(new FileReader(FileSpell))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                if (riga.trim().isEmpty()) continue; // Salta righe vuote
                Spell s= SpellFactory.crea(riga.split("\\|"));
                /**String[] dati = riga.split("\\|");
                String nome = dati[0];
                String desc = dati[1];
                String classe = dati[2];
                float costo = Float.parseFloat(dati[3]);
                String tipo = dati[4]; // DAMAGE o BUFF
                float valore = Float.parseFloat(dati[5]);
                Spell s;
            if (tipo.equalsIgnoreCase("DAMAGE")) {
                s = new SpellDmg(nome, desc, classe, costo, tipo, valore);
            } else {
                s = new SpellBuff(nome, desc, classe, costo, tipo, valore);
            }
            listaSpells.add(s);     
        }**/
                listaSpells.add(s);     
            }
        } catch (Exception e) {
            System.err.println("Errore nel caricamento file: " + e.getMessage());
        }

        
    }
    public void mostraMenu() {

    int scelta;

    do {
        System.out.println("\n=== MENU PRINCIPALE ===");
        System.out.println("1 - Inizia Avventura");
        System.out.println("2 - Mostra scheda personaggio");
        System.out.println("3 - Mostra tutti i personaggi");
        System.err.println("4 - Mostra tutti gli equipaggiamenti");
        System.out.println("5 - Scegli Equipaggiamento per Eroe");
        System.out.println("0 - Esci");
        System.out.print("Scelta: ");

        scelta = input.nextInt();
        input.nextLine(); // Consumare la nuova linea rimasta nel buffer

        switch (scelta) {  
            case 1:
                selezioneAttore();
                break;
            case 2:
                mostraScheda();
                break;
            case 3:
                stampaVeloce();
                break;
            case 4:
                System.out.println("Lista Equipaggiamenti:");
                stampaEquip();
                break;
            case 5:
                scegliEquipaggiamento();
                break;
            case 0:
                System.out.println("Uscita dal gioco.");
                break;
            default:
                System.out.println("Scelta non valida.");
        }

    } while (scelta != 0);
}
}