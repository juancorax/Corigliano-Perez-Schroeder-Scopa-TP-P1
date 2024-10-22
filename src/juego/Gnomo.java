package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Gnomo extends Personaje {

    int velocidad = 1;
    private boolean salvado = false;
    private int direccionX; // -1 para izquierda, 1 para derecha

    // Array que contiene la animacion de caminar a la derecha
    private Image[] caminarALaDerecha = new Image[5];
    // Array que contiene la animacion de caminar a la izquierda
    private Image[] caminarALaIzquierda = new Image[5];

    // Tiempo tomado desde que el gnomo cambio de frame en la animacion
    private int tiempoTranscurrido = 0;

    // Variable para almacenar los indices del frame de animacion anterior
    private int imagenAnteriorDerecha = 0;
    private int imagenAnteriorIzquierda = 0;

    public Gnomo(int x, int y, int ancho, int alto, int velocidad) {
        super(x, y, ancho, alto, velocidad);
        this.direccionX = (Math.random() < 0.5) ? -1 : 1; // Asigna una dirección aleatoria al crear el gnomo

        // Almacena cada frame de animacion dentro de los arrays
        for (int i = 0; i < caminarALaDerecha.length; i++) {
            this.caminarALaDerecha[i] = Herramientas.cargarImagen("imagenes/gnomocaminaderecha" + (i + 1) + ".png");
            this.caminarALaIzquierda[i] = Herramientas.cargarImagen("imagenes/gnomocaminaizquierda" + (i + 1) + ".png");
        }
    }

    public boolean estaSalvado() {
        return salvado;
    }

    public void moverHaciaCentro(int centroX) {
        // Cambia aleatoriamente la dirección lateral
        if (Math.random() < 0.01) { // Cambia de dirección con un 1% de probabilidad
            direccionX = -direccionX; // Cambiar dirección
        }

        // Mover en la dirección aleatoria
        if (x < centroX) {
            x += velocidad * direccionX; // Moverse a la derecha o izquierda
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

    public void caer() {
        this.aplicarGravedad();
    }

    boolean estaCercaDePep(Pep pep) {
        int pepIzquierda = pep.getX() - pep.getAncho() / 2;
        int pepDerecha = pep.getX() + pep.getAncho() / 2;
        int pepArriba = pep.getY() - pep.getAlto() / 2;
        int pepAbajo = pep.getY() + pep.getAlto() / 2;

        int gnomoIzquierda = this.x - this.ancho / 2;
        int gnomoDerecha = this.x + this.ancho / 2;
        int gnomoArriba = this.y - this.alto / 2;
        int gnomoAbajo = this.y + this.alto / 2;

        return gnomoDerecha > pepIzquierda && gnomoIzquierda < pepDerecha &&
                gnomoAbajo > pepArriba && gnomoArriba < pepAbajo;
    }
    
    boolean estaCercaDeTortuga(Tortuga tortuga) {
        int tortugaIzquierda = tortuga.getX() - tortuga.getAncho() / 2;
        int tortugaDerecha = tortuga.getX() + tortuga.getAncho() / 2;
        int tortugaArriba = tortuga.getY() - tortuga.getAlto() / 2;
        int tortugaAbajo = tortuga.getY() + tortuga.getAlto() / 2;

        int gnomoIzquierda = this.x - this.ancho / 2;
        int gnomoDerecha = this.x + this.ancho / 2;
        int gnomoArriba = this.y - this.alto / 2;
        int gnomoAbajo = this.y + this.alto / 2;

        return gnomoDerecha > tortugaIzquierda && gnomoIzquierda < tortugaDerecha &&
                gnomoAbajo > tortugaArriba && gnomoArriba < tortugaAbajo;
    }

    public void dibujar(Entorno entorno, int altoDeResolucion) {
        Image imagen;

        if (this.direccionX == 1) {
            imagen = this.caminarALaDerecha[this.imagenAnteriorDerecha];

            if (entorno.tiempo() - this.tiempoTranscurrido > 50) {
                this.imagenAnteriorDerecha++;
                this.tiempoTranscurrido = entorno.tiempo();
            }

            if (this.imagenAnteriorDerecha > this.caminarALaDerecha.length - 1) {
                this.imagenAnteriorDerecha = 0;
            }
        } else {
            imagen = this.caminarALaIzquierda[this.imagenAnteriorIzquierda];

            if (entorno.tiempo() - this.tiempoTranscurrido > 50) {
                this.imagenAnteriorIzquierda++;
                this.tiempoTranscurrido = entorno.tiempo();
            }

            if (this.imagenAnteriorIzquierda > this.caminarALaIzquierda.length - 1) {
                this.imagenAnteriorIzquierda = 0;
            }
        }

        entorno.dibujarImagen(imagen, this.x, this.y, 0, altoDeResolucion / 200);
    }
}
