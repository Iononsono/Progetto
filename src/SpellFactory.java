public class SpellFactory {
    public static Spell crea(String[] dati) {
        if (dati == null || dati.length < 6) {
            return null;
        }

        try {
            String nome = dati[0].trim();
            String desc = dati[1].trim();
            String classe = dati[2].trim();
            float costoMP = Float.parseFloat(dati[3].trim());
            String tipo = dati[4].trim().toUpperCase();
            float valore = Float.parseFloat(dati[5].trim());
            switch (tipo) {
                case "DAMAGE":
                    return new SpellDmg(nome, desc, classe, costoMP, tipo, valore);

                case "BUFF":
                case "DEBUFF":
                    String statTarget = (dati.length > 6) ? dati[6].trim().toLowerCase() : "atk";
                    return new SpellStatMod(nome, desc, classe, costoMP, tipo, valore, statTarget);

                default:
                    System.err.println("Tipo spell sconosciuto: " + tipo + " per la spell " + nome);
                    return null;
            }

        } catch (NumberFormatException e) {
            System.err.println("Errore nel formato numerico della spell: " + dati[0]);
            return null;
        }
    }
}