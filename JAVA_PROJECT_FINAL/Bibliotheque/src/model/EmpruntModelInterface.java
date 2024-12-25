package model;

import exceptions.EmpruntExisteException;
import exceptions.EmpruntNonTrouveException;
import java.time.LocalDate;
import java.util.List;

public interface EmpruntModelInterface {
    // Méthode pour ajouter un emprunt
    void ajouterEmprunt(Emprunt emprunt) throws EmpruntExisteException;

    // Méthode pour afficher tous les emprunts
    void afficherEmprunts();

    // Méthode pour modifier un emprunt (ex: changer la date de retour)
    void modifierEmprunt(int id, LocalDate nouvelleDateRetour) throws EmpruntNonTrouveException;

    // Méthode pour supprimer un emprunt
    void supprimerEmprunt(int id);

    // Méthode pour obtenir la liste des emprunts
    List<Emprunt> getEmprunts();
}
