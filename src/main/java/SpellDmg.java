import java.util.Map;

public class SpellDmg extends Spell {

    public SpellDmg(String nome, String descrizione, String classe, float costoMP, String tipo, float valore) {
        super(nome, descrizione, classe, costoMP, tipo, valore);
    }
    @Override
    public boolean applicaEffetto(Map<String, Float> statsPg, Map<String, Float> statsNemico) {
        if (statsPg.get("mp") >= getCostoMP()) {//check del mana
            statsPg.put("mp", statsPg.get("mp") - getCostoMP());
            System.out.printf("[%s] usa %s!\n", nome, getNome());
            statsNemico.put("hp", statsNemico.get("hp") - getValore());
            System.out.println(getNome() + " infligge " + getValore() + " danni.");
            return true;
        }
        return false;
    }
    
    @Override
    public String getDescCombat(){
        return String.format("%s (Costo MP: %.1f, Danno: %.1f)", 
                         nome, costoMP, valore);
    }
}
