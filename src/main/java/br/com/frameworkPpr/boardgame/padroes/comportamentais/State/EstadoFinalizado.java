package main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.state;

public class EstadoFinalizado implements EstadoJogo{
    private final ContextoJogo contexto;

    public EstadoFinalizado(ContextoJogo contexto) {
        this.contexto = contexto;
    }

    @Override
    public void iniciarJogo(){
        throw  new IllegalStateException("O jogo foi finalizado.");
    }

    @Override
    public void pausarJogo(){
        throw  new IllegalStateException("O jogo ja está finalizado.");
    }

    @Override
    public void reiniciarJogo() {
        // Não faz nada, pois o jogo está finalizado
        System.out.println("Não é possível reiniciar. O jogo está finalizado.");
    }

    @Override
    public void finalizarJogo() {
        // O jogo já está finalizado, não faz nada
        System.out.println("O jogo já está finalizado.");
    }
}
