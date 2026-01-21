public class QuestFactory {
    public static Quest crea(String titolo, String desc, String tipo, String target, int qta, Equip e) {
        ObiettivoStrategy ob;
        if (tipo.equalsIgnoreCase("KILL")) {
            ob = new ObiettivoKill(target, qta);
        } else {
            ob = new ObiettivoLivello(Integer.parseInt(target)); // da modificare con livello o altri tipi di obiettivi
        }
        return new Quest(titolo, desc, ob, e);
    }
}
