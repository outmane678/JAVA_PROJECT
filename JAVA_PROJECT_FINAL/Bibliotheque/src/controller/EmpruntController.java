package controller;

import exceptions.EmpruntExisteException;
import exceptions.EmpruntNonTrouveException;
import model.Emprunt;
import model.EmpruntModel;
import model.Livre;
import model.LivreModel;
import model.Utilisateur;
import model.UtilisateurModel;
import view.EmpruntView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class EmpruntController {
    private EmpruntModel model;
    private EmpruntView view;
    private LivreModel livreModel;
    private UtilisateurModel utilisateurModel;

    public EmpruntController() {
        // Initialisation des modèles
        this.livreModel = new LivreModel("C:\\Users\\DELL\\Desktop\\livres.csv");
        this.utilisateurModel = new UtilisateurModel("C:\\Users\\DELL\\Desktop\\utilisateurs.csv");
        this.model = new EmpruntModel("C:\\Users\\DELL\\Desktop\\emprunts.csv", utilisateurModel.getUtilisateurs(), livreModel.getLivres());
        this.view = new EmpruntView();

        // Ajouter les listeners aux boutons
        view.getAjouterButton().addActionListener(e -> ajouterEmprunt());
        view.getModifierButton().addActionListener(e -> modifierEmprunt());
        view.getSupprimerButton().addActionListener(e -> supprimerEmprunt());

        // Charger les emprunts dans la vue
        chargerEmprunts();
    }

    private void chargerEmprunts() {
        List<Emprunt> emprunts = model.getEmprunts();
        DefaultTableModel tableModel = (DefaultTableModel) view.getEmpruntsTable().getModel();
        tableModel.setRowCount(0); // Effacer les lignes actuelles
        for (Emprunt emprunt : emprunts) {
            tableModel.addRow(new Object[]{
                    emprunt.getId(),
                    emprunt.getLivre().getId(),
                    emprunt.getUtilisateur().getId(),
                    emprunt.getDateEmprunt(),
                    emprunt.getDateRetour()
            });
        }
    }

    private void ajouterEmprunt() {
        try {
            int id = Integer.parseInt(view.getIdTextField().getText());
            int livreId = Integer.parseInt(view.getLivreIdTextField().getText());
            int utilisateurId = Integer.parseInt(view.getUtilisateurIdTextField().getText());
            LocalDate dateEmprunt = LocalDate.parse(view.getDateEmpruntTextField().getText());
            LocalDate dateRetour = LocalDate.parse(view.getDateRetourTextField().getText());

            // Vérification si l'ID existe déjà
            if (model.getEmprunts().stream().anyMatch(e -> e.getId() == id)) {
                throw new EmpruntExisteException("Erreur : Un emprunt avec cet ID existe déjà !");
            }

            // Vérification si le livre et l'utilisateur existent
            Livre livre = livreModel.getLivres().stream()
                    .filter(l -> l.getId() == livreId)
                    .findFirst()
                    .orElseThrow(() -> new EmpruntNonTrouveException("Erreur : Livre introuvable avec cet ID !"));

            Utilisateur utilisateur = utilisateurModel.getUtilisateurs().stream()
                    .filter(u -> u.getId() == utilisateurId)
                    .findFirst()
                    .orElseThrow(() -> new EmpruntNonTrouveException("Erreur : Utilisateur introuvable avec cet ID !"));

            // Ajout de l'emprunt
            Emprunt emprunt = new Emprunt(id, livre, utilisateur, dateEmprunt, dateRetour);
            model.ajouterEmprunt(emprunt);
            JOptionPane.showMessageDialog(view, "Emprunt ajouté avec succès !");
            chargerEmprunts();
            reinitialiserChamps();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Erreur de saisie : ID, Livre ID, Utilisateur ID doivent être des entiers.");
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(view, "Erreur de format de date : Utilisez le format yyyy-MM-dd.");
        } catch (EmpruntExisteException | EmpruntNonTrouveException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    private void modifierEmprunt() {
        int selectedRow = view.getEmpruntsTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un emprunt.");
            return;
        }
    
        try {
            int id = (int) view.getEmpruntsTable().getValueAt(selectedRow, 0);
            String newDateRetour = JOptionPane.showInputDialog(view, "Nouvelle date de retour (yyyy-MM-dd) :");
    
            LocalDate dateRetour = LocalDate.parse(newDateRetour);
            // Assuming modifierEmprunt can throw EmpruntNonTrouveException
            model.modifierEmprunt(id, dateRetour); 
            JOptionPane.showMessageDialog(view, "Emprunt modifié avec succès !");
            chargerEmprunts();
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(view, "Erreur de format de date : Utilisez le format yyyy-MM-dd.");
        } catch (EmpruntNonTrouveException e) {
            // Handle the specific exception for Emprunt not found
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }
    

    private void supprimerEmprunt() {
        int selectedRow = view.getEmpruntsTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un emprunt.");
            return;
        }

        int id = (int) view.getEmpruntsTable().getValueAt(selectedRow, 0);
        model.supprimerEmprunt(id);
        JOptionPane.showMessageDialog(view, "Emprunt supprimé avec succès !");
        chargerEmprunts();
    }

    private void reinitialiserChamps() {
        view.getIdTextField().setText("");
        view.getLivreIdTextField().setText("");
        view.getUtilisateurIdTextField().setText("");
        view.getDateEmpruntTextField().setText("");
        view.getDateRetourTextField().setText("");
    }

    public EmpruntView getView() {
        return this.view;
    }
}
