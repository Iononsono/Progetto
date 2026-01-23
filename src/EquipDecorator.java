public abstract class EquipDecorator implements Statistiche {
    protected Statistiche componente;

    public EquipDecorator(Statistiche componente) {
        this.componente = componente;
    }
    @Override
    public float getHp() {
        return componente.getHp();
    }

    @Override
    public float getAtk() {
        return componente.getAtk();
    }

    @Override
    public float getMp() {
        return componente.getMp();
    }
}