package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UtilisateurView extends JFrame {
    private JTable utilisateursTable;
    private JTextField idTextField, nomTextField, roleTextField;
    private JButton ajouterButton, modifierButton, supprimerButton;

    public UtilisateurView() {
        setTitle("Gestion des Utilisateurs");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialisation des composants
        utilisateursTable = new JTable(new DefaultTableModel(new Object[]{"ID", "Nom", "Rôle"}, 0));
        JScrollPane scrollPane = new JScrollPane(utilisateursTable);

        // Champs de texte pour ajouter/modifier un utilisateur
        idTextField = new JTextField(20);
        nomTextField = new JTextField(20);
        roleTextField = new JTextField(20);

        // Boutons
        ajouterButton = new JButton("Ajouter");
        modifierButton = new JButton("Modifier");
        supprimerButton = new JButton("Supprimer");

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idTextField);
        inputPanel.add(new JLabel("Nom:"));
        inputPanel.add(nomTextField);
        inputPanel.add(new JLabel("Rôle:"));
        inputPanel.add(roleTextField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ajouterButton);
        buttonPanel.add(modifierButton);
        buttonPanel.add(supprimerButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        setVisible(true);
    }

    // Méthodes pour accéder aux composants de la vue
    public JTable getUtilisateursTable() {
        return utilisateursTable;
    }

    public JTextField getIdTextField() {
        return idTextField;
    }

    public JTextField getNomTextField() {
        return nomTextField;
    }

    public JTextField getRoleTextField() {
        return roleTextField;
    }

    public JButton getAjouterButton() {
        return ajouterButton;
    }

    public JButton getModifierButton() {
        return modifierButton;
    }

    public JButton getSupprimerButton() {
        return supprimerButton;
    }

    public DefaultTableModel getTableModel() {
        return (DefaultTableModel) utilisateursTable.getModel();
    }
}
