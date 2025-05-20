package main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.memento;

import java.util.Stack;

/*Funciona como um CareTaker: 
ele armazena e gerencia os mementos, mas não acessa seu conteúdo.*/
public class HistoricoTabuleiro {
    private final Stack<TabuleiroMemento> desfazer = new Stack<>();
    private final Stack<TabuleiroMemento> refazer = new Stack<>();

    public void salvar(TabuleiroMemento memento) {
        desfazer.push(memento);
        refazer.clear();
    }

    public TabuleiroMemento desfazer() {
        if (!desfazer.isEmpty()) {
            TabuleiroMemento memento = desfazer.pop();
            refazer.push(memento);
            return memento;
        }
        return null;
    }

    public TabuleiroMemento refazer() {
        if (!refazer.isEmpty()) {
            TabuleiroMemento memento = refazer.pop();
            desfazer.push(memento);
            return memento;
        }
        return null;
    }

    public boolean temDesfazer() {
        return !desfazer.isEmpty();
    }

    public boolean temRefazer() {
        return !refazer.isEmpty();
    }
}
