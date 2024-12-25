package exceptions;
// Exception lorsque l'utilisateur n'est pas trouvé
public class UtilisateurNonTrouveException extends Exception {
    public UtilisateurNonTrouveException(String message) {
        super(message);
    }
}