package COVARDES;

public class Carta {

    private int valor;

    public Carta(int valor) {
        if (valor < 0 || valor > 6) {
            throw new IllegalArgumentException("El valor de la carta debe estar entre 0 y 6");
        }
        this.valor = valor;// asigna el valor de las cartas 
    }

    public int getValor() {  //retorna valor apra cada carta
        return valor;
    }

    @Override
    public String toString() {
        return "Carta{" + "valor=" + valor + '}';
    }
}
