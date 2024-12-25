package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LivreView extends JFrame {
    private JTable livresTable;
    private JTextField idTextField, titreTextField, auteurTextField, anneeTextField, genreTextField, rechercheTextField;
    private JButton ajouterButton, modifierButton, supprimerButton, rechercherButton;  // Ajouter un bouton de recherche

    public LivreView() {
        setTitle("Gestion des Livres");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialisation des composants
        livresTable = new JTable(new DefaultTableModel(new Object[]{"ID", "Titre", "Auteur", "Année", "Genre"}, 0));
        JScrollPane scrollPane = new JScrollPane(livresTable);

        // Champs de texte pour ajouter/modifier un livre
        idTextField = new JTextField(20);
        titreTextField = new JTextField(20);
        auteurTextField = new JTextField(20);
        anneeTextField = new JTextField(20);
        genreTextField = new JTextField(20);

        // Champ de recherche
        rechercheTextField = new JTextField(20);

        // Boutons
        ajouterButton = new JButton("Ajouter");
        modifierButton = new JButton("Modifier");
        supprimerButton = new JButton("Supprimer");
        rechercherButton = new JButton("Rechercher");  // Bouton de recherche

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));  // 6 lignes maintenant (5 pour les livres + 1 pour la recherche)
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idTextField);
        inputPanel.add(new JLabel("Titre:"));
        inputPanel.add(titreTextField);
        inputPanel.add(new JLabel("Auteur:"));
        inputPanel.add(auteurTextField);
        inputPanel.add(new JLabel("Année:"));
        inputPanel.add(anneeTextField);
        inputPanel.add(new JLabel("Genre:"));
        inputPanel.add(genreTextField);
        inputPanel.add(new JLabel("Recherche:"));
        inputPanel.add(rechercheTextField);  // Champ de recherche

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ajouterButton);
        buttonPanel.add(modifierButton);
        buttonPanel.add(supprimerButton);
        buttonPanel.add(rechercherButton);  // Ajouter le bouton de recherche

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        setVisible(true);
    }

    // Méthodes pour accéder aux composants de la vue
    public JTable getLivresTable() {
        return livresTable;
    }

    public JTextField getIdTextField() {
        return idTextField;
    }

    public JTextField getTitreTextField() {
        return titreTextField;
    }

    public JTextField getAuteurTextField() {
        return auteurTextField;
    }

    public JTextField getAnneeTextField() {
        return anneeTextField;
    }

    public JTextField getGenreTextField() {
        return genreTextField;
    }

    public JTextField getRechercheTextField() {  // Méthode pour obtenir le champ de recherche
        return rechercheTextField;
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

    public JButton getRechercherButton() {  // Méthode pour obtenir le bouton de recherche
        return rechercherButton;
    }

    public DefaultTableModel getTableModel() {
        return (DefaultTableModel) livresTable.getModel();
    }
}
