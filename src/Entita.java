public  class Entita {
    protected String nome;

    protected int classe;
    Eroe er = new Eroe(nome, classe);
    public Entita(String nome, int classe) {
        this.nome = nome;
        this.classe = classe;
        System.out.println("Eroe creato: " + er.nome + " Classe: " + er.classeN);
    }
    
    public String getNome() {
        return er.nome;
    }

    public String getClasse() {
        return er.classeN;
    }
    
}