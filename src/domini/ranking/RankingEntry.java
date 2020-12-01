package domini.ranking;

public class RankingEntry extends Ranking{
    //TODO
    private int id;
    private int tempsRecord;
    
    ///// Constructora /////
    protected RankingEntry(int id, int tempsRecord) {
        this.id = id;
        this.tempsRecord = tempsRecord;
    }
    
    ///// Consultores /////
    
    protected int getId() {
        return id;
    }
    
    protected int getTempsRecord() {
        return tempsRecord;
    }
    
    public void printEntry() {
        System.out.println("ID = " + id);
        System.out.println("Durada = " + tempsRecord);
    }
    
    ///// Modificadores /////
    
    protected void modificarTemps(int temps) {
        this.tempsRecord = temps;
    }
    
}
