import java.util.Scanner;
public abstract class Entita {
    protected String nome;
    Scanner input = new Scanner(System.in);
    protected int classe;
    protected Eroe er; // Dichiariamo solo la variabile, senza "new" qui

    public Entita(String nome, int classe) {
        this.nome=nome;
        this.classe=classe;
    }
    public void CreaEroe() {
        er = new Eroe(nome, classe);
    }
    public String getNome() {
        return nome;
    }
    public void mostraSchedaEntita() {
        System.out.println("Nome: " + er.getNome());
        System.out.println("Classe: " + er.getClasse());
        System.out.println("Statistiche:"+ er.bs.hp + " HP, " + er.bs.atk + " ATK, " + er.bs.mp + " MP");
    }
}