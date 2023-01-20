package tetris;

import java.util.Random;

public class Tetris {
//variables globales

    static int tauler[][];
    static int figura[][];

    //Medidas del tablero
    static int fila;
    static int col;

    /*Cuando se mueve la ficha, aqui guardanmos la ultima posición
    que será el lado izquierdo de la parte de abajo  */
    static int posFila;
    static int posCol;

    final static int DOS = 2;
    final static int TRES = 3;

//funciones
//figuras
//mover
//caer
    static void caer() {
        Random rnd = new Random();
        int numero = rnd.nextInt(5);

        if (numero == 1) {
            caerCuadrado();
        } else if (numero == 2) {
            caerColumna();
        } else if (numero == 3) {
            caerLlado();
        } else {
            caerL();
        }

    }

    static void caerCuadrado() {
        for (int i = fila - 1; i > 0; i++) {
            if (figura[i][posCol] == 0 && figura[i][posCol + 1] == 0
                    && figura[i - 1][posCol] == 0 && figura[i - 1][posCol + 1] == 0) {
                tauler[i][posCol] = 1;
                tauler[i][posCol + 1] = 1;
                tauler[i - 1][posCol] = 1;
                tauler[i - 1][posCol + 1] = 1;
            }
        }
    }

    /*Desde el punto de referencia de la figura (extremo inferior derecho), iremos
    haciendo la figura para ver donde se puede colocar */
    static void caerColumna() {
        for (int i = fila - 1; i > 0; i++) {
            if (figura[i][posCol] == 0 && figura[i - 1][posCol] == 0
                    && figura[i - DOS][posCol] == 0 && figura[i - TRES][posCol] == 0) {
                tauler[i][posCol] = 1;
                tauler[i - 1][posCol] = 1;
                tauler[i - DOS][posCol] = 1;
                tauler[i - TRES][posCol] = 1;
            }
        }
    }

    static void caerLlado() {
        for (int i = fila - 1; i > 0; i--) {
            if (figura[i][posCol] == 0 && figura[i - 1][posCol] == 0
                    && figura[i][posCol + 1] == 0 && figura[i][posCol + DOS] == 0) {
                tauler[i][posCol] = 1;
                tauler[i - 1][posCol] = 1;
                tauler[i][posCol + 1] = 1;
                tauler[i][posCol + DOS] = 1;
            }
        }
    }

    static void caerL() {
        for (int i = fila - 1; i > 0; i--) {
            if (figura[i][posCol] == 0 && figura[i][posCol + 1] == 0
                    && figura[i - 1][posCol + 1] == 0 && figura[i - DOS][posCol + 1] == 0) {
                tauler[i][posCol] = 1;
                tauler[i][posCol + 1] = 1;
                tauler[i - 1][posCol + 1] = 1;
                tauler[i - DOS][posCol + 1] = 1;
            }
        }
    }

//eliminar fila si está llena
//llegar tope
    static void limite() {
        for (int i = 0; i < col; i++) {
            if (tauler[0][i] != 0) {
                System.out.println("S'ha acabat el joc i has perdut!");
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }

}
