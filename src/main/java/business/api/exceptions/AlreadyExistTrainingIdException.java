package business.api.exceptions;

public class AlreadyExistTrainingIdException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Ya existe la clase de entrenamiento";

    public static final int CODE = 1;

    public AlreadyExistTrainingIdException() {
        this("");
    }

    public AlreadyExistTrainingIdException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
