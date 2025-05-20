package main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.multiton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.java.br.com.frameworkPpr.boardgame.game.Peca;

/**
 * Enum representa as duas equipes do jogo de tabuleiro.
 * Cada equipe tem um time associado.
 * Padrão de projeto: Enum Mutiton.
 */

public class TimeMultiton {
    private static final Map<String, TimeMultiton> times = new HashMap<>();
    private String nome;
    private List<Peca> pecas = new ArrayList<>();

    private TimeMultiton(String nome) {
        this.nome = nome;
    }

    public static TimeMultiton getInstance(String nome) {
        return times.computeIfAbsent(nome, TimeMultiton::new);
    }

    // Método para adicionar peças ao time com base na referencia da instancia
    // Exemplo: Time.BRANCO.adicionarPecasAoTime(peca);
    // Isso garante que as peças sejam adicionadas ao time correto.
    public boolean adicionarPecasAoTime(Peca peca) {
        if (peca == null) {
            throw new IllegalArgumentException("Peça não pode ser nula.");
        }
        return getPecas().add(peca);
    }

    // Método para listar peças do time com base na referencia da instancia
    // Exemplo: Time.BRANCO.getPecasDoTime();
    // Isso garante que as peças sejam listadas do time correto.
    public List<Peca> getPecasDoTime() {
        return new ArrayList<>(getPecas());
    }

    // Método que me retorna todas as chaves do Map de Times 
    public static Set<String> getChaves(){
        return times.keySet();
    }
    // Método que me retorna todos os valores do Map de Times
    public static Set<TimeMultiton> getTimeObjetos(){
        return (Set) times.values();
    }


    // Método para remover peças do time com base na referencia da instancia
    // Exemplo: Time.BRANCO.removerPecaDoTime(peca);
    // Isso garante que as peças sejam removidas do time correto.
    public boolean removerPecaDoTime(Peca peca) {
        if (peca == null) {
            throw new IllegalArgumentException("A peça não pode ser nula.");
        }
        return getPecas().remove(peca);
    }

    // Pega a quantidade de peças do time com base na referencia da instancia
    // Exemplo: Time.BRANCO.getQuantidadePecasDoTime();
    // Isso garante que a quantidade de peças seja retornada do time correto.
    public int getQuantidadePecasDoTime() {
        return getPecas().size();
    }

    @Override
    public String toString() {
        return nome;
    }

    private List<Peca> getPecas() {
        if (pecas == null) {
            pecas = new ArrayList<>();
        }
        return pecas;
    }

    public static Map<String, TimeMultiton> getTimes() {
        return times;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((pecas == null) ? 0 : pecas.hashCode());
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
        TimeMultiton other = (TimeMultiton) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (pecas == null) {
            if (other.pecas != null)
                return false;
        } else if (!pecas.equals(other.pecas))
            return false;
        return true;
    }

    
}
