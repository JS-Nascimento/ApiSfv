package dev.jstec.apisfv.exception;

public class InvalidPasswordException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPasswordException() {
        super("Senha inválida");
    }
    
}