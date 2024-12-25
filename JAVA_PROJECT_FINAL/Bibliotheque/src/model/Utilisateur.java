package model;

public class Utilisateur {
    private int id;
    private String nom;
    private String role;

    // constructeur par défaut
    public Utilisateur() {}
    
    // constructeur par initialisation
    public Utilisateur(int id, String nom, String role) {
        this.id = id;
        this.nom = nom;
        this.role = role;
    }

    // getters et setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // méthode toString
    @Override
    public String toString() {
        return id + " , "+ nom +" , "+ role ;
    }

}
