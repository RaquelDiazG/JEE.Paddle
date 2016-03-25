package business.api.exceptions;

public class AlreadyExistUserIdInTrainingIdException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Usuario ya existente en la clase de entrenamiento";

    public static final int CODE = 1;

    public AlreadyExistUserIdInTrainingIdException() {
        this("", "");
    }

    public AlreadyExistUserIdInTrainingIdException(String user, String training) {
        super(DESCRIPTION + ". " + user + ". " + training, CODE);
    }

}
