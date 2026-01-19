public class ObiettivoKill implements ObiettivoStrategy {
    private String nomeNemicoTarget;
    private int necessari;
    private int attuali;

    public ObiettivoKill(String nomeNemico, int quantita) {
        this.nomeNemicoTarget = nomeNemico;
        this.necessari = quantita;
        this.attuali = 0;
    }

    public void verifica(Object evento) {
        if (evento instanceof String && ((String) evento).equalsIgnoreCase(nomeNemicoTarget)) {
            if (attuali < necessari) attuali++;
        }
    }
    @Override
    public boolean isCompletato() { 
        return attuali >= necessari;
    }
    public void aggiorna(Object evento) {
        if (evento instanceof String && ((String) evento).equalsIgnoreCase(nomeNemicoTarget)) {
            if (attuali < necessari) attuali++;
        }
    }

    public String getDescrizione() { 
        return "Uccidi " + nomeNemicoTarget + " (" + attuali + "/" + necessari + ")";
    }
    public String getObiettivoDesc() {
        return "Uccisione";
    }
    public String getNomeNemicoTarget(){
        return nomeNemicoTarget;
    }
    public Integer getNecessari(){
        return necessari;
    }
}


