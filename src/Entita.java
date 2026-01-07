public  class Entita {
    protected String nome;

    protected int classe;

    public Entita(String nome, int classe) {
        this.nome = nome;
        this.classe = classe;
    }

    public void CreaEroe(String nome, int classe){
       Eroe er= new Eroe(nome, classe);
    }

    public String getNome() {
        return nome;
    }

    public int getClasse() {
        return classe;
    }
}