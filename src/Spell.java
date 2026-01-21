import java.util.Map;

public abstract class Spell {
    String nome;
    String descrizione;
    String classe;
    float costoMP;
    String tipo;
    float valore;


    public Spell(String nome, String descrizione, String classe, float costoMP, String tipo, float valore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.classe = classe;
        this.costoMP = costoMP;
        this.tipo = tipo;
        this.valore = valore;
    }
    public abstract void applicaEffetto(Map<String, Float> statsUtilizzatore, Map<String, Float> statsBersaglio);

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
    public String getTipo() {
        return tipo;
    }
    
    public float getValore() {
        return valore;
    }
    public abstract String getDescCombat();
}