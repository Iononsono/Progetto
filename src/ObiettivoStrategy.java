public interface ObiettivoStrategy {
    void aggiorna(Object evento);
    boolean isCompletato();
    String getDescrizione(); // Descrizione (es: "Uccidi Orchi")
    int getCorrente();         // Progresso attuale
    int getTotale();
}