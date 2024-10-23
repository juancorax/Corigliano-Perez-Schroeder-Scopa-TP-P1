package juego;

public class Personaje {

    protected int x;
    protected int y;
    protected int ancho;
    protected int alto;
    protected int velocidad;
    protected int velocidadDeCaida;
    protected boolean enElAire;

    // La gravedad es constante y nunca cambia
    protected final double gravedad = 1;

    public Personaje(int x, int y, int ancho, int alto, int velocidad) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;

        // Variables con valores por defecto
        this.velocidadDeCaida = 0;
        this.enElAire = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public int getVelocidad() {
        return velocidad;
    }
    
    public void setVelocidadDeCaida(int velocidadDeCaida) {
    	this.velocidadDeCaida=velocidadDeCaida;
    }

    public void aplicarGravedad() {
        this.velocidadDeCaida += gravedad;

        if (this.velocidadDeCaida > 7) {
            this.velocidadDeCaida = 7;
        }

        this.y += velocidadDeCaida;
    }

    public void colisionConIslas(IslaFlotante[] islasFlotantes) {
        int pepIzquierda = this.getX() - (this.getAncho() / 2);
        int pepDerecha = this.getX() + (this.getAncho() / 2);
        int pepArriba = this.getY() - (this.getAlto() / 2);
        int pepAbajo = this.getY() + (this.getAlto() / 2);

        for (IslaFlotante islaFlotante : islasFlotantes) {
            int islaFlotanteIzquierda = islaFlotante.getX() - (islaFlotante.getAncho() / 2);
            int islaFlotanteDerecha = islaFlotante.getX() + (islaFlotante.getAncho() / 2);
            int islaFlotanteArriba = islaFlotante.getY() - (islaFlotante.getAlto() / 2);
            int islaFlotanteAbajo = islaFlotante.getY() + (islaFlotante.getAlto() / 2);

            // Comprobar si hay colision
            if (pepDerecha > islaFlotanteIzquierda && pepIzquierda < islaFlotanteDerecha &&
                    pepAbajo > islaFlotanteArriba && pepArriba < islaFlotanteAbajo) {

                // Calcular cada solapamiento
                int solapamientoIzquierda = pepDerecha - islaFlotanteIzquierda;
                int solapamientoDerecha = islaFlotanteDerecha - pepIzquierda;
                int solapamientoArriba = pepAbajo - islaFlotanteArriba;
                int solapamientoAbajo = islaFlotanteAbajo - pepArriba;

                // Encontrar el menor solapamiento
                int solapamientoMinimo = Math.min(Math.min(solapamientoIzquierda, solapamientoDerecha),
                        Math.min(solapamientoArriba, solapamientoAbajo));

                if (solapamientoMinimo == solapamientoIzquierda) {
                    // Colision desde la izquierda
                    this.x = islaFlotanteIzquierda - (this.ancho / 2);

                    break;
                } else if (solapamientoMinimo == solapamientoDerecha) {
                    // Colision desde la derecha
                    this.x = islaFlotanteDerecha + (this.ancho / 2);

                    break;
                } else if (solapamientoMinimo == solapamientoArriba) {
                    // Colision desde arriba
                    this.y = islaFlotanteArriba - (this.alto / 2);

                    // Reiniciar la velocidad de caida debido a la colision
                    this.velocidadDeCaida = 0;

                    // Restablecer al valor por defecto para poder volver a saltar
                    enElAire = false;

                    break;
                } else if (solapamientoMinimo == solapamientoAbajo) {
                    // Colision desde abajo
                    this.y = islaFlotanteAbajo + (this.alto / 2);

                    break;
                }
            }
        }
    }
}