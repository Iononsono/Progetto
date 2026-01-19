public abstract class EquipDecorator implements Statistiche {
    protected Statistiche componente;

    public EquipDecorator(Statistiche componente) {
        this.componente = componente;
    }
}