package model;

import java.util.List;

import exceptions.UtilisateurExisteException;
import exceptions.UtilisateurNonTrouveException;

public interface UtilisateurModelInterface {

    // Méthode pour ajouter un utilisateur
    void ajouterUtilisateur(Utilisateur utilisateur) throws UtilisateurExisteException;

    // Méthode pour modifier un utilisateur
    void modifierUtilisateur(int id, String nvNom, String nvRole) throws UtilisateurNonTrouveException;

    // Méthode pour supprimer un utilisateur
    void supprimerUtilisateur(int id) throws UtilisateurNonTrouveException;

    // Méthode pour afficher la liste des utilisateurs
    void afficher();

    // Méthode pour charger les utilisateurs depuis un fichier CSV
    void chargerUtilisateursDepuisCSV();

    // Méthode pour sauvegarder les utilisateurs dans un fichier CSV
    void sauvegarderUtilisateursDansCSV();

    // Méthode pour obtenir la liste des utilisateurs
    List<Utilisateur> getUtilisateurs();
}
