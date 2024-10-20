package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Pep extends Personaje {

    // Pep comienza mirando a la derecha
    private boolean miraALaDerecha = true;

    public Pep(int x, int y, int ancho, int alto, int velocidad) {
        // 'super' utiliza el constructor de la clase padre
        super(x, y, ancho, alto, velocidad);
    }

    public void moverIzquierda() {
        this.x -= this.velocidad;
        this.miraALaDerecha = false;
    }

    public void moverDerecha() {
        this.x += this.velocidad;
        this.miraALaDerecha = true;
    }

    public void saltar() {
        if (!(this.enElAire)) {
            this.velocidadDeCaida = -this.velocidad * 6;
            this.y -= this.velocidad;

            this.enElAire = true;
        }
    }

    public void dibujar(Entorno entorno, int altoDeResolucion) {
        Image imagen;

        if (this.miraALaDerecha) {
            imagen = Herramientas.cargarImagen("imagenes/pepderecha.png");
        } else {
            imagen = Herramientas.cargarImagen("imagenes/pepizquierda.png");
        }

        entorno.dibujarImagen(imagen, this.x, this.y, 0, altoDeResolucion / 200);
    }

    public boolean getMiraAlaDerecha() {
        return this.miraALaDerecha;
    }
}
