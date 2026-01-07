
public  class Eroe   {
    protected String nome;
    protected int classe;
    protected String classeN;
    //protected int stat1, stat2, stat3;

    public Eroe(String nome, int classe) {
        this.nome = nome;
        if (classe ==1 ){
            this.classeN="Mago";
        }else if (classe ==2 ){
            this.classeN="Guerriero";
        }else if (classe ==3 ){
            this.classeN="Arciere";
        }else {
            System.out.println("Classe non valida, impostata come Guerriero di default");
            this.classeN="Guerriero";
        }
        System.out.println("Eroe creato: " + this.nome + " Classe: " + this.classeN);
    
    }
    
}