package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Pep extends Personaje {

    // Pep comienza mirando a la derecha
    private boolean miraALaDerecha = true;

    // Array que contiene la animacion de caminar a la derecha
    private Image[] caminarALaDerecha = new Image[5];
    // Array que contiene la animacion de caminar a la izquierda
    private Image[] caminarALaIzquierda = new Image[5];

    // Tiempo tomado desde que Pep cambio de frame en la animacion
    private int tiempoTranscurrido = 0;

    // Variable para almacenar los indices del frame de animacion anterior
    private int imagenAnteriorDerecha = 0;
    private int imagenAnteriorIzquierda = 0;

    public Pep(int x, int y, int ancho, int alto, int velocidad) {
        // 'super' utiliza el constructor de la clase padre
        super(x, y, ancho, alto, velocidad);

        // Almacena cada frame de animacion dentro de los arrays
        for (int i = 0; i < caminarALaDerecha.length; i++) {
            this.caminarALaDerecha[i] = Herramientas.cargarImagen("imagenes/pepcaminaderecha" + (i + 1) + ".png");
            this.caminarALaIzquierda[i] = Herramientas.cargarImagen("imagenes/pepcaminaizquierda" + (i + 1) + ".png");
        }
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

    // Dibuja a Pep dependiendo de si esta quieto, salta, camina o dispara
    public void dibujar(Entorno entorno, int altoDeResolucion, boolean pepDisparo, boolean pepCamina) {
        Image imagen;

        if (this.miraALaDerecha) {
            if (pepDisparo) {
                imagen = Herramientas.cargarImagen("imagenes/pepdisparoderecha.png");
            } else if (pepCamina && !this.enElAire) {
                imagen = this.caminarALaDerecha[this.imagenAnteriorDerecha];

                if (entorno.tiempo() - this.tiempoTranscurrido > 50) {
                    this.imagenAnteriorDerecha++;
                    this.tiempoTranscurrido = entorno.tiempo();
                }

                if (this.imagenAnteriorDerecha > this.caminarALaDerecha.length - 1) {
                    this.imagenAnteriorDerecha = 0;
                }
            } else if (this.enElAire) {
                imagen = Herramientas.cargarImagen("imagenes/pepsaltaderecha.png");
            } else {
                imagen = Herramientas.cargarImagen("imagenes/pepderecha.png");
            }
        } else {
            if (pepDisparo) {
                imagen = Herramientas.cargarImagen("imagenes/pepdisparoizquierda.png");
            } else if (pepCamina && !this.enElAire) {
                imagen = this.caminarALaIzquierda[this.imagenAnteriorIzquierda];

                if (entorno.tiempo() - this.tiempoTranscurrido > 50) {
                    this.imagenAnteriorIzquierda++;
                    this.tiempoTranscurrido = entorno.tiempo();
                }

                if (this.imagenAnteriorIzquierda > this.caminarALaIzquierda.length - 1) {
                    this.imagenAnteriorIzquierda = 0;
                }
            } else if (this.enElAire) {
                imagen = Herramientas.cargarImagen("imagenes/pepsaltaizquierda.png");
            } else {
                imagen = Herramientas.cargarImagen("imagenes/pepizquierda.png");
            }
        }

        entorno.dibujarImagen(imagen, this.x, this.y, 0, altoDeResolucion / 200);
    }

    public boolean getMiraAlaDerecha() {
        return this.miraALaDerecha;
    }
}
