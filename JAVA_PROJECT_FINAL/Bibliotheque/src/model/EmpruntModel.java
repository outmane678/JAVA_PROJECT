package model;

import exceptions.EmpruntExisteException;
import exceptions.EmpruntNonTrouveException;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmpruntModel implements EmpruntModelInterface {
    private List<Emprunt> emprunts;
    private String filePath;
    private List<Utilisateur> utilisateurs;
    private List<Livre> livres;

    // Constructeur de la classe EmpruntModel
    public EmpruntModel(String filePath, List<Utilisateur> utilisateurs, List<Livre> livres) {
        this.filePath = filePath;
        this.utilisateurs = utilisateurs;
        this.livres = livres;
        this.emprunts = new ArrayList<>();
        chargerEmprunts();  // Charger les emprunts depuis le fichier au démarrage
    }

    // Ajouter un emprunt
    @Override
    public void ajouterEmprunt(Emprunt emprunt) throws EmpruntExisteException {
        // Vérification si l'ID de l'emprunt existe déjà
        boolean empruntExiste = emprunts.stream()
                                        .anyMatch(e -> e.getId() == emprunt.getId());

        if (empruntExiste) {
            throw new EmpruntExisteException("Un emprunt avec cet ID existe déjà. Veuillez utiliser un autre ID.");
        } else {
            // Ajouter l'emprunt uniquement si l'ID est unique
            emprunts.add(emprunt);
            System.out.println("Emprunt ajouté : " + emprunt);
            sauvegarderEmprunts();  // Sauvegarder après ajout
        }
    }

    // Afficher les emprunts
    @Override
    public void afficherEmprunts() {
        if (emprunts.isEmpty()) {
            System.out.println("Aucun emprunt.");
        } else {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            for (Emprunt emprunt : emprunts) {
                System.out.println("Emprunt ID: " + emprunt.getId() +
                                   ", Livre ID: " + emprunt.getLivre().getId() +
                                   ", Utilisateur ID: " + emprunt.getUtilisateur().getId() +
                                   ", Date Emprunt: " + emprunt.getDateEmprunt().format(dateFormat) +
                                   ", Date Retour: " + emprunt.getDateRetour().format(dateFormat));
            }
        }
    }

    // Modifier un emprunt (par exemple, modification de la date de retour)
    @Override
    public void modifierEmprunt(int id, LocalDate nouvelleDateRetour) throws EmpruntNonTrouveException {
        for (Emprunt emprunt : emprunts) {
            if (emprunt.getId() == id) {
                emprunt.setDateRetour(nouvelleDateRetour); // Modification de la date de retour
                System.out.println("Emprunt modifié : " + emprunt);
                sauvegarderEmprunts(); // Sauvegarder après modification
                return;
            }
        }
        throw new EmpruntNonTrouveException("Emprunt avec ID " + id + " non trouvé.");
    }

    // Supprimer un emprunt
    @Override
    public void supprimerEmprunt(int id) {
        boolean removed = emprunts.removeIf(emprunt -> emprunt.getId() == id);
        if (removed) {
            sauvegarderEmprunts();  // Sauvegarder après suppression
        } else {
            System.out.println("Emprunt avec ID " + id + " non trouvé.");
        }
    }

    // Sauvegarder les emprunts dans un fichier CSV
    private void sauvegarderEmprunts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("id_Emprunt,id_livre,id_utilisateur,date_emprunt,date_retour");
            writer.newLine();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for (Emprunt emprunt : emprunts) {
                writer.write(emprunt.getId() + "," +
                             emprunt.getLivre().getId() + "," +
                             emprunt.getUtilisateur().getId() + "," +
                             emprunt.getDateEmprunt().format(dateFormat) + "," +
                             emprunt.getDateRetour().format(dateFormat));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des emprunts : " + e.getMessage());
        }
    }

    // Charger les emprunts depuis un fichier CSV
    private void chargerEmprunts() {
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
                // Lire et ignorer la première ligne (en-têtes)
                reader.readLine();
    
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 5) {
                        int id = Integer.parseInt(parts[0]);
                        int idLivre = Integer.parseInt(parts[1]);
                        int idUtilisateur = Integer.parseInt(parts[2]);
                        LocalDate dateEmprunt = LocalDate.parse(parts[3], dateFormat);
                        LocalDate dateRetour = LocalDate.parse(parts[4], dateFormat);
    
                        Livre livreEmprunt = findLivreById(idLivre);
                        Utilisateur utilisateurEmprunt = findUtilisateurById(idUtilisateur);
    
                        if (livreEmprunt != null && utilisateurEmprunt != null) {
                            emprunts.add(new Emprunt(id, livreEmprunt, utilisateurEmprunt, dateEmprunt, dateRetour));
                            System.out.println("Emprunt chargé: " + id);
                        }
                    }
                }
            } catch (IOException | java.time.format.DateTimeParseException e) {
                System.out.println("Erreur lors du chargement des emprunts : " + e.getMessage());
            }
        }
    }

    // Recherche d'un livre par son ID
    private Livre findLivreById(int id) {
        return livres.stream()
                     .filter(livre -> livre.getId() == id)
                     .findFirst()
                     .orElse(null);
    }

    // Recherche d'un utilisateur par son ID
    private Utilisateur findUtilisateurById(int id) {
        return utilisateurs.stream()
                           .filter(utilisateur -> utilisateur.getId() == id)
                           .findFirst()
                           .orElse(null);
    }

    // Getter pour la liste des emprunts
    @Override
    public List<Emprunt> getEmprunts() {
        return emprunts;
    }
}
