package juego;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	// Variables y métodos propios de cada grupo

	// Variables para cada clase
	private EstadoDelJuego estadoDelJuego;
	private IslaFlotante[] islasFlotantes;
	private Pep pep;
	private Disparo disparo;
	private Gnomo[] gnomos;
	private Tortuga[] tortugas;

	// Resolucion del juego
	private int anchoDeResolucion = 1280;
	private int altoDeResolucion = 720;

	// Variables para determinar el movimiento, duracion
	// y dibujo del disparo
	private int xDePepCuandoDisparo;
	private boolean pepMirabaALaDerechaCuandoDisparo;

	// Variables para determinar que Pep esta caminando
	private boolean pepCamina = false;
	private int xDePepAnterior;

	// Variables para determinar que tipo de casa dibujar
	private boolean algunGnomoNoExiste = false;
	private int milisegundosHastaAhora;

	Juego() {
		// Instancia el objeto entorno
		this.entorno = new Entorno(this, "Al Rescate de los Gnomos", anchoDeResolucion, this.altoDeResolucion);

		// Inicializar lo que haga falta para el juego
		// (agregar todo debajo de este punto)

		// Valores automaticamente calculados dependiendo de la resolucion
		// Se utilizan para instanciar las islas
		int centroX = this.anchoDeResolucion / 2;
		int centroY = this.altoDeResolucion / 2;
		int separacionHorizontal = centroX / 3;
		int separacionVertical = centroY / 3;

		// Instanciacion del estado del juego
		this.estadoDelJuego = new EstadoDelJuego();

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

		// Instanciacion de los Gnomos
		this.gnomos = new Gnomo[3];
		for (int i = 0; i < gnomos.length; i++) {
			gnomos[i] = new Gnomo(anchoDeResolucion / 2, altoDeResolucion / 10, 25, 25, 2);
		}
		// Instanciacion de las Tortugas
		this.tortugas = new Tortuga[3];
		tortugas[0] = new Tortuga(427, 0, 25, 25, 2);
		tortugas[1] = new Tortuga(214, 0, 25, 25, 2);
		tortugas[2] = new Tortuga(1066, 0, 25, 25, 2);

		// Instanciacion de Pep
		this.pep = new Pep(centroX, (separacionVertical * 5) - (this.altoDeResolucion / 20), altoDeResolucion / 20,
				this.altoDeResolucion / 20, 3);

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

		// dibuja el fondo
		this.entorno.dibujarImagen(Herramientas.cargarImagen("imagenes/fondo.png"), anchoDeResolucion / 2,
				altoDeResolucion / 2, 0, 2);

		// Muestra en pantalla el estado del juego
		this.estadoDelJuego.mostrarEnPantalla(this.entorno, anchoDeResolucion,
				altoDeResolucion);

		// Comprueba si los gnomos existen
		if (this.gnomos != null) {
			// Comprueba si algun gnomo no existe
			for (Gnomo gnomo : this.gnomos) {
				if (gnomo == null) {
					this.algunGnomoNoExiste = true;
					this.milisegundosHastaAhora = this.entorno.tiempo();
					break;
				}
			}
		}

		// Si algun gnomo no existe, se dibuja la casa con la puerta abierta.
		// En caso contrario, se dibuja la casa normal
		if (this.algunGnomoNoExiste) {
			this.entorno.dibujarImagen(Herramientas.cargarImagen("imagenes/casadegnomosabierta.png"),
					anchoDeResolucion / 2,
					altoDeResolucion / 15, 0, 3);
		} else {
			this.entorno.dibujarImagen(Herramientas.cargarImagen("imagenes/casadegnomos.png"), anchoDeResolucion / 2,
					altoDeResolucion / 15, 0, 3);
		}

		// Se reinicia la variable 'algunGnomoNoExiste' despues de medio segundo
		// de que se detectara que un gnomo no existe
		if (this.algunGnomoNoExiste && this.entorno.tiempo() - this.milisegundosHastaAhora >= 500) {
			this.algunGnomoNoExiste = false;
		}

		// Comprueba si las islas existen
		// y dibuja cada isla en pantalla
		if (this.islasFlotantes != null) {
			for (int i = 0; i < islasFlotantes.length; i++) {
				this.islasFlotantes[i].dibujar(this.entorno, this.altoDeResolucion);
			}
		}

		// Comprueba si Pep existe y si
		// no se cayo al vacio
		if (this.pep != null) {
			// Dibuja a Pep
			this.pep.dibujar(this.entorno, this.altoDeResolucion, this.disparo != null, this.pepCamina);

			// Se reinicia la variable 'pepCamina' si Pep deja de caminar
			if (this.pep.getX() == this.xDePepAnterior) {
				this.pepCamina = false;
			}

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
				this.disparo.dibujar(this.entorno, this.altoDeResolucion, this.pepMirabaALaDerechaCuandoDisparo);

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
				this.gnomos = null;
				this.tortugas = null;
			}
		}

		// Comprueba si los gnomos existen
		if (this.gnomos != null) {
			for (int i = 0; i < gnomos.length; i++) {
				if (gnomos[i] == null) {
					// Crear un nuevo gnomo si el actual es null
					gnomos[i] = new Gnomo(anchoDeResolucion / 2, altoDeResolucion / 10, 25, 25, 2);
					continue; // Saltar a la siguiente iteración después de crear el nuevo gnomo
				}

				// Movimiento de los gnomos
				gnomos[i].moverHaciaCentro(anchoDeResolucion);
				gnomos[i].aplicarGravedad();
				gnomos[i].dibujar(this.entorno, altoDeResolucion);

				// Verificar si el gnomo ha sido salvado por Pep
				if (gnomos[i].estaCercaDePep(this.pep)) {
					gnomos[i] = null; // Gnomo salvado, eliminarlo del juego
					this.estadoDelJuego.setGnomosSalvados(this.estadoDelJuego.getGnomosSalvados() + 1); // Sumarlo a la
																										// cantidad de
																										// gnomos
																										// salvados
					continue; // Saltar a la siguiente iteración para evitar otros métodos en este ciclo
				}

				// Verificar si el gnomo ha caído al vacío
				if (gnomos[i].getY() > altoDeResolucion) {
					gnomos[i] = null; // Eliminar gnomo caído
					this.estadoDelJuego.setGnomosPerdidos(this.estadoDelJuego.getGnomosPerdidos() + 1); // Sumarlo a la
																										// cantidad de
																										// gnomos
																										// perdidos
					continue; // Saltar a la siguiente iteración para evitar otros métodos en este ciclo
				}

				// Verifica colisión con islas solo si el gnomo no es null
				gnomos[i].colisionConIslas(this.islasFlotantes);
			}
		}
		// TORTUGAS
		if (this.tortugas != null) {
			for (int i = 0; i < tortugas.length; i++) {
				tortugas[i].aplicarGravedad();
				tortugas[i].dibujar(this.entorno);
				tortugas[i].colisionConIslas(this.islasFlotantes);
			}

			tortugas[0].rebotarTortugas(anchoDeResolucion,
					(anchoDeResolucion / 2) - ((anchoDeResolucion / 2) / 3) - (anchoDeResolucion / 12),
					(anchoDeResolucion / 2) - ((anchoDeResolucion / 2) / 3) + (anchoDeResolucion / 12));

			tortugas[1].rebotarTortugas(anchoDeResolucion,
					((anchoDeResolucion / 2) - ((anchoDeResolucion / 2) / 3) * 2) - (anchoDeResolucion / 12),
					((anchoDeResolucion / 2) - ((anchoDeResolucion / 2) / 3) * 2) + (anchoDeResolucion / 12));

			tortugas[2].rebotarTortugas(anchoDeResolucion,
					((anchoDeResolucion / 2) + ((anchoDeResolucion / 2) / 3) * 2) - (anchoDeResolucion / 12),
					((anchoDeResolucion / 2) + ((anchoDeResolucion / 2) / 3) * 2) + (anchoDeResolucion / 12));
		}
	}

	public void detectarMovimientosDePep() {
		// Movimiento hacia la derecha
		boolean sePresionoLaTeclaDerecha = this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)
				|| this.entorno.estaPresionada('d');
		if (sePresionoLaTeclaDerecha) {
			this.pep.moverDerecha();
			this.pepCamina = true;
			this.xDePepAnterior = this.pep.getX();
		}

		// Movimiento hacia la izquierda
		boolean sePresionoLaTeclaIzquierda = this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)
				|| this.entorno.estaPresionada('a');
		if (sePresionoLaTeclaIzquierda) {
			this.pep.moverIzquierda();
			this.pepCamina = true;
			this.xDePepAnterior = this.pep.getX();
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
