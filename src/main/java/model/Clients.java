package model;

public class Clients {
    private String num;
    private String nom;
    private String pnom;
    private String loc;
    private String pays;
public Clients(){

}

    public Clients(String num, String nom, String pnom, String loc, String pays) {
        this.num = num;
        this.nom = nom;
        this.pnom = pnom;
        this.loc = loc;
        this.pays = pays;
    }
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPnom() {
        return pnom;
    }

    public void setPnom(String pnom) {
        this.pnom = pnom;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

}



