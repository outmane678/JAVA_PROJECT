package test;

import controller.EmpruntController;
import view.EmpruntView;

public class MainApp {
    public static void main(String[] args) {
        // Démarrer l'application
        // Cette ligne crée le contrôleur, qui initialise à la fois la vue et les modèles nécessaires
        EmpruntController controller = new EmpruntController();

        // La vue est déjà liée au contrôleur dans le constructeur de EmpruntController,
        // mais pour être sûr que la vue soit visible, on la rend explicite ici.
        EmpruntView view = controller.getView();  // Récupérer la vue depuis le contrôleur
        view.setVisible(true);
    }
}
