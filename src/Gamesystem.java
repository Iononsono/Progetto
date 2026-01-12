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
        mostraScheda();
        input.close();
    }
  
    public void selezioneAttore() {
        
        String nomeEroe;
        int classeEroe;
        
        System.out.println("Sei admin o player?(0 o 1)"); 
        int Attore = input.nextInt();
        if(Attore == 0){
           System.out.println("Benvenuto Admin!");
        } else {
            System.out.println("Benvenuto Player!");
            System.out.println("Inserisci il nome del tuo eroe:");
            input.nextLine(); 
            nomeEroe = input.nextLine();   
            System.out.println("Nome eroe scelto: " + nomeEroe);
            System.out.println("Scegli la classe del tuo eroe: 1-Guerriero 2-Mago 3-Arciere");
            classeEroe = input.nextInt();
            System.out.println("Classe eroe scelta: " + classeEroe);
            //e = new Entita(nomeEroe, classeEroe);
            //CREAZIONE EROE e.CreaEroe();   
            Eroe er = new Eroe(nomeEroe, classeEroe);
            listaEroi.add(er);

            
            
        }
    }

    public void mostraScheda() {

        System.out.println("Di quale Entità vuoi vedere la scheda?");
        input.nextLine();
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
    listaEroi.forEach(System.out::println);
    //listaNemici.forEach(System.out::println);
    }
}