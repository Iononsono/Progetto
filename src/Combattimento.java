import java.util.Map;

public class Combattimento {
    private Eroe e;
    private Nemico n;
    private Map<String, Float> statsEroe;
    private Map<String, Float> statsNemico;
    public Combattimento(Eroe e, Nemico n) {
        this.e = e;
        this.n = n;
        this.statsEroe = e.getStats();
        this.statsNemico = n.getStats();
        avviaCombattimento();
    }
    public void avviaCombattimento() {
        System.out.println("Inizio del combattimento tra " + e.getNome() + " e " + n.getNome());

        while (statsEroe.get("hp") > 0 && statsNemico.get("hp") > 0) {
            // Turno dell'eroe
            float dannoEroe = statsEroe.get("atk");
            statsNemico.put("hp", statsNemico.get("hp") - dannoEroe);
            System.out.println(e.getNome() + " attacca {" + n.getNome() + "} per " + dannoEroe + " danni. HP rimanenti di {" + n.getNome() + "}: " + statsNemico.get("hp"));
            if (statsNemico.get("hp") <= 0) {
                System.out.println(n.getNome() + " è stato sconfitto!");
                break;
            }

            // Turno del nemico
            float dannoNemico = statsNemico.get("atk");
            statsEroe.put("hp", statsEroe.get("hp") - dannoNemico);
            System.out.println(n.getNome() + " attacca {" + e.getNome() + "} per " + dannoNemico + " danni. HP rimanenti di {" + e.getNome() + "}: " + statsEroe.get("hp"));

            if (statsEroe.get("hp") <= 0) {
                System.out.println(e.getNome() + " è stato sconfitto!");
                break;
            }
        }

        System.out.println("Combattimento terminato.");
    }
}
