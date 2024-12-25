package controller;

import exceptions.UtilisateurExisteException;
import exceptions.UtilisateurNonTrouveException;
import model.Utilisateur;
import model.UtilisateurModel;
import view.UtilisateurView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UtilisateurController {
    private UtilisateurModel model;
    private UtilisateurView view;

    public UtilisateurController() {
        this.model = new UtilisateurModel("C:\\Users\\DELL\\Desktop\\utilisateurs.csv");
        this.view = new UtilisateurView();

        // Ajouter les listeners aux boutons
        view.getAjouterButton().addActionListener(e -> ajouterUtilisateur());
        view.getModifierButton().addActionListener(e -> modifierUtilisateur());
        view.getSupprimerButton().addActionListener(e -> supprimerUtilisateur());

        // Charger les utilisateurs dans la vue
        chargerUtilisateurs();
    }

    private void chargerUtilisateurs() {
        List<Utilisateur> utilisateurs = model.getUtilisateurs();
        DefaultTableModel tableModel = (DefaultTableModel) view.getUtilisateursTable().getModel();
        tableModel.setRowCount(0); // Effacer les lignes actuelles
        for (Utilisateur utilisateur : utilisateurs) {
            tableModel.addRow(new Object[]{utilisateur.getId(), utilisateur.getNom(), utilisateur.getRole()});
        }
    }

    private void ajouterUtilisateur() {
        try {
            int id = Integer.parseInt(view.getIdTextField().getText());
            String nom = view.getNomTextField().getText();
            String role = view.getRoleTextField().getText();
            
            // Ajouter l'utilisateur
            Utilisateur utilisateur = new Utilisateur(id, nom, role);
            model.ajouterUtilisateur(utilisateur);
            JOptionPane.showMessageDialog(view, "Utilisateur ajouté avec succès !");
            chargerUtilisateurs();
            
            // Réinitialisation des champs
            view.getIdTextField().setText("");
            view.getNomTextField().setText("");
            view.getRoleTextField().setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Erreur de saisie : ID doit être un entier.");
        } catch (UtilisateurExisteException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    private void modifierUtilisateur() {
        int selectedRow = view.getUtilisateursTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un utilisateur.");
            return;
        }

        try {
            int idSelectionne = (int) view.getUtilisateursTable().getValueAt(selectedRow, 0);
            int idSaisi = Integer.parseInt(view.getIdTextField().getText());

            if (idSaisi != idSelectionne) {
                JOptionPane.showMessageDialog(view, "Erreur : Vous ne pouvez pas modifier l'ID de l'utilisateur sélectionné.");
                return;
            }

            String nom = view.getNomTextField().getText();
            String role = view.getRoleTextField().getText();

            model.modifierUtilisateur(idSelectionne, nom, role);
            JOptionPane.showMessageDialog(view, "Utilisateur modifié avec succès !");
            
            chargerUtilisateurs();
            
            // Réinitialisation des champs
            view.getIdTextField().setText("");
            view.getNomTextField().setText("");
            view.getRoleTextField().setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Erreur de saisie : ID doit être un entier.");
        } catch (UtilisateurNonTrouveException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    private void supprimerUtilisateur() {
        int selectedRow = view.getUtilisateursTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un utilisateur.");
            return;
        }

        int id = (int) view.getUtilisateursTable().getValueAt(selectedRow, 0);
        try {
            model.supprimerUtilisateur(id);
            JOptionPane.showMessageDialog(view, "Utilisateur supprimé avec succès !");
            chargerUtilisateurs();
        } catch (UtilisateurNonTrouveException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    // Ajout de la méthode getView()
    public UtilisateurView getView() {
        return this.view;
    }
}
