package model;

import exceptions.LivreDejaExisteException;
import exceptions.LivreNonTrouveException;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LivreModel implements LivreModelInterface {
    private List<Livre> livres;
    private final String cheminFichier;

    // Constructeur
    public LivreModel(String cheminFichier) {
        this.cheminFichier = cheminFichier;
        this.livres = new ArrayList<>();
        chargerLivresDepuisCSV(); // Charger les livres depuis le fichier CSV
    }

    public void ajouterLivre(Livre livre) throws LivreDejaExisteException {
        // Vérifier si un livre avec le même ID existe déjà
        for (Livre l : livres) {
            if (l.getId() == livre.getId()) {
                throw new LivreDejaExisteException("Un livre avec l'ID " + livre.getId() + " existe déjà. L'ajout est annulé.");
            }
        }
        livres.add(livre);
        System.out.println("Livre ajouté : " + livre);
        sauvegarderLivresDansCSV(); // Sauvegarder dans le fichier CSV après ajout
    }

    public void supprimerLivre(int id) throws LivreNonTrouveException {
        Iterator<Livre> iterator = livres.iterator();
        boolean livreSupprime = false;

        while (iterator.hasNext()) {
            Livre livre = iterator.next();
            if (livre.getId() == id) {
                iterator.remove();  // Suppression sécurisée avec Iterator
                System.out.println("Livre supprimé : " + livre);
                livreSupprime = true;
                break;
            }
        }

        if (!livreSupprime) {
            throw new LivreNonTrouveException("Livre avec l'ID " + id + " non trouvé.");
        } else {
            sauvegarderLivresDansCSV(); // Sauvegarder après suppression
        }
    }

    public void modifierLivre(int id, String nvtitre, String nvauteur, int nvannneepub, String nvgenre) throws LivreNonTrouveException {
        boolean livreTrouve = false;
        for (Livre livre : livres) {
            if (livre.getId() == id) {
                livre.setTitre(nvtitre);
                livre.setAuteur(nvauteur);
                livre.setAnneePublication(nvannneepub);
                livre.setGenre(nvgenre);
                System.out.println("Livre modifié : " + livre);
                livreTrouve = true;
                break;
            }
        }
        if (!livreTrouve) {
            throw new LivreNonTrouveException("Livre avec l'ID " + id + " non trouvé.");
        } else {
            sauvegarderLivresDansCSV(); // Sauvegarder après modification
        }
    }

    public void afficher() {
        if (livres.isEmpty()) {
            System.out.println("Aucun livre dans la bibliothèque.");
        } else {
            System.out.println("Liste des livres dans la bibliothèque :");
            for (Livre livre : livres) {
                System.out.println(livre);
            }
        }
    }

    public void chargerLivresDepuisCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            // Ignorer la première ligne (les en-têtes du CSV)
            br.readLine();

            while ((ligne = br.readLine()) != null) {
                String[] valeurs = ligne.split(",");
                if (valeurs.length == 5) {
                    int id = Integer.parseInt(valeurs[0]);
                    String titre = valeurs[1];
                    String auteur = valeurs[2];
                    int anneePub = Integer.parseInt(valeurs[3]);
                    String genre = valeurs[4];

                    Livre livre = new Livre(id, titre, auteur, anneePub, genre);
                    livres.add(livre); // Ajouter le livre à la liste
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sauvegarderLivresDansCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(cheminFichier))) {
            // Écrire l'en-tête
            bw.write("id,titre,auteur,anneepub,genre");
            bw.newLine();

            // Écrire chaque livre
            for (Livre livre : livres) {
                bw.write(livre.getId() + "," + livre.getTitre() + "," + livre.getAuteur() + "," + livre.getAnneePublication() + "," + livre.getGenre());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Livre> getLivres() {
        return this.livres;
    }
    // Méthode de recherche pour les livres, incluant la recherche par année
    public List<Livre> rechercherLivres(String query) {
    List<Livre> resultats = new ArrayList<>();
    for (Livre livre : livres) {
        // Recherche dans le titre, l'auteur, le genre, l'ID et l'année
        if (livre.getTitre().toLowerCase().contains(query.toLowerCase()) ||
            livre.getAuteur().toLowerCase().contains(query.toLowerCase()) ||
            livre.getGenre().toLowerCase().contains(query.toLowerCase()) ||
            String.valueOf(livre.getId()).contains(query) ||
            String.valueOf(livre.getAnneePublication()).contains(query)) {  // Recherche par année
            resultats.add(livre);
        }
    }
    return resultats;
}


}
