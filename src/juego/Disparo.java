package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

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

    public void dibujar(Entorno entorno, int altoDeResolucion, boolean pepMirabaALaDerechaCuandoDisparo) {
        Image imagen;

        if (pepMirabaALaDerechaCuandoDisparo) {
            imagen = Herramientas.cargarImagen("imagenes/poderderecha.png");
        } else {
            imagen = Herramientas.cargarImagen("imagenes/poderizquierda.png");
        }

        entorno.dibujarImagen(imagen, this.x, this.y, 0, altoDeResolucion / 200);
        entorno.dibujarImagen(imagen, this.x, this.y, 0, altoDeResolucion / 200);
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
