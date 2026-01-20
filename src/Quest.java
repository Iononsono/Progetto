public class Quest {
    private String titolo;
    private String descrizione;
    private ObiettivoStrategy obiettivo; // Riferimento alla strategia
    private boolean completata;
    private Equip premio;
    private boolean premioRiscattato;

    public Quest(String titolo, String descrizione, String obiettivo, String target, int quantita, Equip premio) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        if(obiettivo.equals("KILL")){
            this.obiettivo = new ObiettivoKill(target, quantita);
        }
        this.completata = false;
        this.premioRiscattato = false;
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

    public void stampaQuest()
    {

          System.out.println("Titolo: "+getTitolo()+" Desc: "+getDescrizione()+" Ogg: "+getObbiettivoDesc()+" Quantita: "+getQuantita()+" Target: "+getTarget()+" premio: "+getPremio().getNome());
    }
    public String getStato() {
        return titolo + " - " + obiettivo.getDescrizione() + (completata ? " [PRONTA]" : "");
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
    public String getObbiettivoDesc() {
        return obiettivo.getObiettivoDesc();
    }
    public String getTarget() {
        if(obiettivo instanceof ObiettivoKill) {
            return ((ObiettivoKill) obiettivo).getNomeNemicoTarget();
        }
        return "";
    }
    public Integer getQuantita() {
        if(obiettivo instanceof ObiettivoKill) {
            return ((ObiettivoKill) obiettivo).getNecessari();
        }
        return null;
    }
    public Equip getPremio(){
    return premio;   
    }
}