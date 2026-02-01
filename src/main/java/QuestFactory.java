import java.util.Scanner;
public class QuestFactory {
    private static final Scanner input = new Scanner(System.in);
    public static Quest crea(Equip eq) {
        System.out.println("Inserisci nome della quest:");
        String titoloQuest = input.nextLine();
        System.out.println("Inserisci la descrizione:");
        String descQuest = input.nextLine();
        System.out.println("Inserire l'obbiettivo della quest:[KILL/....]");
        String obQuest= input.nextLine();
        System.out.println("Inserire il Target[LIVELLO/<NEMICO>]:");
        String targetQuest = input.nextLine();
        System.out.println("Inserire il numero per il target scelto:");
        int qta;
        try {
            qta = input.nextInt();
            input.nextLine(); // Consumare la nuova linea rimasta
        } catch (Exception e) {
            System.out.println("Input non valido. Impostazione qta 1.");
            qta = 1;
            input.nextLine();
        }
        ObiettivoStrategy ob;
        try {
            if (obQuest.equalsIgnoreCase("KILL")) {
                ob = new ObiettivoKill(targetQuest, qta);
            } else {
                ob = new ObiettivoLivello(Integer.parseInt(targetQuest)); 
            }
        } catch (NumberFormatException e) {
            System.out.println("Target non valido. Creazione obiettivo KILL di default.");
            ob = new ObiettivoKill("goblin", 1);
        }
        return new Quest(titoloQuest, descQuest, ob, eq);
    }
    public static Quest crea(String titolo, String desc, String tipo, String target, int qta, Equip e) {//lettura file
        ObiettivoStrategy ob;
        try {
            if (tipo.equalsIgnoreCase("KILL")) {
                ob = new ObiettivoKill(target, qta);
            } else {
                ob = new ObiettivoLivello(Integer.parseInt(target)); 
            }
        } catch (NumberFormatException ex) {
            System.out.println("Errore nel parsing del target della quest: " + target);
            ob = new ObiettivoKill("goblin", 1);
        }
        return new Quest(titolo, desc, ob, e);
    }
}
