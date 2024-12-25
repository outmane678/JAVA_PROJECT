package model;
import java.time.LocalDate;

public class Emprunt {
    private int id;
    private Livre livre;
    private Utilisateur utilisateur;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    // constructeur par défaut
    public Emprunt() {}

    // constructeur par initialisation
    public Emprunt(int id, Livre livre, Utilisateur utilisateur, LocalDate dateEmprunt, LocalDate dateRetour) {
        this.id = id;
        this.livre = livre;
        this.utilisateur = utilisateur;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    // getters et setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livre getLivre() {
        return this.livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public LocalDate getDateEmprunt() {
        return this.dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return this.dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    // méthode toString
    @Override
    public String toString(){
        return "Emprunt ID: " + id + ", Livre: " + livre.getTitre() + ", Utilisateur: " + utilisateur.getNom() +
               ", Date Emprunt: " + dateEmprunt + ", Date Retour: " + dateRetour;
    }

}
