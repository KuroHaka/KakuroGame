package domini.usuari;

import java.util.ArrayList;
import java.util.Vector;
import domini.partida.Partida;

public class Usuari {
    private String name;
    private String password; // és un hash
    private Configuracio defecte;
    private Vector<String> id_partides;
    
    //Constructoras
    
    public Usuari() {
        this.name = "nobody";
        this.password = "x";
        this.defecte = new Configuracio(); //Hauriem de posar una configuracio inical per defecte?
        this. id_partides = new Vector<String> ();
    }
    
    public Usuari(String nom, String hashPwd) {
        this.name = nom;
        this.password = hashPwd;
        this.defecte = new Configuracio(); //Hauriem de posar una configuracio inical per defecte?
        this. id_partides = new Vector<String> ();
    }
    
    public Usuari(String nom, String hashPwd, Configuracio c, Vector<String> partides) {
        this.name = nom;
        this.password = hashPwd;
        this.defecte = c;
        this.id_partides = partides;
    }
    
    //Funciones
    
    public void canviarContrasenya(String s) {
        this.password = s;
    }
    
    public void canviarNom(String s) {
        this.name = s;
    }
    
    public void canviarConfigDefecte(int dx, int dy, int nb, int nbe) {
        this.defecte = new Configuracio(dx, dy, nb, nbe);
    }
    
    public void canviarConfigDefecteExplicit(Configuracio c) {
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
    
    public Vector<String> getLlistaIdPartides(){
        return this.id_partides;
    }
    
    public void afegirPartida(String p){
        id_partides.add(p);
    }
    
}
