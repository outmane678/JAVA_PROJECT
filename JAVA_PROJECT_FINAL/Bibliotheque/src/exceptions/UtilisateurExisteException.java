package exceptions;
// Exception lorsque l'utilisateur existe déjà
public class UtilisateurExisteException extends Exception {
    public UtilisateurExisteException(String message) {
        super(message);
    }
}