package main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.builder;

import main.java.br.com.frameworkPpr.boardgame.game.Tabuleiro;

public class TabuleiroDirector {
    private final TabuleiroBuilder builder;

    public TabuleiroDirector(TabuleiroBuilder builder) {
        this.builder = builder;
    }

    public Tabuleiro construir(int largura, int altura) {
        builder.iniciarTabuleiro(largura, altura);
        builder.adicionarCasas();
        builder.adicionarPecas();
        return builder.getResultado();
    }

    public TabuleiroBuilder getBuilder() {
        return builder;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((builder == null) ? 0 : builder.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TabuleiroDirector other = (TabuleiroDirector) obj;
        if (builder == null) {
            if (other.builder != null)
                return false;
        } else if (!builder.equals(other.builder))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TabuleiroDirector [builder=" + builder + "]";
    }

    
}

