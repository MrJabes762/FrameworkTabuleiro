package main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.factory.exceptions;

public class CriacaoPecaException extends RuntimeException {
    public CriacaoPecaException() {
        super();
    }

    public CriacaoPecaException(String message) {
        super(message);
    }

    public CriacaoPecaException(String message, Throwable cause) {
        super(message, cause);
    }

    public CriacaoPecaException(Throwable cause) {
        super(cause);
    }
}
