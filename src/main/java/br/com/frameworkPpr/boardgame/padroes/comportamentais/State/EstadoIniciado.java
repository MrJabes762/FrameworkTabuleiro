package main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.state;

public class EstadoIniciado implements EstadoJogo {
    private final ContextoJogo contexto;

    public EstadoIniciado(ContextoJogo contexto) {
        this.contexto = contexto;
    }

    @Override
    public void iniciarJogo() {
        System.out.println("O jogo já está iniciado.");
    }
    @Override
    public void pausarJogo() {
        System.out.println("O jogo foi pausado.");
        contexto.setEstadoAtual(new EstadoPausado(contexto));
    }
    @Override
    public void finalizarJogo() {
        System.out.println("O jogo foi finalizado.");
        contexto.setEstadoAtual(new EstadoFinalizado(contexto));
    }
    @Override  
    public void reiniciarJogo(){
        System.out.println("Jogo reiniciado.");
        contexto.setEstadoAtual(new EstadoIniciado(contexto));
    }
}
