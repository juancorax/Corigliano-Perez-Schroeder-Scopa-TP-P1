package juego;

import java.awt.Color;

import entorno.Entorno;
public class Gnomo extends Personaje {
    
	int velocidad = 1;
    private boolean salvado = false;
    private int direccionX; // -1 para izquierda, 1 para derecha

    public Gnomo(int x, int y, int ancho, int alto, int velocidad) {
        super(x, y, ancho, alto, velocidad);
        this.direccionX = (Math.random() < 0.5) ? -1 : 1; // Asigna una dirección aleatoria al crear el gnomo
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

    public void dibujar(Entorno entorno) {
        if (!salvado) {
            entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.gray);
        }
    }
}


