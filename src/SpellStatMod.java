import java.util.Map;

public class SpellStatMod extends Spell {
    private String statTarget; // La statistica da modificare (es. "atk", "def", "hp")

    public SpellStatMod(String nome, String descrizione, String classe, float costoMP, String tipo, float valore, String statTarget) {
        super(nome, descrizione, classe, costoMP, tipo, valore);
        this.statTarget = statTarget.toLowerCase().trim();
    }

    @Override
    public boolean applicaEffetto(Map<String, Float> statsPg, Map<String, Float> statsNemico) {
        if (statsPg.get("mp") >= getCostoMP()) {//check del mana
            statsPg.put("mp", statsPg.get("mp") - getCostoMP());
            System.out.printf("[%s] usa %s!\n", nome, getNome());
            if (tipo.equalsIgnoreCase("BUFF")) {
                statsPg.put(statTarget, statsPg.get(statTarget) + valore);
                System.out.println("\n[EFFETTO BUFF]");
                System.out.println(nome + " aumenta " + statTarget.toUpperCase() + " di " + valore + "!");
            } 
        // Logica per il DEBUFF: agisce sempre sul nemico (bersaglio)
            else if (tipo.equalsIgnoreCase("DEBUFF")) {
                statsNemico.put(statTarget, statsNemico.get(statTarget) + valore);
                System.out.println("\n[EFFETTO DEBUFF]:");
                System.out.println(nome + " riduce " + statTarget.toUpperCase() + " del bersaglio!");
            }
            return true;
        }
        return false;
    }
    public String getStatTarget() {
        return statTarget;
    }
    public String getDescCombat(){
        String etichetta = tipo.equalsIgnoreCase("BUFF") ? "Buff" : "Debuff";
        return String.format("%s (Costo MP: %.1f, %s %s: %.1f)", 
                         nome, costoMP, etichetta, statTarget.toUpperCase(), valore);

    }
}