public class Quest {
    private String titolo;
    private String descrizione;
    private ObiettivoStrategy obiettivo; // Riferimento alla strategia
    private boolean completata;
    private Equip premio;
    private boolean premioRiscattato;

    public Quest(String titolo, String descrizione, ObiettivoStrategy obiettivo, Equip premio) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.obiettivo = obiettivo;
        this.completata = false;
        this.premio = premio;
    }

    public void aggiorna(Object evento) {
        if (!completata) {
            obiettivo.aggiorna(evento); // Delega alla strategia
            if (obiettivo.isCompletato()) {
                this.completata = true;
                System.out.println("\n[QUEST] Completata: " + titolo);
            }
        }
    }

    public void stampaQuest(){
        System.out.println("Titolo: "+getTitolo()+" Desc: "+getDescrizione()+" Premio: "+getPremio().getNome());
    }   
    public boolean isCompletata() { 
        return completata; 
    }
    public String getTitolo() {
        return titolo;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public Equip getPremio(){
    return premio;   
    }
    public ObiettivoStrategy getObiettivo(){
        return obiettivo;
    }
    public String getProgressBar() {
        int totale = obiettivo.getTotale();
        int corrente = obiettivo.getCorrente();
        int dimensioneBarra = 10; // Lunghezza della barra in caratteri
    
        // Calcolo della percentuale (evitando divisioni per zero)
        float percentuale = (totale > 0) ? (float) corrente / totale : 0;
        int riempimento = (int) (percentuale * dimensioneBarra);
    
        StringBuilder barra = new StringBuilder("[");
        for (int i = 0; i < dimensioneBarra; i++) {
            if (i < riempimento) barra.append("#"); // Parte completata
            else barra.append("-");                // Parte mancante
        }
        barra.append("] ").append((int)(percentuale * 100)).append("%");
        return barra.toString();
    }
}