package test;

import controller.UtilisateurController;
import view.UtilisateurView;

public class TestUtilisateur {
    public static void main(String[] args) {
        // Créer une instance de UtilisateurController
        UtilisateurController controller = new UtilisateurController();

        // Rendre la vue visible
        UtilisateurView view = controller.getView(); // Utilisation de la méthode getView() pour accéder à la vue
        view.setVisible(true);
    }
}
