package main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.proxy;

import java.util.Map;
import main.java.br.com.frameworkPpr.boardgame.game.Casa;
import main.java.br.com.frameworkPpr.boardgame.game.Peca;
import main.java.br.com.frameworkPpr.boardgame.game.Posicao;
import main.java.br.com.frameworkPpr.boardgame.game.TabuleiroInterface;
import main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.state.ContextoJogo;
import main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.state.EstadoIniciado;
import main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.state.EstadoJogo;
import main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.flyweight.CasaFlyweight;
import main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.flyweight.PecaFlyweight;

/**
 * A classe TabuleiroProxySecurity é um exemplo da aplicação do padrão de projeto estrutural Proxy.
 * Ele é aplicado para que antes de realizar qualquer ação,
 * O tabuleiro passa a responsabilidade para ele, quem instancia o tabuleiro não sabe, 
 * 
 * E tambem há a aplicação o Singleton, isso garante que haja apenas 
 * uma instância do proxy no mesmo pacote.
 * Nessa casse terá apenas a lógica validação de inicio do jogo, assim como, 
 * demais validações antes de execurar as operações 
 * Toda a logica de movimentação é de responsabiidade da classe Tabuleiro
 */
public class TabuleiroProxySecurity implements TabuleiroInterface {
    private int linhas;
    private int colunas;
    private static TabuleiroProxySecurity proxyInstance;
    private ContextoJogo contexto;


    private TabuleiroProxySecurity() {
        // setJogoIniciado(false);
        this.contexto = new ContextoJogo();
    }

    public static TabuleiroProxySecurity getInstance() {
        synchronized (TabuleiroProxySecurity.class) {
            if (getProxyInstance() == null) {
                setProxyInstance(new TabuleiroProxySecurity());
            }
        }
        return getProxyInstance();
    }

    // Método que será comumente utiizado antes de realizar cada ação 
    private void verificarEstadoPermitido() {
        if (!(contexto.getEstadoAtual() instanceof EstadoIniciado)) {
            throw new IllegalStateException("Ação não permitida no estado atual do jogo: " + contexto.getEstadoAtual().getClass().getSimpleName());
        }
    }

    // Método que Validará possiveis casos esperados ao inicializar as Casas
    @Override
    public void inicializarCasas(int linhas, int colunas) {
        if (this.linhas > 0 && this.colunas > 0) {
            throw new IllegalStateException("O tabuleiro já foi inicializado.");
        }
        if (linhas <= 0 || colunas <= 0) {
            throw new IllegalArgumentException("Número de linhas e colunas deve ser maior que zero.");
        }
        setLinhas(linhas);
        setColunas(colunas);
    }

    private boolean posicaoValida(int linha, int coluna) {
        return linha >= 0 && linha < getLinhas() && coluna >= 0 && coluna < getColunas();
    }

    private boolean posicaoValida(Posicao posicao) {
        return posicaoValida(posicao.getLinha(), posicao.getColuna());
    }

    // Método que irá Validará posiveis casos esperados ao colocar uma peça
    public void colocarPeca(Peca peca, Posicao posicao, Map<Posicao,Casa> casas) {
        verificarEstadoPermitido();
        if (casas.get(posicao) == null) {
            throw new IllegalArgumentException("Posição inválida: " + posicao);
        }
        if (casas.get(posicao).estaOcupada()) {
            throw new IllegalArgumentException("A posição já está ocupada: " + posicao);
        }
        if (!posicaoValida(posicao)) {
            throw new IllegalArgumentException("Posição inválida: " + posicao);
        }
    }

    // Método que irá Validará posiveis casos esperados ao mover uma peça
    @Override
    public void moverPeca(Posicao origem, Posicao destino, Map<Posicao,Casa> casas) {
        verificarEstadoPermitido();
        if (!posicaoValida(origem) || !posicaoValida(destino)) {
            throw new IllegalArgumentException("Posição de origem ou destino inválida.");
        }
        if (casas.get(origem) == null || casas.get(destino) == null) {
            throw new IllegalArgumentException("Posição de origem ou destino inválida.");
        }
        if (!casas.get(origem).estaOcupada()) {
            throw new IllegalArgumentException("Não há peça na posição de origem: " + origem);
        }
        if (casas.get(destino).estaOcupada()) {
            throw new IllegalStateException("A posição de destino já está ocupada!");
        }
    }

    // Método que irá Validará posiveis casos esperados ao remover uma peça
    @Override
    public void removerPeca(Posicao posicao) {
        verificarEstadoPermitido();
        if (!posicaoValida(posicao)) {
            throw new IllegalArgumentException("Posição inválida: " + posicao);
        }
    }

    public void iniciarJogo()
    {
        contexto.iniciarJogo();
    }

    public void pausarJogo()
    {
        contexto.pausarJogo();
    }

    public void finalizarJogo()
    {
        contexto.finalizarJogo();
    }

    public void reiniciarJogo()
    {
        contexto.reiniciarJogo();
    }

    public EstadoJogo getEstadoAtual()
    {
        return contexto.getEstadoAtual();
    }

    public void setEstado(EstadoJogo estado)
    {
        contexto.setEstadoAtual(estado);
    }

    // metodos de acesso
    private int getColunas() {
        return colunas;
    }

    private void setColunas(int colunas) {
        this.colunas = colunas;
    }

    public ContextoJogo getContexto() {
        return contexto;
    }

    private int getLinhas() {
        return linhas;
    }

    private void setLinhas(int linhas) {
        this.linhas = linhas;
    }
    private static TabuleiroProxySecurity getProxyInstance() {
        return proxyInstance;
    }

    private static void setProxyInstance(TabuleiroProxySecurity proxyInstance) {
        TabuleiroProxySecurity.proxyInstance = proxyInstance;
    }

    @Override
    public void colocarPeca(PecaFlyweight peca, Posicao posicao, Map<Posicao, CasaFlyweight> casasFlyweight) {
        // Exemplo de validação: não permitir colocar peça em casa ocupada
        CasaFlyweight casa = casasFlyweight.get(posicao);
        if (casa == null) {
            throw new IllegalArgumentException("Posição inválida para colocar peça: " + posicao);
        }
        if (casa.getPeca() != null) {
            throw new IllegalStateException("Já existe uma peça nesta posição: " + posicao);
        }
        casa.setPeca(peca);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + linhas;
        result = prime * result + colunas;
        result = prime * result + ((contexto == null) ? 0 : contexto.hashCode());
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
        TabuleiroProxySecurity other = (TabuleiroProxySecurity) obj;
        if (linhas != other.linhas)
            return false;
        if (colunas != other.colunas)
            return false;
        if (contexto == null) {
            if (other.contexto != null)
                return false;
        } else if (!contexto.equals(other.contexto))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TabuleiroProxySecurity [linhas=" + linhas + ", colunas=" + colunas + ", contexto=" + contexto + "]";
    }
    
}

