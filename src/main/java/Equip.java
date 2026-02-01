public class Equip extends EquipDecorator {
    private String nome;
    private String tipo;
    private String classe;
    private int atkB, hpB, mpB;

    public Equip(Statistiche componente, String nome, String tipo,String classe, int atkB, int hpB, int mpB) {
        super(componente);
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
    @Override
    public float getAtk() {
        return componente.getAtk() + atkB;
    }

    @Override
    public float getHp() {
        return componente.getHp() + hpB;
    }

    @Override
    public float getMp() {
        return componente.getMp() + mpB;
    }

    

}
