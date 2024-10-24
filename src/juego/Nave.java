package juego;

import entorno.Herramientas;
import entorno.Entorno;

public class Nave {

    private int x;
    private int y;
    private int ancho;
    private int alto;

    public Nave(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    // Dibuja la nave en la posición actual
    public void dibujar(Entorno entorno) {
        entorno.dibujarImagen(Herramientas.cargarImagen("imagenes/nave.png"), this.getX(), this.getY(), 0, 6);
    }

    // Método para mover la nave siguiendo el cursor del mouse
    public void moverConCursor(int mouseX) {
        this.x = mouseX;
    }

    // Metodo para comprobar si hay colisión entre la nave y Pep
    public void colisionConNave(Pep pep) {
        int naveIzquierda = this.getX() - (this.getAncho() / 2);
        int naveDerecha = this.getX() + (this.getAncho() / 2);
        int naveArriba = this.getY() - (this.getAlto() / 2);
        int naveAbajo = this.getY() + (this.getAlto() / 2);

        int pepIzquierda = pep.getX() - (pep.getAncho() / 2);
        int pepDerecha = pep.getX() + (pep.getAncho() / 2);
        int pepArriba = pep.getY() - (pep.getAlto() / 2);
        int pepAbajo = pep.getY() + (pep.getAlto() / 2);

        // Comprobar si hay colision
        if (pepDerecha > naveIzquierda && pepIzquierda < naveDerecha &&
                pepAbajo > naveArriba && pepArriba < naveAbajo) {

            // Calcular cada solapamiento
            int solapamientoIzquierda = pepDerecha - naveIzquierda;
            int solapamientoDerecha = naveDerecha - pepIzquierda;
            int solapamientoArriba = pepAbajo - naveArriba;
            int solapamientoAbajo = naveAbajo - pepArriba;

            // Encontrar el menor solapamiento
            int solapamientoMinimo = Math.min(Math.min(solapamientoIzquierda, solapamientoDerecha),
                    Math.min(solapamientoArriba, solapamientoAbajo));

            if (solapamientoMinimo == solapamientoIzquierda) {
                // Colision desde la izquierda
                pep.x = naveIzquierda - (pep.ancho / 2);

            } else if (solapamientoMinimo == solapamientoDerecha) {
                // Colision desde la derecha
                pep.x = naveDerecha + (pep.ancho / 2);

            } else if (solapamientoMinimo == solapamientoArriba) {
                // Colision desde arriba
                pep.y = naveArriba - (pep.alto / 2);

                // Reiniciar la velocidad de caida debido a la colision
                pep.velocidadDeCaida = 0;

                // Restablecer al valor por defecto para poder volver a saltar
                pep.enElAire = false;
            }
        }
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
}
