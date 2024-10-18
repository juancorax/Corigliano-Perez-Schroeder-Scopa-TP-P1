package juego;

import java.awt.Color;

import entorno.Entorno;

public class EstadoDelJuego {

    private int gnomosSalvados;
    private int gnomosPerdidos;
    private int enemigosEliminados;

    public EstadoDelJuego() {
        this.gnomosSalvados = 0;
        this.gnomosPerdidos = 0;
        this.enemigosEliminados = 0;
    }

    public int getGnomosSalvados() {
        return gnomosSalvados;
    }

    public int getGnomosPerdidos() {
        return gnomosPerdidos;
    }

    public int getEnemigosEliminados() {
        return enemigosEliminados;
    }

    public void setGnomosSalvados(int gnomosSalvados) {
        this.gnomosSalvados = gnomosSalvados;
    }

    public void setGnomosPerdidos(int gnomosPerdidos) {
        this.gnomosPerdidos = gnomosPerdidos;
    }

    public void setEnemigosEliminados(int enemigosEliminados) {
        this.enemigosEliminados = enemigosEliminados;
    }

    // Convierte los milisegundos a minutos y los devuelve como una cadena
    public String cadenaDeMinutos(int milisegundos) {
        int segundos = milisegundos / 1000;
        int minutos = segundos / 60;
        int restoDeSegundos = segundos - (60 * minutos);

        if (restoDeSegundos < 10) {
            return minutos + ":0" + restoDeSegundos;
        }

        return minutos + ":" + restoDeSegundos;
    }

    // Muestra el estado del juego en pantalla
    public void mostrarEnPantalla(Entorno entorno, int anchoDeResolucion, int altoDeResolucion) {
        // Fuente del juego
        entorno.cambiarFont("Arial", anchoDeResolucion / 30, Color.BLACK);

        int mitadDePantalla = anchoDeResolucion / 2;

        // Muestra los 4 mensajes en pantalla
        entorno.escribirTexto("Tiempo: " + this.cadenaDeMinutos(entorno.tiempo()), mitadDePantalla / 20,
                altoDeResolucion / 15);
        entorno.escribirTexto("Salvados: " + this.gnomosSalvados, mitadDePantalla / 2, altoDeResolucion / 15);
        entorno.escribirTexto("Perdidos: " + this.gnomosPerdidos, mitadDePantalla + (mitadDePantalla / 10),
                altoDeResolucion / 15);
        entorno.escribirTexto("Eliminados: " + this.enemigosEliminados, mitadDePantalla + (mitadDePantalla / 2),
                altoDeResolucion / 15);
    }
}
