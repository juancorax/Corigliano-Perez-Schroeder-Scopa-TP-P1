package juego;

import java.awt.Color;
import entorno.Entorno;

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

    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, new Color(34, 139, 34));
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