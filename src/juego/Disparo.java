package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Disparo {

    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;

    public Disparo(int x, int y, int ancho, int alto, int velocidad) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
    }

    public void moverALaDerecha() {
        this.x += this.velocidad;
    }

    public void moverALaIzquierda() {
        this.x -= this.velocidad;
    }
    
    /*public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.ORANGE);
    }*/

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

    public int getAncho() {
        return this.ancho;
    }
    
    public int getAlto() {
        return this.alto;
    }

    public int getVelocidad() {
        return this.velocidad;
    }
}
