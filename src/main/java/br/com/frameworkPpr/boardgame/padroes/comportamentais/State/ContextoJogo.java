package main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.state;

public class ContextoJogo {
    private EstadoJogo estadoAtual;

    public ContextoJogo() {
        this.estadoAtual = new EstadoIniciado(this);
    }

    public void setEstadoAtual(EstadoJogo novoEstado)
    {
        this.estadoAtual = novoEstado;
    }

    public void iniciarJogo() {
        estadoAtual.iniciarJogo();
    }

    public void pausarJogo() {
        estadoAtual.pausarJogo();
    }

    public void reiniciarJogo(){
        estadoAtual.reiniciarJogo();
    }

    public void finalizarJogo() {
        estadoAtual.finalizarJogo();
    }

    public EstadoJogo getEstadoAtual() {
        return estadoAtual;
    }
}
