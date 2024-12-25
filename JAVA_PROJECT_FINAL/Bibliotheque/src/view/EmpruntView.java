package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmpruntView extends JFrame {

    // Composants de la vue
    private JTextField idTextField;
    private JTextField livreIdTextField;
    private JTextField utilisateurIdTextField;
    private JTextField dateEmpruntTextField;
    private JTextField dateRetourTextField;
    private JTable empruntsTable;
    private JButton ajouterButton;
    private JButton modifierButton;
    private JButton supprimerButton;

    public EmpruntView() {
        setTitle("Gestion des Emprunts");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialisation des composants
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));

        inputPanel.add(new JLabel("ID Emprunt:"));
        idTextField = new JTextField();
        inputPanel.add(idTextField);

        inputPanel.add(new JLabel("ID Livre:"));
        livreIdTextField = new JTextField();
        inputPanel.add(livreIdTextField);

        inputPanel.add(new JLabel("ID Utilisateur:"));
        utilisateurIdTextField = new JTextField();
        inputPanel.add(utilisateurIdTextField);

        inputPanel.add(new JLabel("Date Emprunt (yyyy-MM-dd):"));
        dateEmpruntTextField = new JTextField();
        inputPanel.add(dateEmpruntTextField);

        inputPanel.add(new JLabel("Date Retour (yyyy-MM-dd):"));
        dateRetourTextField = new JTextField();
        inputPanel.add(dateRetourTextField);

        // Ajouter le panel des champs de saisie à la vue
        add(inputPanel, BorderLayout.NORTH);

        // Tableau pour afficher les emprunts
        empruntsTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Livre ID", "Utilisateur ID", "Date Emprunt", "Date Retour"});
        empruntsTable.setModel(tableModel);

        JScrollPane tableScrollPane = new JScrollPane(empruntsTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        ajouterButton = new JButton("Ajouter");
        buttonPanel.add(ajouterButton);

        modifierButton = new JButton("Modifier");
        buttonPanel.add(modifierButton);

        supprimerButton = new JButton("Supprimer");
        buttonPanel.add(supprimerButton);

        // Ajouter les boutons à la vue
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Accesseurs pour les champs de texte et les boutons
    public JTextField getIdTextField() {
        return idTextField;
    }

    public JTextField getLivreIdTextField() {
        return livreIdTextField;
    }

    public JTextField getUtilisateurIdTextField() {
        return utilisateurIdTextField;
    }

    public JTextField getDateEmpruntTextField() {
        return dateEmpruntTextField;
    }

    public JTextField getDateRetourTextField() {
        return dateRetourTextField;
    }

    public JTable getEmpruntsTable() {
        return empruntsTable;
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
}
