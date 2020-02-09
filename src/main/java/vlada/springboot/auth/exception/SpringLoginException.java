package vlada.springboot.auth.exception;

public class SpringLoginException extends RuntimeException {
    public SpringLoginException(String poruka) {super(poruka); }
}
