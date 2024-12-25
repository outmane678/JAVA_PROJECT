package model;

public class Livre {
    private int id;
    private String titre;
    private String auteur;
    private int anneePublication;
    private String genre;

    // constructeur par défaut
    public Livre() {}

    // constructeur par initialisation

    public Livre(int id, String titre, String auteur, int anneePublication, String genre) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.genre = genre;
    }
    
    // getters et setters

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return this.auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getAnneePublication() {
        return this.anneePublication;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }    
    
    // méthode toString
    @Override
    public String toString() {
        return id + ", " + titre + ", " + auteur + ", " + anneePublication + ", " + genre;
    }  
}
