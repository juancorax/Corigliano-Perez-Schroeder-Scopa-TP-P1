package juego;

import java.awt.Color;

import entorno.Entorno;

public class Disparo {

    private int x;
    private int y;
    private int radio;
    private int velocidad;

    public Disparo(int x, int y, int radio, int velocidad) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.velocidad = velocidad;
    }

    public void moverALaDerecha() {
        this.x += this.velocidad;
    }

    public void moverALaIzquierda() {
        this.x -= this.velocidad;
    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarCirculo(this.x, this.y, this.radio * 2, Color.ORANGE);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getRadio() {
        return this.radio;
    }

    public int getVelocidad() {
        return this.velocidad;
    }
}
