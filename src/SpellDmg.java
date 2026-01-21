import java.util.Map;

public class SpellDmg extends Spell {

    public SpellDmg(String nome, String descrizione, String classe, float costoMP, String tipo, float valore) {
        super(nome, descrizione, classe, costoMP, tipo, valore);
    }
    @Override
    public void applicaEffetto(Map<String, Float> statsPg, Map<String, Float> statsNemico) {
        statsNemico.put("hp", statsNemico.get("hp") - valore);
        System.out.println(nome + " infligge " + valore + " danni.");
    }
    @Override
    public String getDescCombat(){
        return String.format("%s (Costo MP: %.1f, Danno: %.1f)", 
                         nome, costoMP, valore);
    }
}
