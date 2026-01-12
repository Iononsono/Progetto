import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gamesystem {
    private Entita e;
    private List<Eroe> listaEroi = new ArrayList<>();
    private List<Nemico> listaNemici = new ArrayList<>();
    private Scanner input = new Scanner(System.in);
    public Gamesystem() {

    }
        
    public void start() {
        System.out.println("Benvenuto in Bit And Blade!");
        selezioneAttore();
        stampaVeloce();
        mostraScheda();
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
                System.out.println("Funzione di creazione Nemico non ancora implementata.");
                System.out.println("Inserisci il nome del nemico:");
                input.nextLine(); 
                nome = input.nextLine();
                System.out.println("Specifica se il nemico è un boss o un nemico semplice:");
                classe= input.nextLine();
                Nemico n=new Nemico(nome, classe);
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

            
            
        }
    }

    public void mostraScheda() {

        System.out.println("Di quale Entità vuoi vedere la scheda?");
        String nomeEroe = input.nextLine();

        for (Eroe e : listaEroi) {
            if (e.getNome().equals(nomeEroe)) {
                e.mostraSchedaEntita();
                return;
            }
        }   

        System.out.println("Eroe non trovata.");
    }

    public void stampaVeloce() {
        System.out.println("Lista Eroi:");
         for (Eroe e : listaEroi) {
        System.out.println("- " + e.getNome() + " (Classe: " + e.getClasse() + ")");
         }
    
    //listaNemici.forEach(System.out::println);
    }
}