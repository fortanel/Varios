/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serpiente;

import processing.core.PApplet;
import processing.core.PFont;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author fortanel
 */
public class Serpiente extends PApplet {

    int ancho = 25;
    int alto = 25;
    int tam = 20;
    Mundo pantalla;
    int i = 0;
    int direccion = 0;
    boolean continua = true;

    @Override
    public void setup() {
        size(ancho * tam, alto * tam);
        background(0);
        pantalla = new Mundo(ancho, alto);
    }

    @Override
    public void draw() {
        if (!continua) {

        } else {
            frameRate(10);
            background(0);
            stroke(255, 0, 0, 255);
            fill(255, 0, 0);

            ellipse(pantalla.comida[0] * tam + 10, pantalla.comida[1] * tam + 10, 20, 20);

            for (Celda bloque : pantalla.serpiente.cuerpo) {
                if (bloque.equals(pantalla.serpiente.cabeza)) {
                    stroke(255, 255, 255, 255);
                    fill(0, 255, 0);
                    rect(bloque.X * tam, bloque.Y * tam, tam, tam);
                    fill(255, 255, 255);
                    ellipse(bloque.X * tam + 5, bloque.Y * tam + 5, 3, 3);
                } else {
                    stroke(125, 125, 125, 255);
                    fill(255, 255, 255);
                    rect(bloque.X * tam, bloque.Y * tam, tam, tam);

                }
            }

            continua = pantalla.construyeJuego(direccion);
        }
    }

    public void keyPressed() {
        
        switch (key) {
            case 'a':
                direccion = 3;
                break;
            case 'd':
                direccion = 1;
                break;
            case 's':
                direccion = 2;
                break;
            case 'w':
                direccion = 0;
                break;
            default:
                break;

        }
    }

    public class Celda {

        int X;
        int Y;
        boolean ocupado = false;
        // El tipo indica que objeto ocupa la celda puede ser serpiente o muro
        // si es serpiente es true en caso de que sea muro es false
        boolean tipo;

        public Celda(int X, int Y) {
            this.X = X;
            this.Y = Y;
        }

        public void ocupar(boolean tipo) {
            ocupado = true;
            this.tipo = tipo;
        }

    }

    /**
     * Esta clase contiene toda la informacion relacionada con la serpiente como
     * la ubicacion de todas las partes de su cuerpo, donde empieza y donde
     * termina.
     */
    public class Cuerpo {

        LinkedList<Celda> cuerpo = new LinkedList<Celda>();
        Celda cabeza;
        int direccion;

        // El constructor empieza creando la cabeza de la serpiente
        public Cuerpo(int i, int j) {
            Random rnd = new Random();
            cabeza = new Celda(i, j);
            cuerpo.add(cabeza);
            direccion = rnd.nextInt(4);
        }

        /**
         * Es el metodo para mover a la serpiente, en este se verifica que la
         * serpiente no colisione con ella misma o con un muro.
         *
         * @param i
         * @param j
         */
        public int[] mover() {
            int[] auxiliar = new int[2];
            int[] auxiliar2 = new int[2];

            for (Celda casilla : cuerpo) {
                if (cuerpo.indexOf(casilla) == 0) {
                    auxiliar[0] = casilla.X;
                    auxiliar[1] = casilla.Y;
                    int[] pos = calculaPosicion();
                    casilla.X = casilla.X + pos[0];
                    casilla.Y = casilla.Y + pos[1];
                } else {
                    auxiliar2[0] = casilla.X;
                    auxiliar2[1] = casilla.Y;
                    casilla.X = auxiliar[0];
                    casilla.Y = auxiliar[1];
                    auxiliar[0] = auxiliar2[0];
                    auxiliar[1] = auxiliar2[1];
                }
            }
            return auxiliar;
        }

        public void agregaCola(int i, int j) {
            Celda cola = new Celda(i, j);
            cuerpo.add(cola);
        }

        public int[] calculaPosicion() {
            int[] coor = new int[2];
            switch (direccion) {
                case 0:
                    coor[0] = 0;
                    coor[1] = -1;
                    break;
                case 1:
                    coor[0] = 1;
                    coor[1] = 0;
                    break;
                case 2:
                    coor[0] = 0;
                    coor[1] = 1;
                    break;
                case 3:
                    coor[0] = -1;
                    coor[1] = 0;
                    break;
            }
            return coor;
        }

        public int calculaDireccion(int nueva) {
            if (Math.abs(nueva - direccion) % 2 == 0) {
                return direccion;
            } else {
                return nueva;
            }
        }
    }

    public class Mundo {

        Celda[][] mapa;
        Cuerpo serpiente;
        LinkedList<Celda> libre = new LinkedList<Celda>();
        public int[] comida = new int[2];
        boolean continua = true;
        int ancho;
        int alto;

        public Mundo(int ancho, int alto) {
            mapa = new Celda[ancho][alto];
            serpiente = new Cuerpo((int)ancho/2, (int)alto/2);
            this.ancho = ancho;
            this.alto = alto;
            for (int i = 0; i < ancho; i++) {
                for (int j = 0; j < alto; j++) {
                    mapa[i][j] = new Celda(i, j);
                    libre.add(mapa[i][j]);
                }
            }
            Random rnd = new Random();
            libre.remove(serpiente.cabeza);
            continua = colocaComida();
        }

        public boolean moverSerpiente(int direccion) {
            serpiente.direccion = serpiente.calculaDireccion(direccion);
            int[] pos = serpiente.calculaPosicion();
            if (0 > pos[0] + serpiente.cabeza.X || pos[0] + serpiente.cabeza.X > ancho - 1 || 0 > pos[1] + serpiente.cabeza.Y || pos[1] + serpiente.cabeza.Y > alto - 1) {
                return false;
            } else if (mapa[pos[0] + serpiente.cabeza.X][pos[1] + serpiente.cabeza.Y].ocupado == true) {
                return false;
            } else {
                mapa[pos[0] + serpiente.cabeza.X][pos[1] + serpiente.cabeza.Y].ocupado = true;
                libre.remove(mapa[pos[0] + serpiente.cabeza.X][pos[1] + serpiente.cabeza.Y]);
                int[] auxiliar = serpiente.mover();
                if (comida[0] == serpiente.cabeza.X && comida[1] == serpiente.cabeza.Y) {
                    serpiente.agregaCola(auxiliar[0], auxiliar[1]);
                    boolean resultado = colocaComida();
                    return resultado;
                } else {
                    mapa[auxiliar[0]][auxiliar[1]].ocupado = false;
                    libre.add(mapa[auxiliar[0]][auxiliar[1]]);
                    return true;
                }
            }
        }

        public boolean colocaComida() {
            Random rnd = new Random();
            if (libre.size() > 0) {
                Celda casilla = libre.get(rnd.nextInt(libre.size()));
                comida[0] = casilla.X;
                comida[1] = casilla.Y;
                return true;
            } else {
                return false;
            }
        }

        public boolean construyeJuego(int direccion) {
            if (continua) {
                continua = moverSerpiente(direccion);
                return true;
            } else {
                return false;
            }

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PApplet.main(new String[]{"serpiente.Serpiente"});
    }

}
