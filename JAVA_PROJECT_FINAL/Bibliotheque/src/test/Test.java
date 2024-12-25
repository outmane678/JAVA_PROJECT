package test;

import controller.LivreController;
import view.LivreView;

public class Test {
    public static void main(String[] args) {
        // Créer une instance de LivreController
        LivreController controller = new LivreController();

        // Rendre la vue visible
        LivreView view = controller.getView(); // Utilisation de la méthode getView() pour accéder à la vue
        view.setVisible(true);
    }
}
