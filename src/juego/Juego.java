package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	// Variables y métodos propios de cada grupo

	// Variables para cada clase
	private IslaFlotante[] islasFlotantes;
	private Pep pep;
	private Disparo disparo;
	private Gnomo [] gnomos;

	// Resolucion del juego
	private int anchoDeResolucion = 1280;
	private int altoDeResolucion = 720;

	// Variables para determinar el movimiento
	// y duracion del disparo
	private int xDePepCuandoDisparo;
	private boolean pepMirabaALaDerechaCuandoDisparo;

	Juego() {
		// Instancia el objeto entorno
		this.entorno = new Entorno(this, "Al Rescate de los Gnomos", anchoDeResolucion, this.altoDeResolucion);

		// Inicializar lo que haga falta para el juego
		// (agregar todo debajo de este punto)

		// Valores automaticamente calculados dependiendo de la resolucion
		// Se utilizan para instanciar las islas
		int centroX = anchoDeResolucion / 2;
		int centroY = this.altoDeResolucion / 2;
		int separacionHorizontal = centroX / 3;
		int separacionVertical = centroY / 3;

		// Color del fondo
		this.entorno.colorFondo(new Color(173, 216, 230));

		// Calcula el ancho y alto de cada isla flotante dependiendo de la resolucion
		IslaFlotante.setVariablesDeClase(anchoDeResolucion / 6, this.altoDeResolucion / 20);

		// Arreglo de islas flotantes (se establece la cantidad)
		this.islasFlotantes = new IslaFlotante[9];

		// Instanciacion de cada isla (los valores se calculan automaticamente)
		this.islasFlotantes[0] = new IslaFlotante(centroX, separacionVertical);
		this.islasFlotantes[1] = new IslaFlotante(centroX - separacionHorizontal, separacionVertical * 2);
		this.islasFlotantes[2] = new IslaFlotante(centroX + separacionHorizontal, separacionVertical * 2);
		this.islasFlotantes[3] = new IslaFlotante(centroX - separacionHorizontal * 2, centroY);
		this.islasFlotantes[4] = new IslaFlotante(centroX, centroY);
		this.islasFlotantes[5] = new IslaFlotante(centroX + separacionHorizontal * 2, centroY);
		this.islasFlotantes[6] = new IslaFlotante(centroX - separacionHorizontal, separacionVertical * 4);
		this.islasFlotantes[7] = new IslaFlotante(centroX + separacionHorizontal, separacionVertical * 4);
		this.islasFlotantes[8] = new IslaFlotante(centroX, separacionVertical * 5);

		// Instanciacion de Pep
		this.pep = new Pep(centroX, (separacionVertical * 5) - (this.altoDeResolucion / 20), altoDeResolucion / 20,
				this.altoDeResolucion / 20, 3);

		this.gnomos = new Gnomo[3];
		for (int i = 0; i < gnomos.length; i++) {
			gnomos[i] = new Gnomo(anchoDeResolucion / 2, 0, 25, 25, 2); 
		}
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y
	 * por lo tanto es el método más importante de esta clase. Aquí se debe
	 * actualizar el estado interno del juego para simular el paso del tiempo
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo

		// Comprueba si las islas existen
		// y dibuja cada isla en pantalla
		if (this.islasFlotantes != null) {
			for (int i = 0; i < islasFlotantes.length; i++) {
				this.islasFlotantes[i].dibujar(this.entorno);
			}
		}

		// Comprueba si Pep existe y si
		// no se cayo al vacio
		if (this.pep != null) {
			// Dibuja a Pep
			this.pep.dibujar(this.entorno);

			detectarMovimientosDePep();

			// Se crea un disparo si actualmente no hay uno
			// y se presiona la tecla/boton correspondiente
			if (this.disparo == null
					&& (this.entorno.sePresiono('c') || this.entorno.sePresionoBoton(this.entorno.BOTON_IZQUIERDO))) {
				this.disparo = new Disparo(this.pep.getX(), this.pep.getY(), this.pep.getAncho() / 4, 5);

				// Si Pep disparo, se guardan la posicion y direccion
				if (this.disparo != null) {
					this.xDePepCuandoDisparo = this.pep.getX();
					this.pepMirabaALaDerechaCuandoDisparo = this.pep.getMiraAlaDerecha();
				}
			}

			// Si el disparo existe, lo dibuja
			if (this.disparo != null) {
				this.disparo.dibujar(this.entorno);

				// Mueve el disparo dependiendo de la direccion
				// a la cual Pep miraba
				if (this.pepMirabaALaDerechaCuandoDisparo) {
					this.disparo.moverALaDerecha();
				} else {
					this.disparo.moverALaIzquierda();
				}

				// Condiciones para que el disparo desaparezca
				if (this.disparo.getX() <= 0
						|| this.disparo.getX() >= this.anchoDeResolucion
						|| this.disparo.getX() > this.xDePepCuandoDisparo + (anchoDeResolucion / 6)
						|| this.disparo.getX() < this.xDePepCuandoDisparo - (anchoDeResolucion / 6)) {
					this.disparo = null;
				}
			}

			// Aplica gravedad a menos que haya colision
			this.pep.aplicarGravedad();
			this.pep.colisionConIslas(islasFlotantes);

			// Comprueba si Pep se cayo al vacio
			// y elimina todo en caso de ser asi
			if (this.pep.getY() - (this.pep.getAlto() / 2) > this.altoDeResolucion) {
				this.pep = null;
				this.islasFlotantes = null;
			}
		}
		
		for (int i = 0; i < gnomos.length; i++) {
	        if (gnomos[i] == null) {
	            // Crear un nuevo gnomo si el actual es null
	            gnomos[i] = new Gnomo(anchoDeResolucion / 2, 0, 25, 25, 2);
	            continue; // Saltar a la siguiente iteración después de crear el nuevo gnomo
	        }
	        
	        // Movimiento de los gnomos
	        gnomos[i].moverHaciaCentro(anchoDeResolucion);
	        gnomos[i].aplicarGravedad();
	        gnomos[i].dibujar(this.entorno);

	        // Verificar si el gnomo ha sido salvado por Pep
	        if (gnomos[i].estaCercaDePep(this.pep)) {
	            gnomos[i] = null; // Gnomo salvado, eliminarlo del juego
	            continue; // Saltar a la siguiente iteración para evitar otros métodos en este ciclo
	        }

	        // Verificar si el gnomo ha caído al vacío
	        if (gnomos[i].getY() > altoDeResolucion) {
	            gnomos[i] = null; // Eliminar gnomo caído
	            continue; // Saltar a la siguiente iteración para evitar otros métodos en este ciclo
	        }

	        // Verifica colisión con islas solo si el gnomo no es null
	        gnomos[i].colisionConIslas(this.islasFlotantes);
	    }
	}

	public void detectarMovimientosDePep() {
		// Movimiento hacia la derecha
		boolean sePresionoLaTeclaDerecha = this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)
				|| this.entorno.estaPresionada('d');
		if (sePresionoLaTeclaDerecha) {
			this.pep.moverDerecha();
		}

		// Movimiento hacia la izquierda
		boolean sePresionoLaTeclaIzquierda = this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)
				|| this.entorno.estaPresionada('a');
		if (sePresionoLaTeclaIzquierda) {
			this.pep.moverIzquierda();
		}

		// Salto
		boolean sePresionoLaTeclaArriba = this.entorno.estaPresionada(this.entorno.TECLA_ARRIBA)
				|| this.entorno.estaPresionada('w');
		if (sePresionoLaTeclaArriba) {
			this.pep.saltar();
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
