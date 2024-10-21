package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Tortuga extends Personaje {
	
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
	
	public void rebotarTortugas(int centroX,int bordeIzquierdo, int bordeDerecho) {

        // cambiar dirección
        if (x<bordeIzquierdo || x>bordeDerecho) {
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
    boolean estaCercaDePep(Pep pep) {
        int pepIzquierda = pep.getX() - pep.getAncho() / 2;
        int pepDerecha = pep.getX() + pep.getAncho() / 2;
        int pepArriba = pep.getY() - pep.getAlto() / 2;
        int pepAbajo = pep.getY() + pep.getAlto() / 2;

        int tortugaIzquierda = this.x - this.ancho / 2;
        int tortugaDerecha = this.x + this.ancho / 2;
        int tortugaArriba = this.y - this.alto / 2;
        int tortugaAbajo = this.y + this.alto / 2;

        return tortugaDerecha > pepIzquierda && tortugaIzquierda < pepDerecha &&
                tortugaAbajo > pepArriba && tortugaArriba < pepAbajo;
    }
    
    /*boolean estaCercaDeDisparo(Disparo disparo) {
        int disparoIzquierda = disparo.getX() - disparo.getRadio();
        int disparoDerecha = disparo.getX() + disparo.getRadio();
        int disparoArriba = disparo.getY() - disparo.getRadio();
        int disparoAbajo = disparo.getY() + disparo.getRadio();

        int tortugaIzquierda = this.x - this.y;
        int tortugaDerecha = this.x + this.y;
        int tortugaArriba = this.y - this.y;
        int tortugaAbajo = this.y + this.y;

        return tortugaDerecha > disparoIzquierda && tortugaIzquierda < disparoDerecha &&
                tortugaAbajo > disparoArriba && tortugaArriba < disparoAbajo;
    }*/
    
    public void dibujar(Entorno entorno, int altoDeResolucion) {
        Image imagen;

        if (this.direccionX == 1) {
            imagen = Herramientas.cargarImagen("imagenes/tortugaderecha.png");
        } else {
            imagen = Herramientas.cargarImagen("imagenes/tortugaizquierda.png");
        }

        entorno.dibujarImagen(imagen, this.x, this.y, 0, altoDeResolucion / 200);
    }
	
	 public int getY() {
	        return y;
	    }
}
