package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	// Variables y métodos propios de cada grupo
	private Pep pep;
	private IslaFlotante[] islasFlotantes;

	// Resolucion del juego
	private int anchoDeResolucion = 1280;

	private int altoDeResolucion = 720;

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

			detectarPulsadoDeTeclas();

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
	}

	public void detectarPulsadoDeTeclas() {
		// Movimiento hacia la derecha
		boolean sePresionoLaTeclaDerecha = this.entorno.estaPresionada(this.entorno.TECLA_DERECHA);
		if (sePresionoLaTeclaDerecha) {
			this.pep.moverDerecha();
		}

		// Movimiento hacia la izquierda
		boolean sePresionoLaTeclaIzquierda = this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA);
		if (sePresionoLaTeclaIzquierda) {
			this.pep.moverIzquierda();
		}

		// Salto
		boolean sePresionoLaTeclaArriba = this.entorno.estaPresionada(this.entorno.TECLA_ARRIBA);
		if (sePresionoLaTeclaArriba) {
			this.pep.saltar();
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
