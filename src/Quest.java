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
}