package juego;

import java.awt.Color;

import entorno.Entorno;

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

    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.BLUE);
    }

    public boolean getMiraAlaDerecha() {
        return this.miraALaDerecha;
    }
}
