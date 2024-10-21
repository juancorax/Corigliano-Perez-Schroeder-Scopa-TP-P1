package juego;

import java.awt.Color;

import entorno.Entorno;

public class Tortuga extends Personaje {

    private int velocidad = 1;
    private int direccionX;

    public Tortuga(int x, int y, int ancho, int alto, int velocidad) {
        super(x, y, ancho, alto, velocidad);
        this.direccionX = (Math.random() < 0.5) ? -1 : 1;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void rebotarTortugas(int centroX, int bordeIzquierdo, int bordeDerecho) {

        // cambiar dirección
        if (x < bordeIzquierdo || x > bordeDerecho) {
            direccionX = -direccionX;
        }

        if (x < centroX) {
            x += this.velocidad * direccionX; // Moverse a la derecha o izquierda
        } else if (x > centroX) {
            x -= velocidad * direccionX; // Moverse a la izquierda o derecha
        }

        // Limitar movimiento para que no se salga de los bordes de la pantalla
        if (x < 0) {
            x = 0;
        } else if (x > 1280) { // Asumiendo que el ancho de resolución es 1280
            x = 1280;
        }
    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.red);
    }
}