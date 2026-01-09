public class Eroe extends Entita {
    protected String classeN;

    public Eroe(String nome, int classe) {
        // Chiama il costruttore di Entita per inizializzare nome e classe
        super(nome, classe);

        
        if (classe == 2) {
            this.classeN = "Mago";
        } else if (classe == 3) {
            this.classeN = "Arciere";
        } else {
            // Se l'utente ha inserito 2, o qualsiasi altro numero (0, 4, 5...)
            if (classe != 1) {
                System.out.println("Input '" + classe + "' non valido. Classe impostata su Guerriero.");
            }
            this.classeN = "Guerriero";
            this.classe = 1; // Forziamo il valore numerico per coerenza
        }

        System.out.println("Eroe creato correttamente: " + this.nome + " [" + this.classeN + "]");
    }

    
}