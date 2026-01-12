import java.util.Scanner;

public class Gamesystem {
    private Entita e;
    private Scanner input = new Scanner(System.in);
    public Gamesystem() {

    }
        
    public void start() {
        System.out.println("Benvenuto in Bit And Blade!");
        selezioneAttore();
        mostrascheda();
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
            e = new Entita(nomeEroe, classeEroe);
            e.CreaEroe();//CREAZIONE EROE    
        }
    }

    public void mostrascheda() {

        System.out.println("Di quale Entità vuoi vedere la scheda?");
        //successivamente per cercare tra tutte le entità (for e: in e.listaEntità)
        if (e.getNome().equals(input.nextLine())) {
            e.mostraSchedaEntita();
        } else {
            System.out.println("Entità non trovata.");
        }
    }
}