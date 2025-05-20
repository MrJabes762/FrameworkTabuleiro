package main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.state;

public interface EstadoJogo {
    void iniciarJogo();
    void pausarJogo();
    void finalizarJogo();
    void reiniciarJogo();
}
