package main.java.br.com.frameworkPpr.boardgame.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.observer.Observer;
import main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.strategy.MovimentoStrategy;
import main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.multiton.TimeMultiton;

public abstract class Peca implements Observer{
    private String nome;
    private TimeMultiton time;
    private Map<String, Object> caracteristicas; 
    protected MovimentoStrategy movimentoStrategy;
    private List<Observer> observadores = new ArrayList<>();

    public Peca() {
        this.caracteristicas = new HashMap<>();
    }

    public Peca(String nome, TimeMultiton time, MovimentoStrategy movimentoStrategy, Map<String, Object> caracteristicas) {
        setNome(nome);
        setTime(time);
        this.movimentoStrategy = movimentoStrategy;
        setCaracteristicas(caracteristicas != null ? caracteristicas : new HashMap<>());
    }

    public void adicionarObservadores(Observer o)
    {
        observadores.add(o);
    }

    

    public void setTime(TimeMultiton time2) {
        this.time = time2;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<String, Object> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Map<String, Object> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public void adicionarCaracteristica(String chave, Object valor) {
        this.caracteristicas.put(chave, valor);
    }

    public Object obterCaracteristica(String chave) {
        return this.caracteristicas.get(chave);
    }

    public List<Posicao> obterMovimentosPossiveis(Posicao posicaoAtual, Tabuleiro tabuleiro) {
        return movimentoStrategy.calcularMovimentosPossiveis(posicaoAtual, tabuleiro);
    }

    public TimeMultiton getTime() {
        return time;
    }

    @Override
    public void update(String evento) {
        System.out.println("Peça" + getNome() + " recebeu notificação: " + evento);

        if (evento.startsWith("peça movida")) {
            String[] partes = evento.split(":");
            String[] posicoes = partes[1].trim().split(" para ");
            Posicao origem = new Posicao(Integer.parseInt(posicoes[0].split(",")[0]), Integer.parseInt(posicoes[0].split(",")[1]));
            Posicao destino = new Posicao(Integer.parseInt(posicoes[1].split(",")[0]), Integer.parseInt(posicoes[1].split(",")[1]));

            System.out.println("Movimento detectado de " + origem + " para " + destino);
        }
    }

    @Override
    public String toString() {
        return "Peca [nome=" + nome + ", time=" + time + ", caracteristicas=" + caracteristicas + ", movimentoStrategy="
                + movimentoStrategy + "]";
    }

    public MovimentoStrategy getMovimentoStrategy() {
        return movimentoStrategy;
    }

    public List<Observer> getObservadores() {
        return observadores;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((caracteristicas == null) ? 0 : caracteristicas.hashCode());
        result = prime * result + ((movimentoStrategy == null) ? 0 : movimentoStrategy.hashCode());
        result = prime * result + ((observadores == null) ? 0 : observadores.hashCode());
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
        Peca other = (Peca) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (caracteristicas == null) {
            if (other.caracteristicas != null)
                return false;
        } else if (!caracteristicas.equals(other.caracteristicas))
            return false;
        if (movimentoStrategy == null) {
            if (other.movimentoStrategy != null)
                return false;
        } else if (!movimentoStrategy.equals(other.movimentoStrategy))
            return false;
        if (observadores == null) {
            if (other.observadores != null)
                return false;
        } else if (!observadores.equals(other.observadores))
            return false;
        return true;
    }
    
}
