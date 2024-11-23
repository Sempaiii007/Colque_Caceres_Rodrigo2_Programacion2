package COVARDES;
public class Juego {// toda la logica del juego 

    private int puntosJugador1;
    private int puntosJugador2;
    private int rondasGanadasJugador1;
    private int rondasGanadasJugador2;

    public Juego() {
        puntosJugador1 = 0;
        puntosJugador2 = 0;
        rondasGanadasJugador1 = 0;
        rondasGanadasJugador2 = 0;
    }

    // Método para comparar las cartas seleccionadas y determinar el ganador de la ronda 
    public String jugarRonda(Carta cartaJugador1, Carta cartaJugador2) {
        int valorJugador1ConIncremento = cartaJugador1.getValor() + 1;
        int valorJugador2ConIncremento = cartaJugador2.getValor() + 1;

        // Verificar si el jugador 1 tiene una carta 0 y pierde contra una diferencia de 2 o más
        if (cartaJugador1.getValor() == 0 && Math.abs(valorJugador1ConIncremento - valorJugador2ConIncremento) >= 2) {
            puntosJugador2 += 5;  // Sumar 5 puntos al jugador 2
            // Verificar si el jugador 2 gana la partida con esos puntos
            if (puntosJugador2 >= 5) {
                return "TU PRINCESA A CAIDO EN BATALLA";
            } else {
                return "TU PRINCESA A CAIDO EN BATALLA";
            }
        }
        // Verificar si el jugador 2 tiene una carta 0 y pierde contra una diferencia de 2 o más
        else if (cartaJugador2.getValor() == 0 && Math.abs(valorJugador1ConIncremento - valorJugador2ConIncremento) >= 2) {
            puntosJugador1 += 5;  // Sumar 5 puntos al jugador 1
            // Verificar si el jugador 1 gana la partida con esos puntos
            if (puntosJugador1 >= 5) {
                return "TU PRINCIPE A CAIDO EN BATALLA";
            } else {
                return "TU PRINCIPE A CAIDO EN BATALLA ";
            }
        }

        // Comparar los valores de las cartas y determinar el ganador de la ronda
        else if (Math.abs(valorJugador1ConIncremento - valorJugador2ConIncremento) == 1) {
            return "MANDAD A MAS GUERREROS";
        } else if (valorJugador1ConIncremento - valorJugador2ConIncremento >= 2) {
            rondasGanadasJugador1++;
            puntosJugador1++;
            // Verificar si el jugador 1 gana la partida
            if (puntosJugador1 >= 5) {
                return "JUGADOR 1 A GANADO EL ENFRENTAMIENTO";
            }
            return "JUGADOR 2 A GANADO EL ENFRENTAMIENTO";
        } else if (valorJugador2ConIncremento - valorJugador1ConIncremento >= 2) {
            rondasGanadasJugador2++;
            puntosJugador2++;
            // Verificar si el jugador 2 gana la partida
            if (puntosJugador2 >= 5) {
                return "JUGADOR 2 A GANADO EL ENFRENTAMIENTO";
            }
            return "JUGADOR 2 A GANADO EL ENFRENTAMIENTO";
        }
        return "MANDAD A MAS GUERREROS";
    }
    // sempagoad
    // Método para verificar si un jugador ha ganado el juego
    public boolean haGanadoJuego() {
        return puntosJugador1 >= 5 || puntosJugador2 >= 5;
    }

    // Métodos para obtener el puntaje de cada jugador
    public int getPuntosJugador1() {
        return puntosJugador1;
    }

    public int getPuntosJugador2() {
        return puntosJugador2;
    }

    // Métodos para obtener la cantidad de rondas ganadas por cada jugador
    public int getRondasGanadasJugador1() {
        return rondasGanadasJugador1;
    }

    public int getRondasGanadasJugador2() {
        return rondasGanadasJugador2;
    }

    // Método para reiniciar el juego
    public void reiniciarJuego() {
        puntosJugador1 = 0;
        puntosJugador2 = 0;
        rondasGanadasJugador1 = 0;
        rondasGanadasJugador2 = 0;
    }
}