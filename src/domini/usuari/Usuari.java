package domini.usuari;

public class Usuari {
    private String name;
    private String password;
    private Configuracio defecte;
    
    
    //Constructoras
    
    public Usuari() {
        this.name = "";
        this.password = "";
        this.defecte = new Configuracio(); //Hauriem de posar una configuracio inical per defecte?
    }
    
    public Usuari(String n, String p) {
        this.name = n;
        this.password = p;
        this.defecte = new Configuracio();
    }
    
    //Funciones
    
    public void canviarContrasenya(String s) {
        this.password = s;
    }
    
    public void canviarNom(String s) {
        this.name = s;
    }
    
    public void canviarConfigDefecte(int dx, int dy, int nb) {
        this.defecte = new Configuracio(dx, dy, nb);
    }
    
    public void canviarConfigDefecte2(Configuracio c) {
        this.defecte = c;
    }
    
    public String getNom() {
        return this.name;
    }
    
    public String getContrasenya() {
        return this.password;
    }
    
    public Configuracio getConfigDefecte() {
        return this.defecte;
    }
    
    public void print() {
        System.out.println("Nom: " + this.name);
        System.out.println("Contrasenya: " + this.password);
        defecte.print();
    }
    
}
