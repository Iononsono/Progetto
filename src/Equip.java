public class Equip {
    String nome;
    String classe;
    String tipo;// ARMOR o WEAPON
    
    int hpB, mpB, atkB;

    public Equip(String nome, String tipo,String classe, int atkB, int hpB, int mpB) {
        this.nome = nome;
        this.classe = classe;
        this.tipo = tipo;
        this.atkB = atkB;
        this.hpB = hpB;
        this.mpB = mpB;

    }

    public String getNome() {
        return nome;
    }
    public String getClasse() {
        return classe;
    }
    public String getTipo() {
        return tipo;
    }
    public int getAtkBonus() {
        return atkB;
    }
    public int getHpBonus() {
        return hpB;
    }
    public int getMpBonus() {
        return mpB;
    }

    

}
