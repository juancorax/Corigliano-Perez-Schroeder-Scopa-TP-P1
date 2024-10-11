package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	// Variables y métodos propios de cada grupo
	private IslaFlotante[] islasFlotantes;

	Juego() {
		// Resolucion del juego
		int anchoDeResolucion = 1280;
		int altoDeResolucion = 720;

		// Instancia el objeto entorno
		this.entorno = new Entorno(this, "Al Rescate de los Gnomos", anchoDeResolucion, altoDeResolucion);

		// Inicializar lo que haga falta para el juego
		// (agregar todo debajo de este punto)

		// Calcula el ancho y alto de cada isla flotante dependiendo de la resolucion
		IslaFlotante.setVariablesDeClase(anchoDeResolucion / 6, altoDeResolucion / 20);

		// Valores automaticamente calculados dependiendo de la resolucion
		// Se utilizan para instanciar las islas
		int centroX = anchoDeResolucion / 2;
		int centroY = altoDeResolucion / 2;
		int separacionHorizontal = centroX / 3;
		int separacionVertical = centroY / 3;

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

		// Dibuja cada isla en pantalla
		for (int i = 0; i < islasFlotantes.length; i++) {
			IslaFlotante islaFlotante = this.islasFlotantes[i];

			if (islaFlotante != null) {
				islaFlotante.dibujar(this.entorno);
			}
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
