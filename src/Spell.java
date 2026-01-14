public class Spell {
    String nome;
    String descrizione;
    String classe;
    float costoMP;


    public Spell(String nome, String descrizione, String classe, float costoMP) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.classe = classe;
        this.costoMP = costoMP;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getClasse() {
        return classe;
    }

    public float getCostoMP() {
        return costoMP;
    }
}