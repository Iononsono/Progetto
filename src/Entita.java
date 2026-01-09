public  class  Entita {
    protected String nome;
    protected int classe;
    protected Eroe er; // Dichiariamo solo la variabile, senza "new" qui
    //protected BaseStats bs;

    public Entita(String nome, int classe) {
         this.nome=nome;
        this.classe=classe;
    }


    public void CreaEroe() {
       
        er = new Eroe(nome, classe);
    }

    public String getNome() {
        return er.nome;
    }

    public String getClasse() {
        return er.classeN;
    }
}