package model;

import exceptions.LivreDejaExisteException;
import exceptions.LivreNonTrouveException;
import java.util.List;

public interface LivreModelInterface {
    // Méthodes qui peuvent lancer des exceptions
    void ajouterLivre(Livre livre) throws LivreDejaExisteException;
    void modifierLivre(int id, String nvTitre, String nvAuteur, int nvAnneePublication, String nvGenre) throws LivreNonTrouveException;
    void supprimerLivre(int id) throws LivreNonTrouveException;
    
    // Méthodes sans exceptions
    void afficher();
    void chargerLivresDepuisCSV();
    void sauvegarderLivresDansCSV();
    List<Livre> rechercherLivres(String query);
    // Accesseur
    List<Livre> getLivres();
}
