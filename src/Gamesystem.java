import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gamesystem {
    private Eroe er;
    private Nemico n;
    private Spell s;
    private List<Eroe> listaEroi = new ArrayList<>();
    private List<Nemico> listaNemici = new ArrayList<>();
    private List<Spell> listaSpells = new ArrayList<>();
    private Scanner input= new Scanner(System.in);
    private String[] dati;
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
           System.out.println("Cosa vuoi fare?\n1-Crea Nemico\n"+
            "2-Crea Equipaggiamento");
           scelta = input.nextInt();
           switch(scelta){
    
            case 1:
                System.out.println("Inserisci il nome del nemico:");
                input.nextLine(); 
                nome = input.nextLine();
                System.out.println("Specifica se il nemico è un boss o un goblin:");
                classe= input.nextLine();
                Nemico n=new Nemico(nome, classe);
                listaNemici.add(n);
                salvaSuFile(n);
                System.out.println("Nemico creato: " + n.getNome() + " Classe: " + n.getClasse());
                break;
            default:
                System.out.println("Scelta non valida.");
                break;
           }
        } else {
            System.out.println("Benvenuto Player!");
            System.out.println("Inserisci il nome del tuo eroe:");
            input.nextLine(); 
            nome = input.nextLine();   
            System.out.println("Nome eroe scelto: " + nome);
            System.out.println("Scegli la classe del tuo eroe: 1-Guerriero 2-Mago 3-Arciere");
            classe = input.nextLine();
            
            //e = new Entita(nomeEroe, classeEroe);
            //CREAZIONE EROE e.CreaEroe();   
            Eroe er = new Eroe(nome, classe);
            listaEroi.add(er);
            salvaSuFile(er);

            
            
        }
    }

    public void letturadatiFile() {
        String FileEroe= "src/eroi.txt";
        String FileNemico= "src/nemici.txt";
        String FileSpell="src/spells.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(FileSpell))) {
            String riga;
            // Legge riga per riga finché il file non è vuoto
            while ((riga = br.readLine()) != null) {
                if (riga.trim().isEmpty()) continue; // Salta righe vuote
                sceltaspell(riga);
                
            }

        } catch (Exception e) {
            System.err.println("Errore nel caricamento file: " + e.getMessage());
        }

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
            System.err.println("Errore nel caricamento file: " + e.getMessage());
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
            System.err.println("Errore nel caricamento file: " + e.getMessage());
        }
    }

    public void salvaSuFile(Entita en) {
        String FileEroe= "src/eroi.txt";
        String FileNemico= "src/nemici.txt";
        String File;
        if (en.classe.equals("goblin") || en.classe.equals("boss")) {
            File = FileNemico;
        }
        else  File = FileEroe;
        
        try (FileWriter fw = new FileWriter(File, true); // 'true' aggiunge al file senza cancellare il vecchio
             PrintWriter out = new PrintWriter(fw)) {
            
            // Recuperiamo i dati dall'eroe (coerente con UC1 e UC12)
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
        
    
    public void mostraScheda() {

        System.out.println("Di quale personaggio vuoi vedere la scheda?");
        String nomeEroe = input.nextLine();
        Eroe e= trovaEroe(nomeEroe);
        Nemico n= trovaNemico(nomeEroe);
        if (e != null) {
            e.mostraSchedaEntita();
        }else if (n!=null){
            n.mostraSchedaEntita();
        }else {
            System.out.println("Personaggio non trovato.");
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
    
    //listaNemici.forEach(System.out::println);
    }
    public void avviaCombattimento() {
        System.out.println("Quale eroe vuoi fare combattere?");
        String nomeEroe = input.nextLine();
        Eroe e= trovaEroe(nomeEroe);
        if (e != null) {
            System.out.println("Con quale nemico vuoi far combattere " + e.getNome() + "?");;
            Nemico n= listaNemici.get(0); // Per semplicità, selezioniamo il primo nemico della lista
            if (n != null) {
                c=new Combattimento(e, n);
                
                // Implementa la logica del combattimento qui
            } else {
                System.out.println("Nemico non trovato.");
            }
        } else {
            System.out.println("Eroe non trovato.");
        }
        
    }

    public Eroe trovaEroe(String nome) {
        for (Eroe e : listaEroi) {
            if (e.getNome().equals(nome)) {
                return e;
            }
        }
        return null; 
    }
    public Nemico trovaNemico(String nome) {
        for (Nemico n : listaNemici) {
            if (n.getNome().equals(nome)) {
                return n;
            }
        }
        return null; 
    }
    public void sceltaspell(String riga) {
        String nome = dati[0].trim();
        String desc = dati[1].trim();
        String classe = dati[2].trim();
        float costo = Float.parseFloat(dati[3].trim());
        String tipo = dati[4].trim(); // DAMAGE o BUFF
        float valore = Float.parseFloat(dati[5].trim());

        Spell s;
        if (tipo.equalsIgnoreCase("DAMAGE")) {
            s = new SpellDmg(nome, desc, classe, costo, tipo, valore);
        } else {
            s = new SpellBuff(nome, desc, classe, costo, tipo, valore);
        }
        listaSpells.add(s);
    }
    public void mostraMenu() {

    int scelta;

    do {
        System.out.println("\n=== MENU PRINCIPALE ===");
        System.out.println("1 - Crea personaggio");
        System.out.println("2 - Mostra scheda personaggio");
        System.out.println("3 - Avvia combattimento");
        System.out.println("4 - Mostra tutti i personaggi");
        System.out.println("0 - Esci");
        System.out.print("Scelta: ");

        scelta = Integer.parseInt(input.nextLine());

        switch (scelta) {  
            case 1:
                selezioneAttore();
                break;
            case 2:
                mostraScheda();
                break;
            case 3:
                avviaCombattimento();
                break;
            case 4:
                stampaVeloce();
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