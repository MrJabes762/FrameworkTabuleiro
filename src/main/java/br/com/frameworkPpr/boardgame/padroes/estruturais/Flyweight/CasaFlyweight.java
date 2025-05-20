package main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.flyweight;

public interface CasaFlyweight {
    String getCor();
    int getNumero();
    boolean estaOcupada();
    Object getPeca();
    void setPeca(PecaFlyweight peca);
}
