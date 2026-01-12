import java.util.Scanner;
public abstract class Entita {
    protected String nome;
    Scanner input = new Scanner(System.in);
    protected String classe;
    protected Eroe er; // Dichiariamo solo la variabile, senza "new" qui

    public Entita(String nome, String classe) {
        this.nome=nome;
        this.classe=classe;
        
    }
    /*public void CreaEroe() {
        er = new Eroe(nome, classe);
    }*/
    public String getNome() {
        return nome;
    }
    public String getClasse() {
        return classe;
    }

    public void mostraSchedaEntita() {
       
    }
}