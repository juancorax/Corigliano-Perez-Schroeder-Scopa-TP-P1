package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Tortuga extends Personaje {

	private int direccionX;
	private int isla;

	// Array que contiene la animacion de caminar a la derecha
	private Image[] caminarALaDerecha = new Image[5];
	// Array que contiene la animacion de caminar a la izquierda
	private Image[] caminarALaIzquierda = new Image[5];

	// Tiempo tomado desde que la tortuga cambio de frame en la animacion
	private int tiempoTranscurrido = 0;

	// Variable para almacenar los indices del frame de animacion anterior
	private int imagenAnteriorDerecha = 0;
	private int imagenAnteriorIzquierda = 0;

	public Tortuga(int x, int y, int ancho, int alto, int velocidad, int isla) {
		super(x, y, ancho, alto, velocidad);
		this.direccionX = (Math.random() < 0.5) ? -1 : 1;
		this.isla=isla;

		// Almacena cada frame de animacion dentro de los arrays
		for (int i = 0; i < caminarALaDerecha.length; i++) {
			this.caminarALaDerecha[i] = Herramientas.cargarImagen("imagenes/tortugacaminaderecha" + (i + 1) + ".png");
			this.caminarALaIzquierda[i] = Herramientas
					.cargarImagen("imagenes/tortugacaminaizquierda" + (i + 1) + ".png");
		}
	}

	public int getVelocidad() {
		return velocidad;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
	
	public int getIsla() {
		return isla;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public void setY(int y) {
		this.y=y;
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

	boolean estaCercaDePep(Pep pep) {
		int pepIzquierda = pep.getX() - pep.getAncho() / 2;
		int pepDerecha = pep.getX() + pep.getAncho() / 2;
		int pepArriba = pep.getY() - pep.getAlto() / 2;
		int pepAbajo = pep.getY() + pep.getAlto() / 2;

		int tortugaIzquierda = this.x - this.ancho / 2;
		int tortugaDerecha = this.x + this.ancho / 2;
		int tortugaArriba = this.y - this.alto / 2;
		int tortugaAbajo = this.y + this.alto / 2;

		return tortugaDerecha > pepIzquierda && tortugaIzquierda < pepDerecha && tortugaAbajo > pepArriba
				&& tortugaArriba < pepAbajo;
	}
	
	public boolean intersectaConDisparo(Disparo disparo) {
        int xMin1 = this.x - ancho / 2;
        int xMax1 = this.x + ancho / 2;
        int yMin1 = this.y - alto / 2;
        int yMax1 = this.y + alto / 2;

        int xMin2 = disparo.getX() - disparo.getAncho() / 2;
        int xMax2 = disparo.getX() + disparo.getAncho() / 2;
        int yMin2 = disparo.getY() - disparo.getAlto() / 2;
        int yMax2 = disparo.getY() + disparo.getAlto() / 2;

        return (xMin1 < xMax2 && xMax1 > xMin2 &&
                yMin1 < yMax2 && yMax1 > yMin2);
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

	public int getY() {
		return y;
	}
}
