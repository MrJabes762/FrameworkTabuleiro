package main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.flyweight;

public class CasaConcretaFlyweight implements CasaFlyweight {
    private final String cor;
    private final int numero;

    public CasaConcretaFlyweight(String cor, int numero) {
        this.cor = cor;
        this.numero = numero;
    }

    @Override
    public String getCor() {
        return cor;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public boolean estaOcupada() {
        // Como Flyweight, não armazena estado de ocupação real.
        // Retorna sempre false (ocupação é extrínseca, depende do contexto externo).
        return false;
    }

    @Override
    public Object getPeca() {
        // Flyweight não armazena peça, retorna sempre null
        return null;
    }

    @Override
    public void setPeca(PecaFlyweight peca) {
        // Flyweight não armazena peça, não faz nada
        // Método vazio propositalmente
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cor == null) ? 0 : cor.hashCode());
        result = prime * result + numero;
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
        CasaConcretaFlyweight other = (CasaConcretaFlyweight) obj;
        if (cor == null) {
            if (other.cor != null)
                return false;
        } else if (!cor.equals(other.cor))
            return false;
        if (numero != other.numero)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CasaConcretaFlyweight [cor=" + cor + ", numero=" + numero + "]";
    }
    
}
