package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class IslaFlotante {

    // Variables de clase
    private static int anchoDeClase;
    private static int altoDeClase;

    // Este metodo debe llamarse antes de la instanciacion
    public static void setVariablesDeClase(int ancho, int alto) {
        anchoDeClase = ancho;
        altoDeClase = alto;
    }

    private int x;
    private int y;
    private int ancho;
    private int alto;

    // Antes de la instanciacion debe llamarse al metodo 'setVariablesDeClase'
    public IslaFlotante(int x, int y) {
        this.x = x;
        this.y = y;

        // anchoDeClase & altoDeClase son variables de clase
        this.ancho = anchoDeClase;
        this.alto = altoDeClase;
    }

    public void dibujar(Entorno entorno, int altoDeResolucion) {
        Image imagen = Herramientas.cargarImagen("imagenes/isla.png");

        entorno.dibujarImagen(imagen, this.x, this.y, 0, altoDeResolucion / 200);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}