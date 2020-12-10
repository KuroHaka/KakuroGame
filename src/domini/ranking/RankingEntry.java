package domini.ranking;

public class RankingEntry extends Ranking{
    //TODO
    private String id;
    private int tempsRecord;

    ///// Constructora /////
    protected RankingEntry(String id, int tempsRecord) {
        this.id = id;
        this.tempsRecord = tempsRecord;
    }

    ///// Consultores /////

    protected String getId() {
        return this.id;
    }

    protected int getTempsRecord() {
        return this.tempsRecord;
    }

    public void printEntry() {
        System.out.println("ID = " + id);
        System.out.println("Durada = " + tempsRecord);
    }

    ///// Modificadores /////

    protected void modificarTemps(int temps) {
        this.tempsRecord = temps;
    }

    protected void modificarEntry(String idN, int temps) {
        this.id = idN;
        this.tempsRecord = temps;
    }

}
