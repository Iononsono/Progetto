public class ObiettivoLivello implements ObiettivoStrategy {
    private int necessari;
    private int attuali;

    public ObiettivoLivello(int livelloNecessario) {
        this.necessari = livelloNecessario;
        this.attuali = 0;
    }

    @Override
    public int getCorrente() { return attuali; }
    @Override
    public int getTotale() { return necessari; }
    @Override
    public boolean isCompletato() { 
        return attuali >= necessari;
    }
    @Override
    public String getDescrizione() { 
        return "Raggiungi il livello " + necessari + " (" + attuali + "/" + necessari + ")";
    }
    @Override
    public void aggiorna(Object evento) {
        // Se l'evento è un Integer, sovrascriviamo il livello attuale
        if (evento instanceof Integer) {
            this.attuali = (Integer) evento;
            // Mostriamo il progresso solo se non è ancora finita
            if (!isCompletato()) {
                System.out.println("[QUEST] Progresso livello: " + attuali + "/" + necessari);
            }
        } 
    }
    public String getNomeNemicoTarget(){
        return null;
    }
    public Integer getNecessari(){
        return null;
    }
    public String getTipo(){
        return "LEVEL";
    }
    @Override
    public Object getNomeTarget(){
        return necessari;
    }
}
