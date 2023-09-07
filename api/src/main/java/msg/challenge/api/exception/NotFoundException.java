package msg.challenge.api.exception;

//@ResponseStatus(
//        value = HttpStatus.NOT_FOUND,
//        reason = "mensagem não encontrada"
//)
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("mensagem não encontrada");
    }
}
