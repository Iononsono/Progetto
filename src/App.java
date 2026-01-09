import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String nomeEroe;
        int classeEroe;
        System.out.println("Hello, World!");
        System.out.println("Benvenuto in Bit And Blade!");
        System.out.println("Sei admin o player?(0 o 1)"); 

       Scanner input = new Scanner(System.in);
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
            Eroe e = new Eroe(nomeEroe, classeEroe);
            //Entita e = new Entita(nomeEroe, classeEroe);
       }
    
       input.close();
    }
}
