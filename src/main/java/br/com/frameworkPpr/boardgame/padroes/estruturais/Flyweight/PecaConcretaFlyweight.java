package main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.flyweight;

import main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.multiton.TimeMultiton;

public class PecaConcretaFlyweight implements PecaFlyweight {
    private final String tipo;
    private final TimeMultiton time;

    public PecaConcretaFlyweight(String tipo, TimeMultiton time) {
        this.tipo = tipo;
        this.time = time;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public TimeMultiton getTime() {
        return time;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
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
        PecaConcretaFlyweight other = (PecaConcretaFlyweight) obj;
        if (tipo == null) {
            if (other.tipo != null)
                return false;
        } else if (!tipo.equals(other.tipo))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PecaConcretaFlyweight [tipo=" + tipo + ", time=" + time + "]";
    }

}
