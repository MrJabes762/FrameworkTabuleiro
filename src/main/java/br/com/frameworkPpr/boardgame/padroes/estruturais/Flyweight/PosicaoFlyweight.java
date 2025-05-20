package main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.flyweight;

public class PosicaoFlyweight {
    private final int linha;
    private final int coluna;

    public PosicaoFlyweight(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    @Override  
    public boolean equals(Object obj) {  
        if (this == obj) return true;  
        if (obj == null || getClass() != obj.getClass()) return false;  
        PosicaoFlyweight that = (PosicaoFlyweight) obj;  
        return linha == that.linha && coluna == that.coluna;  
    }

    @Override
    public int hashCode()
    {
        return 31 * linha + coluna;
    }

    @Override
    public String toString() {
        return "PosicaoFlyweight [linha=" + linha + ", coluna=" + coluna + "]";
    }
    
}
