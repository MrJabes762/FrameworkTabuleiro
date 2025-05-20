package main.java.br.com.frameworkPpr.boardgame.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.memento.TabuleiroMemento;
import main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.observer.Observer;
import main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.observer.VitoriaDerrotaObserver;
import main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.observer.exceptions.VitoriaException;
import main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.multiton.TimeMultiton;
import main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.flyweight.PecaFlyweight;
import main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.proxy.TabuleiroProxySecurity;

public class Tabuleiro {
    private Map<Posicao, Casa> casas;
    private VitoriaDerrotaObserver vitoriaDerrotaObserver;
    private static TabuleiroProxySecurity proxySecurityInstance;
    private List<Observer> observadores = new ArrayList<>();
    private int linhas;
    private int colunas;

    public Tabuleiro() {
        setProxySecurityInstance(TabuleiroProxySecurity.getInstance());
        setCasas(new HashMap<>());
        setVitoriaDerrotaObserver(new VitoriaDerrotaObserver(this));
    }

    public void inicializarCasas (int linhas, int colunas){
        this.linhas = linhas;
        this.colunas = colunas;
        getProxySecurityInstance().inicializarCasas(linhas, colunas);
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                Posicao posicao = new Posicao(linha, coluna);
                getCasas().put(posicao, new Casa(posicao));
            }
        }
    }

    public TimeMultiton registrarTime(String nomeTime) {
        return TimeMultiton.getInstance(nomeTime);
    }

    public void colocarPeca(Peca peca, Posicao posicao) {
        getProxySecurityInstance().colocarPeca(peca, posicao, getCasas());
        getCasas().get(posicao).setPeca(peca);
        TimeMultiton.getInstance(peca.getTime().toString()).adicionarPecasAoTime(peca);
        adicionarObservador(peca);
        notificarObservadores("Peça adicionada na posição: " + posicao);
    }
    
    public void moverPeca(Posicao origem, Posicao destino) {
        getProxySecurityInstance().moverPeca(origem, destino, getCasas());
        Casa casaDestino = getCasas().get(destino);
        Casa casaOrigem = getCasas().get(origem);
        Peca pecaMovida = casaOrigem.getPeca();
        casaDestino.setPeca(pecaMovida);
        casaOrigem.setPeca(null);
        notificarObservadores("peça movida: " + origem.getLinha() + "," + origem.getColuna() + " para " + destino.getLinha() + "," + destino.getColuna());
    }

    public void removerPeca(Posicao posicao) throws VitoriaException {
        getProxySecurityInstance().removerPeca(posicao);
        if(getVitoriaDerrotaObserver().verificarVencedor() != null){
           throw new VitoriaException("O jogo já acabou, não é possível remover peças. Já temos um vencedor: " + getVitoriaDerrotaObserver().verificarVencedor().toString());
        }
        TimeMultiton.getInstance(// Recuperar a instancia do time 
                getCasas()// Na casa
                .get(posicao)// Da Posição 
                .getPeca()// Pega a peça
                .getTime()// Dessa peça eu pego o time
                .toString()) // Mas como é string, pego a string
            .removerPecaDoTime(// Chamo o metodo RemoverPecaDoTime do mutiton
                getCasas()// Na casa
                .get(posicao)// Da Posição
                .getPeca());// Pega a peça isso por si só ja remove a peça do time e decrementa a quantidade de peças
        getCasas()
            .get(posicao)
            .setPeca(null);
    }

    public void iniciarJogo (){
        getProxySecurityInstance().iniciarJogo();
    }

    public void finalizarJogo (){
        getProxySecurityInstance().finalizarJogo();
    }

    public String desistir(TimeMultiton timeDesistente) throws VitoriaException{
        if (getVitoriaDerrotaObserver().verificarVencedor() != null) {
            throw new VitoriaException("O jogo já acabou, não é possível desistir.");
        }
        TimeMultiton vencedor = TimeMultiton.getTimeObjetos().stream()
        .filter(time -> !Objects.equals(time,timeDesistente))
        .findFirst()
        .orElseThrow(() -> new IllegalStateException("Nenhum time restante para declarar como vencedor."));
        getVitoriaDerrotaObserver().notificarVencedor(vencedor);
        finalizarJogo();
        return "O time " + vencedor.toString() + " venceu por desistência do time " + timeDesistente.toString() + ".";
    }

    private Map<Posicao, Casa> getCasas() {
        return casas;
    }

    private void setCasas(Map<Posicao, Casa> casas) {
        this.casas = casas;
    }

    public VitoriaDerrotaObserver getVitoriaDerrotaObserver() {
        return vitoriaDerrotaObserver;
    }

    private void setVitoriaDerrotaObserver(VitoriaDerrotaObserver vitoriaDerrotaObserver) {
        this.vitoriaDerrotaObserver = vitoriaDerrotaObserver;
    }

    public static TabuleiroProxySecurity getProxySecurityInstance() {
        return proxySecurityInstance;
    }

    public static void setProxySecurityInstance(TabuleiroProxySecurity proxyInstance) {
        Tabuleiro.proxySecurityInstance = proxyInstance;
    }

    public void adicionarObservador(Observer observador)
    {
        observadores.add(observador);
    }

    public void removerObservador(Observer observador)
    {
        observadores.remove(observador);
    }

    public void notificarObservadores(String evento)
    {
        for (Observer observador : observadores)
        {
            observador.update(evento);
        }
    }

    public TabuleiroMemento criarMemento() {
        // Captura o estado das casas
        return new TabuleiroMemento(new HashMap<>(casas));
    }

    public void restaurarMemento(TabuleiroMemento memento) {
        // Restaura o estado das casas
        setCasas(new HashMap<>(memento.getCasasSnapshot()));

        // Restaura o estado das peças por time diretamente do TimeMultiton
        TimeMultiton.getTimeObjetos().forEach(time -> {
            time.getPecasDoTime().clear(); // Limpa as peças do time
            casas.values().stream()
                .filter(casa -> casa.getPeca() != null && casa.getPeca().getTime().equals(time.toString()))
                .forEach(casa -> time.adicionarPecasAoTime(casa.getPeca())); // Reassocia as peças ao time
        });
    }

    public PecaFlyweight getPecaNaPosicao(Posicao posToca) {
        Casa casa = casas.get(posToca);
        if (casa != null && casa.getPeca() != null) {
            return (PecaFlyweight) casa.getPeca();
        }
        return null;
    }

    public boolean estaDentro(Posicao pos) {
        // Supondo linhas e colunas começam em 0
        return pos.getLinha() >= 0 && pos.getLinha() < getLinhas() && pos.getColuna() >= 0 && pos.getColuna() < getColunas();
    }

    public boolean ehTerra(Posicao pos) {
        Casa casa = getCasas().get(pos);
        return casa != null && casa.getTipo().equalsIgnoreCase("terra");
    }

    public boolean ehAgua(Posicao pos) {
        Casa casa = getCasas().get(pos);
        return casa != null && casa.getTipo().equalsIgnoreCase("agua");
    }

    public boolean ehArmadilha(Posicao pos) {
        Casa casa = getCasas().get(pos);
        return casa != null && casa.getTipo().equalsIgnoreCase("armadilha");
    }

    public Peca getPeca(Posicao pos) {
        Casa casa = getCasas().get(pos);
        return casa != null ? casa.getPeca() : null;
    }

    public boolean podeCapturar(Posicao origem, Posicao destino) {
        // Implemente a lógica de captura do jogo Selva
        Peca atacante = getPeca(origem);
        Peca alvo = getPeca(destino);
        if (alvo == null) return false;
        // Exemplo: pode capturar se força >= do alvo, exceto regras especiais
        Integer forcaAtacante = (Integer) atacante.obterCaracteristica("forca");
        Integer forcaAlvo = (Integer) alvo.obterCaracteristica("forca");
        if (forcaAtacante == null || forcaAlvo == null) return false;
        // Regra especial rato-elefante pode ser tratada aqui
        return forcaAtacante >= forcaAlvo;
    }

    public int getLinhas() {
        return this.linhas;
    }

    public int getColunas() {
        return this.colunas;
    }

    public List<Observer> getObservadores() {
        return observadores;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((casas == null) ? 0 : casas.hashCode());
        result = prime * result + ((pecasPorTime == null) ? 0 : pecasPorTime.hashCode());
        result = prime * result + ((vitoriaDerrotaObserver == null) ? 0 : vitoriaDerrotaObserver.hashCode());
        result = prime * result + ((observadores == null) ? 0 : observadores.hashCode());
        result = prime * result + linhas;
        result = prime * result + colunas;
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
        Tabuleiro other = (Tabuleiro) obj;
        if (casas == null) {
            if (other.casas != null)
                return false;
        } else if (!casas.equals(other.casas))
            return false;
        if (pecasPorTime == null) {
            if (other.pecasPorTime != null)
                return false;
        } else if (!pecasPorTime.equals(other.pecasPorTime))
            return false;
        if (vitoriaDerrotaObserver == null) {
            if (other.vitoriaDerrotaObserver != null)
                return false;
        } else if (!vitoriaDerrotaObserver.equals(other.vitoriaDerrotaObserver))
            return false;
        if (observadores == null) {
            if (other.observadores != null)
                return false;
        } else if (!observadores.equals(other.observadores))
            return false;
        if (linhas != other.linhas)
            return false;
        if (colunas != other.colunas)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Tabuleiro [casas=" + casas + ", pecasPorTime=" + pecasPorTime + ", vitoriaDerrotaObserver="
                + vitoriaDerrotaObserver + ", observadores=" + observadores + ", linhas=" + linhas + ", colunas="
                + colunas + "]";
    }

    

    
}