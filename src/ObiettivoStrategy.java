public interface ObiettivoStrategy {
    boolean isCompletato();
    // Passiamo un oggetto o dei parametri per permettere alla strategia di controllare
    void aggiorna(Object evento); 
    String getDescrizione();
    String getObiettivoDesc();
}